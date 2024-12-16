package visualso.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ExitListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int option= JOptionPane.showConfirmDialog(null, "Do you really want to exit ?","Notification",JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
		}
	}
}
