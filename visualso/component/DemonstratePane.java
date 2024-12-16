package visualso.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class DemonstratePane extends JTextArea{
	
	public DemonstratePane(int x, int y,Color color, String sortInfo) {
		setOpaque(true);
		setBackground(color);
		setForeground(Color.BLACK);
		setText(sortInfo);
		setEditable(false);
		setFocusable(true);
		setLineWrap(true);
		setWrapStyleWord(true);
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		setPreferredSize(new Dimension(x,y));
		setVisible(false);
	}
}
