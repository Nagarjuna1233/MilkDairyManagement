package com.milkdairy.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MDMValidationUtil {

	//private static final Logger LOG = Logger.getLogger(MDMValidationUtil.class);

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_PATTERN = "\\d{10}";

	public static boolean isValidEmailAddress(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		final Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean isValidPhoneNum(String num) {
		Pattern patternMobile = Pattern.compile(PHONE_PATTERN);
		final Matcher matcherMobile = patternMobile.matcher(num);
		return matcherMobile.matches();
	}

}
