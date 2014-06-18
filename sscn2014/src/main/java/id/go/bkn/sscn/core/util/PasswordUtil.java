package id.go.bkn.sscn.core.util;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a utility that related with password. most of the code is inspired
 * from http://www.owasp.org/index.php/Hashing_Java
 * 
 * @author bayuadji
 * 
 */
public class PasswordUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5821601416373245537L;

	/** The Constant ITERATION_NUMBER. */
	private static final int ITERATION_NUMBER = 100;

	/** The constant with the number of bytes on 64 bits */
	private static final int BYTES_ON_SIXTY_FOUR_BITS = 8;

	/** The base64. */
	private final Base64 base64;

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(PasswordUtil.class);

	/**
	 * constructor.
	 */
	public PasswordUtil() {
		base64 = new Base64();
	}

	/**
	 * encrypt the password, with SHA-1.
	 * 
	 * @param password
	 *            .
	 * @return a encrypted string.
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @author bayuadji
	 */
	public String encryptPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		// Salt generation 64 bits long
		byte[] bSalt = new byte[BYTES_ON_SIXTY_FOUR_BITS];
		random.nextBytes(bSalt);
		// Digest computation
		byte[] bDigest = getHash(ITERATION_NUMBER, password, bSalt);
		String sDigest = byteToBase64(bDigest);
		String sSalt = byteToBase64(bSalt);
		return sDigest + "$" + sSalt;
	}

	/**
	 * checks the password is equals.
	 * 
	 * @param password
	 *            .
	 * @param hash
	 *            .
	 * @return true, if is password equal
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @author bayuadji
	 */
	public boolean isPasswordEqual(String password, String hash)
			throws IOException, DecoderException {
		if (password == null || hash == null) {
			throw new IllegalArgumentException("Please fill the parameter");
		}
		String[] currentHashSalt = hash.split("\\$");
		if (currentHashSalt.length <= 1) {
			return false;
		}
		if (currentHashSalt[0].isEmpty() || currentHashSalt[1].isEmpty()) {
			return false;
		}
		byte[] bDigest = this.base64ToByte(currentHashSalt[0]);
		byte[] bSalt = this.base64ToByte(currentHashSalt[1]);
		// Compute the new DIGEST
		byte[] proposedDigest;
		try {
			proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage());
			return false;
		}
		return Arrays.equals(proposedDigest, bDigest);
	}

	/**
	 * From a password, a number of iterations and a salt, returns the
	 * corresponding digest.
	 * 
	 * @param iterationNb
	 *            int The number of iterations of the algorithm
	 * @param password
	 *            String The password to encrypt
	 * @param salt
	 *            byte[] The salt
	 * @return byte[] The digested password
	 * @throws NoSuchAlgorithmException
	 *             If the algorithm doesn't exist
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @author bayuadji
	 */
	private byte[] getHash(int iterationNb, String password, byte[] salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}

	/**
	 * From a base 64 representation, returns the corresponding byte[].
	 * 
	 * @param data
	 *            String The base64 representation
	 * @return byte[]
	 * @author bayuadji
	 * @throws DecoderException
	 */
	private byte[] base64ToByte(String data) throws DecoderException {
		return (byte[]) base64.decode(data);
	}

	/**
	 * From a byte[] returns a base 64 representation.
	 * 
	 * @param data
	 *            byte[]
	 * @return String
	 * @author bayuadji
	 */
	private String byteToBase64(byte[] data) {
		// remove the crlf on last character on string.
		return base64.encodeToString(data).trim();		
	}
}
