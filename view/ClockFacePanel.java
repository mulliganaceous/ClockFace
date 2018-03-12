package view;
import javax.swing.*;

import luminescent.Luminescent;
import main.ClockFaceMain;
import main.TimeDate;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import model.*;

public class ClockFacePanel extends JPanel implements Luminescent, Observer {
	private static final long serialVersionUID = 100;
	public static final int LENGTH = ClockFaceMain.WINDOW_HEIGHT;
	
	private TimeDateModel model;
	private TimeDate timedate;

	public ClockFacePanel(TimeDateModel model) {
		super();
		this.model = model;
		this.timedate = model.getTimedate();
		this.setPreferredSize(new Dimension(LENGTH, LENGTH));
		this.setBackground(Color.BLACK);
		
		// Connect to Model
		this.model.addObserver(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		this.model.setCommand(new DrawClockCommand(timedate));
		this.model.getCommand().execute(g2);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Updated");
		this.repaint();
	}
}
