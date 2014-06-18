package id.go.bkn.sscn.core.persistence.domain;

import java.io.Serializable;

/**
 * The Class CoreDomain.
 * 
 * @author Roberto
 */

public abstract class CoreDomain implements Serializable{
	
	private static final long serialVersionUID = -1422126445193875973L;
	
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int TWELVE = 12;
	public static final int FIFTEEN = 15;
	public static final int SIXTEEN = 16;
	public static final int SEVENTEEN = 17;
	public static final int EIGHTEEN = 18;
	public static final int NINETEEN = 19;
	public static final int TWENTY = 20;
	public static final int TWENTY_TWO = 22;
	public static final int TWENTY_FIVE = 25;
	public static final int THIRTY = 30;
	public static final int THIRTY_TWO = 32;
	public static final int THIRTY_FIVE = 35;
	public static final int THIRTY_EIGHT = 38;
	public static final int FOURTY = 40;
	public static final int FIFTY = 50;
	public static final int SIXTY = 60;
	public static final int SIXTY_FIVE = 65;
	public static final int SEVENTY = 70;
	public static final int EIGHTY = 80;
	public static final int EIGHTY_ONE = 81;
	public static final int EIGHTY_THREE = 83;
	public static final int NINETY_FIVE = 95;
	public static final int HUNDRED = 100;
	public static final int HUNDRED_TWENTY = 120;
	public static final int HUNDRED_TWENTY_EIGHT = 128;
	public static final int HUNDRED_FIFTY = 150;
	public static final int HUNDRED_SIXTY = 160;
	public static final int TWO_HUNDRED = 200;
	public static final int TWO_HUNDRED_FIFTY = 250;
	public static final int TWO_HUNDRED_FIFTY_FOUR = 254;
	public static final int TWO_HUNDRED_FIFTY_FIVE = 255;
	public static final int THREE_HUNDRED = 300;
	public static final int THOUSAND_TWENTY_FOUR = 1024;
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 * @author Roberto
	 */
	public abstract String getId();

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id to set
	 * @author Roberto
	 */
	public abstract void setId(String id);

}
