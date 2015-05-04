package com.charsyam.calendar;

import com.charsyam.calendar.exceptions.InvalidLunarMonthTypeException;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by charsyam on 2015. 5. 4..
 */
public abstract class LunarCalendar {
	protected LunarCalendar() {
	}

	static int startYear = 1900;
	final long lunarEpochDaysInMillis = _getLunarEpochDays();
	protected long _getLunarEpochDays() {
		return getTimeInMillis(1900, 1, 31);
	}

	protected long getLunarEpochDays() {
		return lunarEpochDaysInMillis;
	}

	protected long getTimeInMillis(int year, int month, int days) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		TimeZone tz = TimeZone.getTimeZone("UTC");
		cal.setTimeZone(tz);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public LunarDate fromSolarDate(int year, int month, int days) {
		long solarInMillis = getTimeInMillis(year, month, days);
		return solarToLunar(solarInMillis);
	}

	abstract int[][] getLunarYearInfo();

	boolean isInThisDays(long days, long targetDays) {
		return ((days - targetDays) < 0);
	}

	int [] getMonthInfo(int v) {
		int leapMonthDays = (((v & 0xF0000) >> 16) > 0) ? 30 : 29;
		int leapMonth = v & 0x0000F;
		int tmp = (v & 0x0FFF0) >> 4;

		int month[] = new int[12];

		for (int i = 11; i >= 0; i--) {
			boolean longMonth = ((tmp & 0x0001) == 0x0001);
			month[i] = longMonth ? 2 : 1;
			tmp >>= 1;
		}

		if (leapMonth > 0) {
			int tmonth = month[leapMonth - 1];
			month[leapMonth - 1] = (leapMonthDays == 29) ? 3 : 5;
			if (tmonth == 2) {
				month[leapMonth - 1] += 1;
			}
		}

		return month;
	}

	class LunarDays {
		public int mDays;
		public int nDays;
		public int lDays;

		LunarDays(int mDays, int nDays, int lDays) {
			this.mDays = mDays;
			this.nDays = nDays;
			this.lDays = lDays;
		}
	}

	private LunarDays _getLunarDays(int m) throws InvalidLunarMonthTypeException {
		switch(m) {
			case 1:
				return new LunarDays(29, 29, 0);
			case 2:
				return new LunarDays(30, 30, 0);
			case 3:
				return new LunarDays(58, 29, 29);
			case 4:
				return new LunarDays(59, 30, 29);
			case 5:
				return new LunarDays(59, 29, 30);
			case 6:
				return new LunarDays(60, 30, 30);
		}

		throw new InvalidLunarMonthTypeException(m);
	}

	private LunarDate solarToLunar(long millis) {
		long lunarBaseMillis = millis - getLunarEpochDays();
		long seconds = lunarBaseMillis / 1000;
		long days = seconds / 86400;

		int yearInfos[][] = getLunarYearInfo();
		boolean isLeapMonth = false;
		boolean matched = false;
		int targetMonth = 0;

		int year;
		for (year = 0; (year < yearInfos.length); year++) {
			int yearDays = yearInfos[year][1];
			if (isInThisDays(days, yearDays)) {
				int [] month = getMonthInfo(yearInfos[year][0]);
				for (int m : month) {
					try {
						LunarDays lunarDays = _getLunarDays(m);
						if (isInThisDays(days, lunarDays.mDays)) {
							if (isInThisDays(days ,lunarDays.nDays) == false) {
								days -= lunarDays.nDays;
								isLeapMonth = true;
							}

							matched = true;
							break;
						}

						days -= lunarDays.mDays;
						targetMonth++;
					} catch (Exception e) {

					}
				}

				if (matched) {
					break;
				}
			}

			days -= yearDays;
		}

		return new LunarDate(year + startYear, targetMonth+1, (int)days + 1, millis, isLeapMonth);
	}
}
