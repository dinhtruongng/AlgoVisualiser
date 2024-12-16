package visualso.component;

import javax.swing.JPanel;

public class Visualizer extends JPanel{
	private int[] array;
	public Visualizer(int[] array) {
		super();
		this.array = array;
	}
	public int[] getArray() {
		return this.array;
	}
}

