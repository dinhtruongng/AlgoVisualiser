package visualso.listener;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import visualso.controller.SortingController;

public abstract class MyActionListener implements ActionListener{
	SortingController controller;
	public MyActionListener(SortingController controller) {
		super();
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
