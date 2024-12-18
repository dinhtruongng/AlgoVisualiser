package visualso.controller;

import visualso.listener.RecordListener;
import visualso.listener.SortListener;
import visualso.listener.VoiceSortListener;

import java.awt.event.ActionListener;

public class HomeController{
	public SortListener sortSelection(String name) {
		return new SortListener(name);
	}

	public ActionListener recordButtonClicked() {
		return new RecordListener();
	}

	public ActionListener sortButtonClicked() {
		return new VoiceSortListener();
	}
}
