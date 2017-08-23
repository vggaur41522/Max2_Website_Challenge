package com.max2.common;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.max2.controller.Max2Challenge;

@Component
public class CommonValidations {
	final static Logger LOGGER = Logger.getLogger(CommonValidations.class);
	// Phone number validator !!
	public static boolean validatePhone(String ph) {
		ph = ph.trim();
		// validate phone numbers of format "1234567890"
		if (ph.matches("\\d{10}"))
			return true;
		// validating phone number with - or spaces
		else if (ph.matches("\\d{3}[-\\s]\\d{3}[-\\s]\\d{4}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (ph.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}

	// Zip Code validator
	public static boolean validateZip(String zip) {
		zip = zip.trim();
		// validate phone numbers of format "1234567890"
		if (zip.matches("\\d{5}"))
			return true;
		// validating phone number with - or spaces
		else if (zip.matches("\\d{5}[-\\s]\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}
	
	public static String stringOP(String[] test)
	{
		StringBuilder sb = new StringBuilder();
		for(String s : test)
		{
			sb.append(s + " | ");
		}
		return sb.toString();
	}
}
