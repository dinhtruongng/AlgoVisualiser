package visualso.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyLabel extends JLabel{
	public MyLabel(String name) {
		super(name);
		setBackground(new Color(0,0,0,0));
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(28,28));
		setVisible(false);
	}
}
