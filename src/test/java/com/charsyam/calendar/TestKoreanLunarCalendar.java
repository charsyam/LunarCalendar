package com.charsyam.calendar;

import com.charsyam.calendar.exceptions.InvalidLunarDateException;
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

		verirfyWithSolarMillis(solar, 2014, 10, 23);
	}

	private void verirfyWithSolarMillis(long millis, int year, int month, int day) {
		Calendar ocal = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone("UTC");
		ocal.setTimeZone(tz);
		ocal.setTimeInMillis(millis);

		int tyear = ocal.get(Calendar.YEAR);
		int tmonth = ocal.get(Calendar.MONTH);
		int tday = ocal.get(Calendar.DAY_OF_MONTH);

		assertEquals(year, tyear);
		assertEquals(month - 1, tmonth);
		assertEquals(day, tday);
	}

	@Test
	public void testSolarDateFromLunarDate1() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		long solar = 0;
		try {
			solar = cal.fromLunarDate(1900, 1, 1);
		} catch(Exception e) {

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

	@Test
	public void testSolarDateFromLunarDate4() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		long solar = 0;
		try {
			solar = cal.fromLunarDate(1987, 6, 1, true);
		} catch (Exception e) {

		}

		verirfyWithSolarMillis(solar, 1987, 7, 26);
	}

	@Test
	public void testSolarDateFromLunarDate5() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		long solar = 0;
		try {
			solar = cal.fromLunarDate(1987, 6, 30, false);
		} catch (Exception e) {

		}

		verirfyWithSolarMillis(solar, 1987, 7, 25);
	}

	@Test
	public void testSolarDateFromLunarDate6() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		long solar = 0;
		boolean ok = false;
		try {
			solar = cal.fromLunarDate(1987, 6, 30, true);
		} catch (InvalidLunarDateException e) {
			ok = true;
		} catch(Exception e) {

		}

		assertTrue(ok);
	}

	@Test
	public void testLunarDateIsValid1() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertTrue(cal.isValid(1974, 2, 29));
	}

	@Test
	public void testLunarDateIsValid2() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertTrue(cal.isValid(1974, 2, 30));
	}

	@Test
	public void testLunarDateIsValid2_1() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertFalse(cal.isValid(1974, 2, 30, true));
	}

	@Test
	public void testLunarDateIsValid3() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertTrue(cal.isValid(1974, 1, 29));
	}

	@Test
	public void testLunarDateIsValid4() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertFalse(cal.isValid(1974, 2, 31));
	}

	@Test
	public void testLunarDateIsValid5() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertTrue(cal.isValid(1987, 6, 30));
	}

	@Test
	public void testLunarDateIsValid6() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertFalse(cal.isValid(1987, 6, 30, true));
	}

	@Test
	public void testLunarDateIsValid7() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertTrue(cal.isValid(1987, 6, 29));
	}

	@Test
	public void testLunarDateIsValid8() {
		KoreanCalendar cal = KoreanCalendar.getInstance();
		assertTrue(cal.isValid(1987, 6, 29, true));
	}
}
