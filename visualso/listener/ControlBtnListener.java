package visualso.listener;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import visualso.component.MyButton;
import visualso.controller.SortingController;

public class ControlBtnListener extends MyActionListener{
	public ControlBtnListener(SortingController controller) {
		super(controller);
	}
    private Icon pauseIcon= new ImageIcon(new ImageIcon(controller.getView().getDirectory()+"pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	private Icon playIcon = new ImageIcon(new ImageIcon(controller.getView().getDirectory()+"play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(((MyButton )e.getSource()).getId()) {
		case "Play":
			if (controller.isSorting()) {
				if (!controller.isPlay()) {
					((JButton)e.getSource()).setIcon(pauseIcon);
					controller.setPlay(true);
					controller.getView().getTimer().start();
				}
				else {
					((JButton)e.getSource()).setIcon(playIcon);
					controller.setPlay(false);
					controller.getView().getTimer().stop();
				}
			}
			break;
		case "Forward":
			if (controller.isSorting()) {
				controller.getView().getProcessSlider().setValue(controller.getCurStep()+1);
		    }
			break;
		case "Backward":
			 if (controller.isSorting())
				 controller.getView().getProcessSlider().setValue(controller.getCurStep()-1);
			break;
		case "End":
			if (controller.isSorting()) {
				controller.getView().getProcessSlider().setValue(controller.getStep());
				controller.getView().getVisualizer().remove(controller.getView().getAnimation());
				}
			break;
		case "Start":
			if (controller.isSorting()) {
				controller.getView().getProcessSlider().setValue(0);
				if(!controller.isPlay()) {
					controller.getView().getVisualizer().remove(controller.getView().getAnimation());
				}
			}
			break;
		}
	}
}
