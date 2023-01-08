/**
 * 
 */
package com.deutschebank.trading.service.impl;

import org.springframework.stereotype.Service;

import com.deutschebank.trading.external.algo.Algo;
import com.deutschebank.trading.service.SignalHandler;

/**
 * Service implementation layer for {@link SignalHandler}
 * 
 * @author veluchamy.jeganathan
 *
 */
@Service
public class SignalHandlerImpl implements SignalHandler {

	@Override
	public void handleSignal(int signal) {
		Algo algo = new Algo();

		switch (signal) {
		case 1:
			algo.setUp();
			algo.setAlgoParam(1, 60);
			algo.performCalc();
			algo.submitToMarket();
			break;

		case 2:
			algo.reverse();
			algo.setAlgoParam(1, 80);
			algo.submitToMarket();
			break;

		case 3:
			algo.setAlgoParam(1, 90);
			algo.setAlgoParam(2, 15);
			algo.performCalc();
			algo.submitToMarket();
			break;

		default:
			algo.cancelTrades();
			break;
		}

		algo.doAlgo();

	}

}
