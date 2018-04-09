package model;

import java.util.Observable;

import main.TimeDate;
import view.DrawClockCommand;

public class TimeDateModel extends Observable {
	private TimeDate timedate;
	private DrawClockCommand command;
	
	public TimeDateModel(TimeDate timedate) {
		this.timedate = timedate;
	}
	
	public TimeDate getTimedate() {
		return this.timedate;
	}
	
	public void setCommand(DrawClockCommand command) {
		this.command = new DrawClockCommand(timedate);
	}
	
	public DrawClockCommand getCommand() {
		return this.command;
	}
	
	public void setDate(int year, int mo, int d) {
		this.timedate.setDate(year, mo, d);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setTime(int h, int m, int s) {
		this.timedate.setTime(h, m, s);
		this.setChanged();
		this.notifyObservers();
	}
}
