package visualso.component;

import java.awt.Color;

import javax.swing.Icon;

import visualso.controller.SortingController;

public class MyControlButton extends MyButton{
	public MyControlButton(int x, int y, Color color,Icon icon,String id, SortingController controller) {
		super(x,y,color);
		setIcon(icon);
		setId(id);
		addActionListener(controller.controlButtonCLicked());
	}
}
