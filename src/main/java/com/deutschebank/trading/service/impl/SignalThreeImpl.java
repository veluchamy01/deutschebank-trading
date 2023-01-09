/**
 * 
 */
package com.deutschebank.trading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutschebank.trading.external.algo.Algo;
import com.deutschebank.trading.service.SignalHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation layer for {@link SignalHandler}
 * 
 * @author veluchamy.jeganathan
 *
 */
@Service("3")
@Slf4j
public class SignalThreeImpl implements SignalHandler {

	@Autowired
	private Algo algo;

	@Override
	public void handleSignal(int signal) {
		log.debug("Inside handle signalThree implementation");
		algo.setAlgoParam(1, 90);
		algo.setAlgoParam(2, 15);
		algo.performCalc();
		algo.submitToMarket();
		algo.doAlgo();
		log.debug("Exit handle signalThree implementation");
	}

}
