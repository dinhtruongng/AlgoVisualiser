package visualso.listener;

import java.awt.event.ActionEvent; 

import javax.swing.Timer;

import visualso.controller.SortingController;

public class TimerListener extends MyActionListener{
	public TimerListener(SortingController controller) {
		super(controller);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (controller.isSorting() && controller.getCurStep() <controller.getStep() ) {				
			controller.getView().getProcessSlider().setValue(controller.getCurStep()+1);	
		   ((Timer)e.getSource()).setDelay(1000 - controller.getSpeed()*10);
		}
	}
}

