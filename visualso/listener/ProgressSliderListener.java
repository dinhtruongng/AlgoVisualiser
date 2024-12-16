package visualso.listener;

import javax.swing.JLayeredPane; 
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import visualso.controller.SortingController;

public class ProgressSliderListener extends MyChangeListener{
	public ProgressSliderListener(SortingController controller) {
		super(controller);
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if( controller.getGuideStates() != null && controller.isSorting()) {
      controller.setCurStep(controller.getView().getProcessSlider().getValue());
			controller.getView().getDemonstratePane().setText(controller.getGuideStates()[controller.getCurStep()]);
        
			controller.getView().getVisualizer().remove(controller.getView().getAnimation());
			controller.getView().getVisualizer().remove(controller.getView().getContainer1());
	    
	    
			controller.getView().setMain(controller.getView().main(controller.getArrayStates()[controller.getCurStep()], controller.getView().getColor()));
			controller.getView().getMain().setBounds(0, 0, controller.getView().getWidth()-200, 250);
        
			controller.getView().setSub(controller.getView().sub(controller.getTempStates()[controller.getCurStep()]));
			controller.getView().getSub().setBounds(0, 270, controller.getView().getWidth()-200, 250);
	    
			controller.getView().setContainer(new JPanel(null));
			controller.getView().getContainer1().setBounds(45, 30, controller.getView().getWidth()-200, 522);
	    
			controller.getView().setAnimation(controller.getView().animation(controller.getView().getMain(), controller.getView().getSub()
	    		, controller.getPointerStates()[controller.getCurStep()]));
			controller.getView().getAnimation().setBounds(45, 30, controller.getView().getWidth()-200, 520);
	    
			controller.getView().getVisualizer().add(controller.getView().getAnimation(), JLayeredPane.PALETTE_LAYER);
			controller.getView().getContainer1().add(controller.getView().getMain());
			controller.getView().getContainer1().add(controller.getView().getSub());
			controller.getView().getVisualizer().add(controller.getView().getContainer1(), JLayeredPane.DEFAULT_LAYER);
		}
	}
}
