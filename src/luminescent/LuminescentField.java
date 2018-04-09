package luminescent;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class LuminescentField extends JTextField implements Luminescent {
	private static final long serialVersionUID = 203;

	public LuminescentField(int len) {
		super(len);
		// Style
		this.setFont(SMALL_FONT);
		this.setBackground(GITD);
		this.setForeground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(GITD));
	}
}
