/**
 * 
 */
package com.deutschebank.trading.enums;

import java.util.Locale;
import java.util.ResourceBundle;

import com.deutschebank.trading.constants.SignalConstants;

/**
 * Enum to fetch messages from code
 * 
 * @author veluchamy.jeganathan
 *
 */
public enum Messages {

	MESSAGE;

	private static ResourceBundle resourceBundleMessage = ResourceBundle.getBundle(SignalConstants.MESSAGE,
			Locale.ENGLISH);

	/**
	 * To fetch description from property file based on message code
	 * 
	 * @param messageCode - messageCode as string
	 * @return - description of the messageCode code
	 */
	public String getMessage(String messageCode) {
		return resourceBundleMessage.getString(messageCode);
	}

}
