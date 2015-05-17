package com.charsyam.calendar.exceptions;

/**
 * Created by charsyam on 2015. 5. 5..
 */
public class InvalidLunarDateException extends Exception{
	public InvalidLunarDateException(int year, int month, int days) {
		super(year + "-" + month + "-" + days + " is invalid lunardate");
	}
}
