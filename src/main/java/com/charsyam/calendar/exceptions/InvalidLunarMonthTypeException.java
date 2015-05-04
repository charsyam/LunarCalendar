package com.charsyam.calendar.exceptions;

/**
 * Created by charsyam on 2015. 5. 5..
 */
public class InvalidLunarMonthTypeException extends Exception {
	public InvalidLunarMonthTypeException(int m) {
		super(m + " should be in 1 ~ 6");
	}
}
