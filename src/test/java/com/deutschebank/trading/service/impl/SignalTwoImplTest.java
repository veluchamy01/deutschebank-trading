/**
 * 
 */
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
 * Tests the service implementation layer of {@link SignalTwoImpl}
 * @author veluchamy.jeganathan
 *
 */
@ExtendWith(MockitoExtension.class)
class SignalTwoImplTest {

	@InjectMocks
	private SignalTwoImpl signalTwoImpl;

	@Mock
	private Algo algo;

	/**
	 * Tests the handle signal implementation
	 */
	@Test
	void test_handleSignal() {
		Mockito.doNothing().when(algo).reverse();
		Mockito.doNothing().when(algo).setAlgoParam(1, 80);
		Mockito.doNothing().when(algo).submitToMarket();
		Mockito.doNothing().when(algo).doAlgo();

		signalTwoImpl.handleSignal(2);

		verify(algo).reverse();
		verify(algo).setAlgoParam(1, 80);
		verify(algo).submitToMarket();
		verify(algo).doAlgo();
	}

}
