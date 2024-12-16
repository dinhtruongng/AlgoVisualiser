package visualso.controller;

import visualso.listener.SortListener;

public class HomeController{
	public SortListener sortSelection(String name) {
		return new SortListener(name);
	}
}
