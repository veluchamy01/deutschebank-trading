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
@Service("2")
@Slf4j
public class SignalTwoImpl implements SignalHandler {

	@Autowired
	private Algo algo;

	@Override
	public void handleSignal(int signal) {
		log.debug("Inside handle signal implementation");
		algo.reverse();
		algo.setAlgoParam(1, 80);
		algo.submitToMarket();
		algo.doAlgo();
		log.debug("Exit handle signal implementation");
	}

}
