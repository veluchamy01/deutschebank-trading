/**
 * 
 */
package com.deutschebank.trading.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.deutschebank.trading.dto.TradingSignalRequestDto;
import com.deutschebank.trading.dto.TradingSignalResponseDto;

import jakarta.validation.ConstraintViolationException;

/**
 * Tests the controller layer
 * 
 * @author veluchamy.jeganathan
 *
 */
@SpringBootTest
class TradingSignalControllerTest {

	@Autowired
	private TradingSignalController tradingSignalController;

	/**
	 * Test for successful scenario of processing trading signal
	 */
	@Test
	void test_processSignals_Success() {
		TradingSignalRequestDto tradingSignalRequestDto = new TradingSignalRequestDto();
		tradingSignalRequestDto.setSignal(1);

		ResponseEntity<TradingSignalResponseDto> result = tradingSignalController
				.processSignals(tradingSignalRequestDto);

		Assertions.assertThat(result.getBody().getMessage()).contains("signal sent for processing");
		Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
	}

	/**
	 * Test for error scenario while processing trading signal
	 */
	@Test
	void test_processSignals_ValidationException() {
		TradingSignalRequestDto tradingSignalRequestDto = new TradingSignalRequestDto();
		tradingSignalRequestDto.setSignal(99);

		ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
			tradingSignalController.processSignals(tradingSignalRequestDto);
		});

		assertThat(thrown.getMessage()).contains("invalid signal. this signal cannot be processed at the moment");
	}

	/**
	 * Test for error scenario while processing trading signal
	 */
	@Test
	void test_processSignals_ConstraintViolationException() {
		TradingSignalRequestDto tradingSignalRequestDto = new TradingSignalRequestDto();

		ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
			tradingSignalController.processSignals(tradingSignalRequestDto);
		});

		assertThat(thrown.getMessage()).contains("should not be empty");
	}

}
