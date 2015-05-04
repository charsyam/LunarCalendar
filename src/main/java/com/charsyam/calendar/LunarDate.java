package com.charsyam.calendar;

/**
 * Created by charsyam on 2015. 5. 5..
 */
public class LunarDate {
	public long solarMillis;
	public int year;
	public int month;
	public int day;
	public boolean isLeapMonth;

	public LunarDate(int year, int month, int day, long solarMillis, boolean isLeapMonth) {
		this(year, month, day, solarMillis);
		this.isLeapMonth = isLeapMonth;
	}

	public LunarDate(int year, int month, int day, long solarMillis) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.solarMillis = solarMillis;
		isLeapMonth = false;
	}

	@Override
	public String toString() {
		String ret = year + "/" + month + "/" + day;
		if (isLeapMonth) {
			ret += " leap";
		}

		return ret;
	}

	public long getSolarMillis() {
		return this.solarMillis;
	}
}
