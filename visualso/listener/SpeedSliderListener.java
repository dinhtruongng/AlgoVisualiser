package visualso.listener;

import javax.swing.JLabel; 
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import visualso.controller.SortingController;

public class SpeedSliderListener extends MyChangeListener{
	private JLabel count;
	public SpeedSliderListener(JLabel count, SortingController controller) {
		super(controller);
		this.count = count;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		   controller.setSpeed(((JSlider) e.getSource()).getValue());
		   count.setText(Integer.toString(controller.getSpeed()));
		   if (controller.isSorting()) {
		       controller.getView().getTimer().stop();
		       controller.getView().getTimer().setDelay(1000-controller.getSpeed()*10);
		       controller.getView().getTimer().start();
		       if (!controller.isPlay()) {
		    	   controller.getView().getTimer().stop();
		       }
		   }
	}
}
