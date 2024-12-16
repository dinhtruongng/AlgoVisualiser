package visualso.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

public class MyTextField extends JTextField {
	public MyTextField(String placeholder) {
		super();
		setCaretColor(Color.BLACK);
		setBackground(Color.BLACK);
		if (getText().equals("")) {
			setText(placeholder);
			setForeground(Color.GRAY);
			setFont(new Font(getFont().getFamily(),Font.ITALIC,getFont().getSize()));
		}
		else {
			setForeground(Color.WHITE);
		}
		setPreferredSize(new Dimension(228,28));
		setVisible(false);
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals("")) {
					setCaretColor(Color.BLACK);
					setForeground(Color.GRAY);
					setText(placeholder);
					setFont(new Font(getFont().getFamily(),Font.ITALIC,getFont().getSize()));
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				setCaretColor(Color.WHITE);
				setForeground(Color.WHITE);
				setFont(new Font(getFont().getFamily(),Font.PLAIN,getFont().getSize()));
				if (getText().equals(placeholder)) {
					setText("");
				}
			}
		});
	}
}