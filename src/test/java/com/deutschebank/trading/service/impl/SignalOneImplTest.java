package com.deutschebank.trading.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deutschebank.trading.external.algo.Algo;

/**
 * Tests the service implementation layer of {@link SignalOneImpl}
 * 
 * @author veluchamy.jeganathan
 *
 */
@ExtendWith(MockitoExtension.class)
class SignalOneImplTest {

	@InjectMocks
	private SignalOneImpl signalOneImpl;

	@Mock
	private Algo algo;

	/**
	 * Tests the handle signal implementation
	 */
	@Test
	void test_handleSignal() {
		Mockito.doNothing().when(algo).setUp();
		Mockito.doNothing().when(algo).setAlgoParam(1, 60);
		Mockito.doNothing().when(algo).performCalc();
		Mockito.doNothing().when(algo).submitToMarket();
		Mockito.doNothing().when(algo).doAlgo();

		signalOneImpl.handleSignal(1);

		verify(algo).setUp();
		verify(algo).setAlgoParam(1, 60);
		verify(algo).performCalc();
		verify(algo).submitToMarket();
		verify(algo).doAlgo();
	}

}
