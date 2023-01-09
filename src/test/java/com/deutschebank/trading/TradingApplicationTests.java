package com.deutschebank.trading;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.deutschebank.trading.controller.TradingSignalController;
import com.deutschebank.trading.dto.TradingSignalRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * End to End Tests from the application level
 * 
 * @author veluchamy.jeganathan
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class TradingApplicationTests {

	@Autowired
	private TradingSignalController tradingSignalController;

	@Autowired
	MockMvc mockMvc;

	/**
	 * Test to check if application loads correctly
	 */
	@Test
	void contextLoads() {
		assertThat(tradingSignalController).isNotNull();
	}

	/**
	 * Test for successful scenario of processing trading signal
	 */
	@Test
	void test_processSignals_Success() throws Exception {
		TradingSignalRequestDto tradingSignalRequestDto = new TradingSignalRequestDto();
		tradingSignalRequestDto.setSignal(1);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();

		mockMvc.perform(post("/trading-signals").content(objectMapper.writeValueAsString(tradingSignalRequestDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.message").value("signal sent for processing"));
	}

	/**
	 * Test for error scenario while processing trading signal
	 */
	@Test
	void test_processSignals_ValidationException() throws Exception {
		TradingSignalRequestDto tradingSignalRequestDto = new TradingSignalRequestDto();
		tradingSignalRequestDto.setSignal(99);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();

		mockMvc.perform(post("/trading-signals").content(objectMapper.writeValueAsString(tradingSignalRequestDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.message").value("Validation Failed")).andExpect(jsonPath("$.details[0]")
						.value("signal: invalid signal. this signal cannot be processed at the moment"));
	}

	/**
	 * Test for error scenario while processing trading signal
	 */
	@Test
	void test_processSignals_ConstraintViolationException() throws Exception {
		TradingSignalRequestDto tradingSignalRequestDto = new TradingSignalRequestDto();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();

		mockMvc.perform(post("/trading-signals").content(objectMapper.writeValueAsString(tradingSignalRequestDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.message").value("Validation Failed"))
				.andExpect(jsonPath("$.details[0]").value("signal: should not be empty"));
	}

}
