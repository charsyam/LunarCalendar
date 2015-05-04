package com.charsyam.calendar;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by charsyam on 2015. 5. 5..
 */
public class TestKoreanChineseCalendar {
	@Test
	public void testLunarDateFromSolarDate1() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date1 = cal.fromSolarDate(2013, 6, 16);

		assertEquals(2013, date1.year);
		assertEquals(5, date1.month);
		assertEquals(8, date1.day);

		ChineseCalendar cal2 = ChineseCalendar.getInstance();
		LunarDate date2 = cal2.fromSolarDate(2013, 6, 16);

		assertEquals(2013, date2.year);
		assertEquals(5, date2.month);
		assertEquals(9, date2.day);
	}
}
