package visualso.controller;

// import visualso.listener.BackListener;
import visualso.listener.ExitListener;
import visualso.listener.HelpAboutListener;
import visualso.listener.RecordListener;
import visualso.listener.VoiceSortListener;

import java.awt.event.ActionListener;

public class BaseController {
	public HelpAboutListener helpButtonClicked(String name,String helpInfo) {
		return new HelpAboutListener(name,helpInfo);
	}
	public ExitListener exitButtonClicked() {
		return new ExitListener();
	}

	public ActionListener recordButtonClicked() {
		return new RecordListener();
	}

	public ActionListener sortButtonClicked() {
		return new VoiceSortListener();
	}

}
