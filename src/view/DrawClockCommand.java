package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import luminescent.Luminescent;
import main.TimeDate;
import model.DaysOfMonth;

public class DrawClockCommand {
	private static final int LENGTH = ClockFacePanel.LENGTH;
	private TimeDate timedate;
	
	public DrawClockCommand(TimeDate timedate) {
		this.timedate = timedate;
	}
	
	public void execute(Graphics2D g2) {
		final int m = 40;
		g2.setFont(Luminescent.SMALL_FONT);
		g2.setColor(Luminescent.GITD);
		g2.drawRect(0, 0, LENGTH-1, LENGTH-1);
		
		/* Draw clock */
		g2.setStroke(new BasicStroke(6));
		g2.drawOval(m, m, LENGTH - m*2, LENGTH - m*2);
		// Draw clock numbers
		double x, y;
		double th;
		for (int k = 0; k < 12; k++) {
			th = (3-k)*Math.PI/6;
			x = LENGTH/2 + Math.cos(th) * (LENGTH/2 - m*1.5);
			y = LENGTH/2 - Math.sin(th) * (LENGTH/2 - m*1.5);
			g2.fillOval((int)x - 6, (int)y - 6, 12, 12);
		}
		// Draw clock ticks
		g2.setStroke(new BasicStroke(1));
		for (int k = 0; k < 60; k++) {
			th = (15-k)*Math.PI/30;
			x = LENGTH/2 + Math.cos(th) * (LENGTH/2 - m*1.5);
			y = LENGTH/2 - Math.sin(th) * (LENGTH/2 - m*1.5);
			g2.drawOval((int)x - 1, (int)y - 1, 2, 2);
		}
		
		/* Draw Day of Month Clock */
		int xc, yc;
		double p;
		// Day of the Month
		xc = LENGTH/8*3;
		yc = LENGTH/8*3;
		g2.setColor(Luminescent.GITD2);
		g2.setStroke(new BasicStroke(3));
		g2.fillOval(xc - 4, yc - 4, 8, 8);
		g2.drawOval(xc - LENGTH/8, yc - LENGTH/8, LENGTH/4, LENGTH/4);
		int days = DaysOfMonth.numOfDays(timedate.getMonth(), timedate.getYear());
		for (int k = 0; k < days; k++) {
			th = Math.PI/2 - k*Math.PI/31*2;
			x = xc + Math.cos(th) * (LENGTH/8 - m/4);
			y = xc - Math.sin(th) * (LENGTH/8 - m/4);
			if (k == 0)
				g2.drawOval((int)x - 2, (int)y - 2, 4, 4);
			else
				g2.drawOval((int)x - 1, (int)y - 1, 2, 2);
		}
		// Day Hand
		g2.setStroke(new BasicStroke(1));
		th = Math.PI/2 - (timedate.getDay() - 1)*Math.PI/31*2;
		x = xc + Math.cos(th) * (LENGTH/8 - m/2);
		y = xc - Math.sin(th) * (LENGTH/8 - m/2);
		g2.drawLine(xc, yc, (int)x, (int)y);
		// Day digit
		g2.drawString(String.format("[%02d]", timedate.getDay()), xc - 12, yc + 24);

		/* Draw Month of Year Clock */
		xc = LENGTH/8*5;
		yc = LENGTH/8*5;
		g2.setColor(Luminescent.GITD2);
		g2.setStroke(new BasicStroke(3));
		g2.fillOval(xc - 4, yc - 4, 8, 8);
		g2.drawOval(xc - LENGTH/8, yc - LENGTH/8, LENGTH/4, LENGTH/4);
		for (int k = 0; k < 12; k++) {
			th = (3-k)*Math.PI/6;
			x = xc + Math.cos(th) * (LENGTH/8 - m/4);
			y = xc - Math.sin(th) * (LENGTH/8 - m/4);
			g2.drawOval((int)x - 1, (int)y - 1, 2, 2);
		}
		// Month Hand
		g2.setStroke(new BasicStroke(1));
		th = Math.PI/2 - (timedate.getMonth() - 1)*Math.PI/6;
		th -= ((float)timedate.getDay()/days)*Math.PI/6;
		x = xc + Math.cos(th) * (LENGTH/8 - m*0.75);
		y = xc - Math.sin(th) * (LENGTH/8 - m*0.75);
		g2.drawLine(xc, yc, (int)x, (int)y);
		// Month digit
		g2.drawString(String.format("[%02d]", timedate.getMonth()), xc - 12, yc + 24);
		
		/* Draw clock hands, let p denote position (pi/6 from position pi/2) */
		x = LENGTH/2;
		y = LENGTH/2;
		g2.setColor(Luminescent.GITD);
		g2.fillOval((int)x - 6, (int) y - 6, 12, 12);
		// Seconds
		p = timedate.getSecond() / 5f;
		th = (3-p)*Math.PI/6;
		g2.setStroke(new BasicStroke(1));
		x = LENGTH/2 + Math.cos(th) * (LENGTH/2 - m*1.25);
		y = LENGTH/2 - Math.sin(th) * (LENGTH/2 - m*1.25);
		g2.drawLine(LENGTH/2, LENGTH/2, (int)x, (int)y);
		// Minutes: 60s equal 1/60 of a step, 1 step is 5m
		p = p / 60f + timedate.getMinute() / 5f;
		th = (3-p)*Math.PI/6;
		g2.setStroke(new BasicStroke(6));
		x = LENGTH/2 + Math.cos(th) * (LENGTH/2 - m*2);
		y = LENGTH/2 - Math.sin(th) * (LENGTH/2 - m*2);
		g2.drawLine(LENGTH/2, LENGTH/2, (int)x, (int)y);
		// Hours: 60 minutes equal 1 step
		p = p / 12f + timedate.getHour();
		th = (3-p)*Math.PI/6;
		g2.setStroke(new BasicStroke(6));
		x = LENGTH/2 + Math.cos(th) * (LENGTH/2 - m*3);
		y = LENGTH/2 - Math.sin(th) * (LENGTH/2 - m*3);
		g2.drawLine(LENGTH/2, LENGTH/2, (int)x, (int)y);
		
		/* Year and Signature */
		g2.setFont(Luminescent.LARGE_FONT);
		g2.drawString(String.format("%4d", timedate.getYear()), m/2, m/2 + 6);
		g2.drawString("Endgame Of DST", m/2, m/2 + 24);
	}
	
	public void saveImage() {
	    try {
	    	String str = String.format("Endgame_Of_DST_%02d%02d.%02d", 
	    			timedate.getHour(),
	    			timedate.getMinute(),
	    			timedate.getSecond());
			BufferedImage bi = new BufferedImage
					(ClockFacePanel.LENGTH, ClockFacePanel.LENGTH, 
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bi.createGraphics();
			execute(g2);
			ImageIO.write(bi, "gif", new File("Saved_Images/" + str + ".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
