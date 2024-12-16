package visualso.listener;

import javax.swing.event.ChangeEvent; 
import javax.swing.event.ChangeListener;

import visualso.controller.SortingController;

public abstract class MyChangeListener implements ChangeListener {
	SortingController controller;
	public MyChangeListener(SortingController controller) {
		this.controller = controller;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		
	}

}
