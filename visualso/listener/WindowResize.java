package visualso.listener;


import java.awt.event.ComponentEvent;

import visualso.controller.SortingController;

public class WindowResize extends MyComponentAdapter{
	public WindowResize(SortingController controller) {
		super(controller);
	}
    @Override
    public void componentResized( ComponentEvent e ) {
    	controller.getView().getBtnSort().setBounds(3, controller.getView().getHeight()-175 , 150, 32);
    	controller.getView().getBtnCreate().setBounds(3, controller.getView().getHeight() -208, 150, 33);
    	controller.getView().getBtnRandom().setBounds(156, controller.getView().getHeight()-205, 78,28);
    	controller.getView().getA().setBounds(236, controller.getView().getHeight()-205, 28,28);
    	controller.getView().getInputArrayField().setBounds(266, controller.getView().getHeight()-205, 228,28);
    	controller.getView().getBtnGo().setBounds(496, controller.getView().getHeight()-205, 50,28);
    	controller.getView().getDemonstratePane().setBounds(controller.getView().getWidth()-490,  controller.getView().getHeight() - 390,380 , 45);
    	controller.getView().getDemonstratePseudoCode().setBounds(controller.getView().getWidth()-490,  controller.getView().getHeight() - 340,380 , 200);
    	controller.getView().getAnimation().setBounds(45, 30, controller.getView().getWidth()-200, 520);
    	controller.getView().getContainer1().setBounds(45, 30, controller.getView().getWidth()-200, 522);
    	controller.getView().getMain().setBounds(0, 0, controller.getView().getWidth()-200, 250);
    	controller.getView().getSub().setBounds(0, 270, controller.getView().getWidth()-200, 250);
    	controller.getView().getErrorLabel().setBounds((controller.getView().getWidth()-450)/2, 5, 300, 20);
    }
}
