package visualso.listener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import visualso.controller.SortingController;
import visualso.exception.DataTypeException;
import visualso.exception.NullException;
import visualso.exception.OutOfBoundException;
import visualso.util.DataUtils;

public class LeftSideBarBtnListener extends MyActionListener{
	private boolean sorted = false;
	
	public LeftSideBarBtnListener(SortingController controller) {
		super(controller);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(((JButton)e.getSource()).getText()) {
		case ">":
			((JButton)e.getSource()).setText("<");
			controller.getView().getBtnCreate().setVisible(true);
			controller.getView().getBtnSort().setVisible(true);
			break;
		case "<":
			((JButton)e.getSource()).setText(">");
			controller.getView().getBtnCreate().setVisible(false);
			controller.getView().getBtnSort().setVisible(false);
			controller.getView().getBtnRandom().setVisible(false);
			controller.getView().getA().setVisible(false);
			controller.getView().getInputArrayField().setVisible(false);
			controller.getView().getBtnGo().setVisible(false);
			break;
		case "Create(A)":
			controller.getView().getBtnRandom().setVisible(true);
			controller.getView().getA().setVisible(true);
			controller.getView().getInputArrayField().setVisible(true);
			controller.getView().getBtnGo().setVisible(true);
			break;
		case "Random":
			controller.getView().updateMainArray(DataUtils.randomArray(SortingController.MAX_LENGTH, controller.getView().getMaxValue()));
			//controller.setSorting(false);
			break;
		case "Go":
			String arr = controller.getView().getInputArrayField().getText();
			if (arr.equals(controller.getView().placeHolder)) {
				arr = "";
			}
			//controller.setSorting(false);
            try {
            	controller.getView().updateMainArray(DataUtils.parseString(arr, SortingController.MAX_LENGTH, controller.getView().getMaxValue()));
			
			} catch (DataTypeException e1) {
				controller.getView().getErrorLabel().setText(e1.getMessage());	
			} catch (NullException e2) {
				controller.getView().getErrorLabel().setText(e2.getMessage());
			} catch (OutOfBoundException e3) {
				controller.getView().getErrorLabel().setText(e3.getMessage());
			} catch (Exception e4) {
				controller.getView().getErrorLabel().setText(e4.getMessage());

			}finally {
				controller.getView().getErrorLabel().setVisible(true);
				controller.getView().repaint();
			}
			break;
		case "Sort":
			if (sorted == false) {
				sorted = true;
				controller.setSorting(true);
				controller.getView().getErrorLabel().setVisible(false);
				controller.getModel().sort();
				controller.getView().getProcessSlider().setMaximum(controller.getStep());
				if (controller.isPlay()) {
					controller.getView().getTimer().stop();
					controller.getView().getTimer().start();}	
			}
			break;
		}
	}
}
