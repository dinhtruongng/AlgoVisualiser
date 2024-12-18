package visualso.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.WindowEvent;
// import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelpAboutListener implements ActionListener{
	private String helpInfo;
	private String name;
	public HelpAboutListener(String name,String helpInfo ) {
		super();
		this.name = name;
		this.helpInfo = helpInfo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame helpFrame = new JFrame(name);
		JTextArea helpContent = new JTextArea(helpInfo);
		helpContent.setOpaque(true);
		helpContent.setBackground(Color.black);
		helpContent.setForeground(Color.WHITE);
		helpContent.setEditable(false);
		helpContent.setFocusable(false);
		helpContent.setLineWrap(true);
		helpContent.setWrapStyleWord(true);
		helpContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		helpFrame.setSize(600,580);
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpFrame.setLocationRelativeTo(null);
		helpFrame.setResizable(false);
		helpFrame.add(helpContent);
		helpFrame.setVisible(true);
		// helpFrame.addWindowFocusListener(new WindowFocusListener() {
		// 	@Override
		// 	public void windowGainedFocus(WindowEvent e) {
		// 	}
		// 	@Override
		// 	public void windowLostFocus(WindowEvent a) {
		// 		((JFrame)a.getSource()).dispose();
		// 	}
		// });
	}
}
