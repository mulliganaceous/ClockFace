package luminescent;

import java.awt.Dimension;
import javax.swing.JLabel;

public class LuminescentLabel extends JLabel implements Luminescent {
	private static final long serialVersionUID = 200L;
	public LuminescentLabel(String str) {
		super(str);
		this.setForeground(GITD);
		this.setFont(LARGE_FONT);
		this.setPreferredSize(new Dimension(144,24));
	}
	
	public LuminescentLabel(String str, int len) {
		super(str);
		this.setForeground(GITD);
		this.setFont(LARGE_FONT);
		if (len > 0)
			this.setPreferredSize(new Dimension(len,24));
	}
}
