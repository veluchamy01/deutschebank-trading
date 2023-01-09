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
 * Tests the service implementation layer of {@link SignalThreeImpl}
 * 
 * @author veluchamy.jeganathan
 *
 */
@ExtendWith(MockitoExtension.class)
class SignalThreeImplTest {

	@InjectMocks
	private SignalThreeImpl signalThreeImpl;

	@Mock
	private Algo algo;

	/**
	 * Tests the handle signal implementation
	 */
	@Test
	void test() {
		Mockito.doNothing().when(algo).setAlgoParam(1, 90);
		Mockito.doNothing().when(algo).setAlgoParam(2, 15);
		Mockito.doNothing().when(algo).performCalc();
		Mockito.doNothing().when(algo).submitToMarket();
		Mockito.doNothing().when(algo).doAlgo();

		signalThreeImpl.handleSignal(3);

		verify(algo).setAlgoParam(1, 90);
		verify(algo).setAlgoParam(2, 15);
		verify(algo).performCalc();
		verify(algo).submitToMarket();
		verify(algo).doAlgo();
	}

}
