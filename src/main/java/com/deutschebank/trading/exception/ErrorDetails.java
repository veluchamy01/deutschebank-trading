/**
 * 
 */
package com.deutschebank.trading.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Custom Error details for exception handling
 * 
 * @author veluchamy.jeganathan
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private List<String> details;

}
