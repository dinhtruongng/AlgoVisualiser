package visualso.controller;

// import visualso.listener.BackListener;
import visualso.listener.ExitListener;
import visualso.listener.HelpAboutListener;

public class BaseController {
	public HelpAboutListener helpButtonClicked(String name,String helpInfo) {
		return new HelpAboutListener(name,helpInfo);
	}
	public ExitListener exitButtonClicked() {
		return new ExitListener();
	}
}
