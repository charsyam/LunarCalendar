package com.charsyam.calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by charsyam on 2015. 5. 5..
 */
public class TestKoreanLunarCalendar {
	@Test
	public void testLunarDateFromSolarDate1() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(1900, 1, 31);

		assertEquals(1900, date.year);
		assertEquals(1, date.month);
		assertEquals(1, date.day);
	}

	@Test
	public void testLunarDateFromSolarDate2() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(1901, 2, 19);

		assertEquals(1901, date.year);
		assertEquals(1, date.month);
		assertEquals(1, date.day);
	}

	@Test
	public void testLunarDateFromSolarDate3() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(1902, 2, 8);

		assertEquals(1902, date.year);
		assertEquals(1, date.month);
		assertEquals(1, date.day);
	}

	@Test
	public void testLunarDateFromSolarDate4() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(1903, 1, 29);

		assertEquals(1903, date.year);
		assertEquals(1, date.month);
		assertEquals(1, date.day);
	}

	@Test
	public void testLunarDateFromSolarDate5() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(1904, 2, 16);

		assertEquals(1904, date.year);
		assertEquals(1, date.month);
		assertEquals(1, date.day);
	}

	@Test
	public void testLunarDateFromSolarDate6() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(1905, 2, 4);

		assertEquals(1905, date.year);
		assertEquals(1, date.month);
		assertEquals(1, date.day);
	}

	@Test
	public void testLunarDateFromSolarDate7() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(2015, 5, 4);

		assertEquals(2015, date.year);
		assertEquals(3, date.month);
		assertEquals(16, date.day);
	}

	@Test
	public void testLunarDateFromSolarDate8() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(2014, 10, 23);

		assertEquals(2014, date.year);
		assertEquals(9, date.month);
		assertEquals(30, date.day);
		assertFalse(date.isLeapMonth);
	}

	@Test
	public void testLunarDateFromSolarDate9() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(2014, 10, 24);

		assertEquals(2014, date.year);
		assertEquals(9, date.month);
		assertEquals(1, date.day);
		assertTrue(date.isLeapMonth);
	}

	@Test
	public void testLunarDateFromSolarDate10() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(1987, 8, 15);

		assertEquals(1987, date.year);
		assertEquals(6, date.month);
		assertEquals(21, date.day);
		assertTrue(date.isLeapMonth);
	}

	@Test
	public void testLunarDateFromSolarDate11() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		LunarDate date = cal.fromSolarDate(2014, 10, 23);
		long solar = date.getSolarMillis();

		Calendar ocal = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone("UTC");
		ocal.setTimeZone(tz);
		ocal.setTimeInMillis(solar);

		assertEquals(2014, ocal.get(Calendar.YEAR));
		assertEquals(10 - 1, ocal.get(Calendar.MONTH));
		assertEquals(23, ocal.get(Calendar.DAY_OF_MONTH));
	}

	private void verirfyWithSolarMillis(long millis, int year, int month, int day) {
		Calendar ocal = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone("UTC");
		ocal.setTimeZone(tz);
		ocal.setTimeInMillis(millis);

		assertEquals(year, ocal.get(Calendar.YEAR));
		assertEquals(month - 1, ocal.get(Calendar.MONTH));
		assertEquals(day, ocal.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSolarDateFromLunarDate1() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		long solar = 0;
		try {
			solar = cal.fromLunarDate(1900, 1, 1);
		} catch (Exception e) {

		}
		verirfyWithSolarMillis(solar, 1900, 1, 31);
	}

	@Test
	public void testSolarDateFromLunarDate2() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		long solar = 0;
		try {
			solar = cal.fromLunarDate(1987, 6, 21, true);
		} catch (Exception e) {

		}

		verirfyWithSolarMillis(solar, 1987, 8, 15);
	}

	@Test
	public void testSolarDateFromLunarDate3() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		long solar = 0;
		try {
			solar = cal.fromLunarDate(1987, 6, 21, false);
		} catch (Exception e) {

		}

		verirfyWithSolarMillis(solar, 1987, 7, 16);
	}
}
