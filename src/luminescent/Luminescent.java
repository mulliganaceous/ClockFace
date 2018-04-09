package luminescent;

import java.awt.Color;
import java.awt.Font;

/**The Luminescent interface sets all the GUI style to a luminescent
 * glow-in-the dark style (technically phosphorescence.
 * @author !MULLIGANACEOUS!
 */
public interface Luminescent {
	Color GITD = new Color(51,255,153);
	Color GITD2 = new Color(0,204,51);
	public static final Font LARGE_FONT = new Font("Century Gothic", 1, 16);
	public static final Font SMALL_FONT = new Font("Century Gothic", 0, 12);
}
