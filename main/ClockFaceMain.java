package main;

import javax.swing.*;
import java.awt.*;

import model.*;
import view.*;
import control.*;

/**Main class, where the ClockFace window runs.
 * @author !MULLIGANACEOUS!
 * @version 1.0:Official
 */
public class ClockFaceMain extends JFrame {
	private static final long serialVersionUID = 1;
	private static ClockFaceMain instance;
	
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 480;
	public static final Dimension DIMENSION = 
			new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	private TimeDateModel model;
	private TimeDate timedate;
	private ClockFacePanel clockface;
	private SetClockPanel setTimeDate;
	
	private ClockFaceMain() {
		super();
		this.setLayout(new FlowLayout());
		
		// TimeDate and ClockFace
		this.timedate = new TimeDate();
		this.model = new TimeDateModel(timedate);
		this.timedate.setDate(2018, 3, 11);
		this.timedate.setTime(2, 0, 0);
		
		this.clockface = new ClockFacePanel(model);
		this.getContentPane().add(clockface);
		
		// Controller panel
		this.setTimeDate = new SetClockPanel(model);
		this.getContentPane().add(setTimeDate);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(DIMENSION);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Clock Face");
	}
	
	public void showGUI() {
		this.setVisible(true);
	}
	
	public static ClockFaceMain getInstance() {
		if (instance == null)
			instance = new ClockFaceMain();
		return instance;
	}
	
	public static void main(String[] args) {
		ClockFaceMain window = ClockFaceMain.getInstance();
		window.showGUI();
	}
}
