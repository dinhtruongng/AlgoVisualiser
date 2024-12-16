package visualso.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import visualso.view.HomeScreen;

public class BackListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new HomeScreen().setVisible(true);
		SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();	
	}
}