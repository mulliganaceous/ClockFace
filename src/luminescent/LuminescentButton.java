package luminescent;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class LuminescentButton extends JButton implements Luminescent {
	private static final long serialVersionUID = 202;

	public LuminescentButton(String str) {
		super(str);
		this.turnOff();
		this.setFont(LARGE_FONT);
		this.setBorder(BorderFactory.createLineBorder(GITD, 6));
		this.setPreferredSize(new Dimension(128,64));
	}
	
	public void turnOn() {
		this.setForeground(Color.BLACK);
		this.setBackground(GITD2);
	}
	
	public void turnOff() {
		this.setForeground(GITD);
		this.setBackground(Color.BLACK);
	}
}
