/**
 * 
 */
package com.deutschebank.trading.service;

/**
 * Service layer for handling the trading signals
 * 
 * @author veluchamy.jeganathan
 *
 */
public interface SignalHandler {

	/**
	 * service method for handling the trading signals
	 * 
	 * @param signal - input trading signal
	 */
	void handleSignal(int signal);

}
