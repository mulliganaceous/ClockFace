package main;

import model.DateException;
import model.DaysOfMonth;

/**Represents a time and a date
 * @author MULLIGANACEOUS
 */
public class TimeDate {
	/* Variables to represent time */
	private int year, mo, d;
	private int h, m, s;
	
	public TimeDate() {
		this.year = 1970;
		this.mo = 0;
		this.d = 0;
		this.h = 0;
		this.m = 0;
		this.s = 0;
	}
	
	private void setYear(int year) {
		this.year = year;
	}
	
	private void setMonth(int mo) throws DateException {
		if (mo < 1 || mo > 12) 
			throw new DateException("Month out of range.");
		this.mo = mo;
	}
	
	private void setDay(int d) throws DateException {
		int daysOfMonth = DaysOfMonth.numOfDays(mo, year);
		if (d < 1 || (daysOfMonth != 0 && d > daysOfMonth))
			throw new DateException("Date should be between 1 to " + daysOfMonth + ".");
		this.d = d;
	}
	
	public void setHour(int h) throws DateException {
		if (h < 0 || h >= 24)
			throw new DateException("Hour should be between 0 to 23.");
		this.h = h;
	}
	
	public void setMinute(int m) throws DateException {
		if (m < 0 || m >= 60)
			throw new DateException("Minute should be between 0 to 59.");
		this.m = m;
	}
	
	public void setSecond(int s) throws DateException {
		if (m < 0 || m >= 60)
			throw new DateException("Second should be between 0 to 59.");
		this.s = s;
	}
	
	public void setDate(int year, int mo, int d) {
		this.setYear(year);
		try {
			this.setMonth(mo);
			this.setDay(d);
		} catch (DateException e) {
			System.err.println("Failed to change date!");
		}
	}
	
	public void resetDate() {
		this.year = 0;
		this.mo = 0;
		this.d = 0;
	}
	
	public void setTime(int h, int m, int s) {
		try {
			this.setHour(h);
			this.setMinute(m);
			this.setSecond(s);
		} catch (DateException e) {
			System.err.println("Failed to change time!");
		}
	}
	
	public int getYear() {
		return this.year;
	}
	
	public int getMonth() {
		return this.mo;
	}
	
	public int getDay() {
		return this.d;
	}
	
	public int getHour() {
		return this.h;
	}
	
	public int getMinute() {
		return this.m;
	}
	
	public int getSecond() {
		return this.s;
	}
	
	public static boolean isLeapYear(int year) {
		if (year % 4 != 0) return false;
		else if (year % 4 == 0) {
			if (year % 400 == 0) return true;
			else if (year % 100 == 0 && year % 400 != 0) return false;
			else return true;
		}
	}
	
	public String toString() {
		String str = "";
		str += String.format("%04d/%02d/%02d_", year, mo, d);
		str += String.format("%02d:%02d:%02d", h, m, s);
		return str;
	}
}
