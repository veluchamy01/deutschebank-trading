/**
 * 
 */
package com.deutschebank.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.trading.dto.SignalDto;
import com.deutschebank.trading.service.SignalHandler;

/**
 * REST Controller for trading service
 * 
 * @author veluchamy.jeganathan
 *
 */
@RestController
@RequestMapping("/trading-signals")
public class TradingSignalController {

	@Autowired
	private SignalHandler signalHandler;

	/**
	 * API to process the trading signals
	 * 
	 * @param signalDto - contains trading signal details
	 * @return - response message
	 */
	@PostMapping
	public ResponseEntity<String> processSignals(@RequestBody SignalDto signalDto) {

		signalHandler.handleSignal(signalDto.getSignal());

		return new ResponseEntity<>("Signal sent for processing", HttpStatus.CREATED);
	}

}
