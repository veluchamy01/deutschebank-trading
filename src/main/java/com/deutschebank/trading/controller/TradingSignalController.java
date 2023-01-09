/**
 * 
 */
package com.deutschebank.trading.controller;

import static com.deutschebank.trading.constants.SignalConstants.RESPONSE_TRADING_SIGNAL;
import static com.deutschebank.trading.enums.Messages.MESSAGE;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.trading.dto.TradingSignalRequestDto;
import com.deutschebank.trading.dto.TradingSignalResponseDto;
import com.deutschebank.trading.service.SignalHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for trading service
 * 
 * @author veluchamy.jeganathan
 *
 */
@RestController
@RequestMapping("/trading-signals")
@Slf4j
@RequiredArgsConstructor
@Validated
public class TradingSignalController {

	private final Map<String, SignalHandler> signalFactory;

	/**
	 * API to process the trading signals
	 * 
	 * @param tradingSignalRequestDto - contains trading signal details
	 * @return - response message
	 */
	@PostMapping
	public ResponseEntity<TradingSignalResponseDto> processSignals(
			@RequestBody @Valid TradingSignalRequestDto tradingSignalRequestDto) {
		Integer signal = tradingSignalRequestDto.getSignal();
		log.info("TradingSignal controller invoked with signal {}.", signal);

		SignalHandler signalHandler = this.signalFactory.get(String.valueOf(signal));
		signalHandler.handleSignal(signal);

		log.info("TradingSignal controller exit with success response.");
		return new ResponseEntity<>(
				TradingSignalResponseDto.builder().message(MESSAGE.getMessage(RESPONSE_TRADING_SIGNAL)).build(),
				HttpStatus.CREATED);
	}

}
