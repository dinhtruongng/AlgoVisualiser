package visualso.listener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import visualso.controller.SortingController;

public class DemonstrateListener extends MyActionListener{
	public DemonstrateListener(SortingController controller) {
		super(controller);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (((JButton)e.getSource()).getText().equals("<")) {
			((JButton)e.getSource()).setText(">");
			controller.getView().getDemonstratePane().setVisible(true);
			controller.getView().getDemonstratePseudoCode().setVisible(true);
		}
		else {
			((JButton )e.getSource()).setText("<");
			controller.getView().getDemonstratePane().setVisible(false);
			controller.getView().getDemonstratePseudoCode().setVisible(false);
		}
	}
}
