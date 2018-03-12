package luminescent;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class LuminescentButton extends JButton implements Luminescent {
	private static final long serialVersionUID = 202;

	public LuminescentButton(String str) {
		super(str);
		this.setForeground(GITD);
		this.setFont(LARGE_FONT);
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(GITD, 6));
		this.setPreferredSize(new Dimension(128,64));
	}
}
