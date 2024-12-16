package visualso.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MyButton extends JButton{
	String id;
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public MyButton(int x, int y, Color color) {
		super();
		setModel(new FixedStateButtonModel());
		setRolloverEnabled(false);
		setMargin(new Insets(0,0,0,0));	
		setFocusable(false);
		setBackground(color);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setPreferredSize(new Dimension(x,y));			
	}
}
