package id.go.bkn.sscn.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.common.cache.Cache;

/**
 * Servlet Filter implementation class ValidateSalt
 */
public class ValidateSalt implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Assume its HTTP
		HttpServletRequest httpReq = (HttpServletRequest) request;

		// Get the salt sent with the request
		String salt = (String) httpReq.getParameter("csrfPreventionSalt");

		// Validate that the salt is in the cache
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) httpReq
				.getSession().getAttribute("csrfPreventionSaltCache");

		if (csrfPreventionSaltCache != null && salt != null
				&& csrfPreventionSaltCache.getIfPresent(salt) != null) {

			// If the salt is in the cache, we move on
			chain.doFilter(request, response);
		} else {
			// Otherwise we throw an exception aborting the request flow
			throw new ServletException(
					"Potential CSRF detected!! Inform a scary sysadmin ASAP.");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
