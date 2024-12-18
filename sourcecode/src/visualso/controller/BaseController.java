package visualso.controller;

// import visualso.listener.BackListener;
import visualso.listener.ExitListener;
import visualso.listener.HelpAboutListener;

import visualso.listener.helpLLMtest;

import visualso.listener.RecordListener;
import visualso.listener.VoiceSortListener;

import java.awt.event.ActionListener;


public class BaseController {
	public helpLLMtest helpButtonClicked() {
		return new helpLLMtest();
	}

	public HelpAboutListener aboutButtonClicked(String name, String helpInfo) {
		return new HelpAboutListener(name, helpInfo);
	}

	public ExitListener exitButtonClicked() {
		return new ExitListener();
	}

}
