package luminescent;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class LuminescentPanel extends JPanel implements Luminescent {
	private static final long serialVersionUID = 209;

	public LuminescentPanel() {
		super();
		this.setBorder(BorderFactory.createLineBorder(Luminescent.GITD));
		this.setBackground(Color.BLACK);
		this.setForeground(Luminescent.GITD);
	}
}
