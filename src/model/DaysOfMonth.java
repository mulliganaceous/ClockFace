package model;

import main.TimeDate;

public class DaysOfMonth {
	private static final int[] DAYS_IN_MONTH = generate();

	private static final int[] generate() {
		final int[] DAYS_IN_MONTH = new int[12];
		DAYS_IN_MONTH[0] = 31;
		DAYS_IN_MONTH[1] = 28;
		DAYS_IN_MONTH[2] = 31;
		DAYS_IN_MONTH[3] = 30;
		DAYS_IN_MONTH[4] = 31;
		DAYS_IN_MONTH[5] = 30;
		DAYS_IN_MONTH[6] = 31;
		DAYS_IN_MONTH[7] = 31;
		DAYS_IN_MONTH[8] = 30;
		DAYS_IN_MONTH[9] = 31;
		DAYS_IN_MONTH[10] = 30;
		DAYS_IN_MONTH[11] = 31;
		return DAYS_IN_MONTH;
	}
	
	public static int numOfDays(int month, int year) {
		if (TimeDate.isLeapYear(year))
			DAYS_IN_MONTH[1] = 29;
		else
			DAYS_IN_MONTH[1] = 28;
		if (month >= 1 && month <= 12)
			return DAYS_IN_MONTH[month - 1];
		else
			return -1;
	}
}