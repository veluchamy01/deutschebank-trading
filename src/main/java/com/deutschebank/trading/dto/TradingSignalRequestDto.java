/**
 * 
 */
package com.deutschebank.trading.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO class has parameters for handling trading signals
 * 
 * @author veluchamy.jeganathan
 *
 */
@Data
public class TradingSignalRequestDto {

	@NotNull(message = "{signal.empty}")
	@Min(value = 1, message = "{signal.invalid}")
	@Max(value = 1, message = "{signal.invalid}")
	private Integer signal;

}
