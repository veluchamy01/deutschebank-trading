/**
 * 
 */
package com.deutschebank.trading.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO class has response parameters of trading signals
 * 
 * @author veluchamy.jeganathan
 *
 */
@Data
@Builder
public class TradingSignalResponseDto {

	private String message;

}
