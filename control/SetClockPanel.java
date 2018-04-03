package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import main.ClockFaceMain;
import model.*;
import luminescent.*;

public class SetClockPanel extends LuminescentPanel {
	private static final long serialVersionUID = 100;
	private static final int HEIGHT = ClockFaceMain.WINDOW_HEIGHT;
	private static final int WIDTH = ClockFaceMain.WINDOW_WIDTH - HEIGHT;
	private TimeDateModel model;
	
	private static JTextField yearField, moField, dField;
	private static JTextField hField, mField, sField;
	
	private static LuminescentButton updateButton;
	private static LuminescentButton updateTimeButton;
	private static LuminescentButton saveImageButton;
	private static LuminescentButton liveUpdateButton;
	
	private Timer timer;
	private boolean isLive;
	
	public SetClockPanel(TimeDateModel model) {
		super();
		this.model = model;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// Date label
		yearField = new LuminescentField(3);
		moField = new LuminescentField(2);
		dField = new LuminescentField(2);
		this.add(new LuminescentLabel("Set Date:"));
		this.add(yearField);
		this.add(new LuminescentLabel("/", -1));
		this.add(moField);
		this.add(new LuminescentLabel("/", -1));
		this.add(dField);
		
		// Time label
		hField = new LuminescentField(2);
		mField = new LuminescentField(2);
		sField = new LuminescentField(2);
		this.add(new LuminescentLabel("Set Time:"));
		this.add(hField);
		this.add(new LuminescentLabel(":", -1));
		this.add(mField);
		this.add(new LuminescentLabel(":", -1));
		this.add(sField);
		
		// Update buttons
		updateButton = new LuminescentButton("Update");
		updateButton.addActionListener(new UpdateListener());
		this.add(updateButton);
		updateTimeButton = new LuminescentButton("Update Time");
		updateTimeButton.addActionListener(new UpdateTimeListener());
		this.add(updateTimeButton);
		saveImageButton = new LuminescentButton("Save Image!");
		saveImageButton.addActionListener(new SaveClockFaceListener());
		this.add(saveImageButton);
		
		// Automatic Update
		liveUpdateButton = new LuminescentButton("Auto-Update");
		liveUpdateButton.addActionListener(new LiveUpdateListener());
		this.add(liveUpdateButton);
		this.timer = new Timer(16, new TimerListener());
	}
	
	private void updateClock() {
		int year, mo, d, h, m, s;
		try {
			year = Integer.parseInt(yearField.getText());
			mo = Integer.parseInt(moField.getText());
			d = Integer.parseInt(dField.getText());
			// Millennium settings
			if (year >= 00 && year < 20)
				year += 2000;
			else if (year >= 20 && year <= 99)
				year += 1900;
			SetClockPanel.this.model.setDate(year, mo, d);
		} catch (NumberFormatException e) {
			System.out.println("Date not updated.");
		}
		try {
			h = Integer.parseInt(hField.getText());
			m = Integer.parseInt(mField.getText());
			s = Integer.parseInt(sField.getText());
			SetClockPanel.this.model.setTime(h, m, s);
		} catch (NumberFormatException e) {
			System.out.println("Time not updated.");
		}
	}
	
	private void updateClockToTime() {
		int year, mo, d, h, m, s;
		LocalDateTime localTimeDate = LocalDateTime.now();
		year = localTimeDate.getYear();
		mo = localTimeDate.getMonthValue();
		d = localTimeDate.getDayOfMonth();
		h = localTimeDate.getHour();
		m = localTimeDate.getMinute();
		s = localTimeDate.getSecond();
		SetClockPanel.this.model.setDate(year, mo, d);
		SetClockPanel.this.model.setTime(h, m, s);
	}
	
	private void startTimer() {
		this.isLive = true;
		this.timer.start();
	}
	
	private void stopTimer() {
		this.isLive = false;
		this.timer.stop();
	}
	
	private class UpdateListener implements ActionListener {
		public UpdateListener() {
			super();
		}
		
		public void actionPerformed(ActionEvent arg0) {
			SetClockPanel.this.updateClock();
			System.out.printf("Date is now %s\n", SetClockPanel.this.model.getTimedate());
		}
	}
	
	private class UpdateTimeListener implements ActionListener {
		public UpdateTimeListener() {
			super();
		}
		
		public void actionPerformed(ActionEvent arg0) {
			SetClockPanel.this.updateClockToTime();
			System.out.printf("Date is now %s\n", SetClockPanel.this.model.getTimedate());
		}
	}
	
	private class SaveClockFaceListener implements ActionListener {
		public SaveClockFaceListener() {
			super();
		}
		
		public void actionPerformed(ActionEvent arg0) {
			SetClockPanel.this.model.getCommand().saveImage();
			System.out.printf("%s.gif is now saved in Saved_Images folder\n", 
					SetClockPanel.this.model.getTimedate());
		}
	}
	
	private class LiveUpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (SetClockPanel.this.isLive) {
				SetClockPanel.this.stopTimer();
				SetClockPanel.liveUpdateButton.turnOff();
			}
			else {
				updateClockToTime();
				SetClockPanel.this.startTimer();
				SetClockPanel.liveUpdateButton.turnOn();
			}
		}
	}
	
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (SetClockPanel.this.isLive)
				updateClockToTime();
		}
	}
}
