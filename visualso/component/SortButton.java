package visualso.component;

import java.awt.Color;
// import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

// import javax.swing.Icon;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import visualso.controller.HomeController;
import visualso.view.HomeScreen;

public class SortButton extends JButton{
	public SortButton(String name, HomeScreen window,HomeController homeController) {
		super(name);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setVerticalTextPosition(SwingConstants.CENTER);
		setVerticalAlignment(CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		
		// Icon SortIcon= new ImageIcon(new ImageIcon(window.getDirectory()+String.join("",name.split(" "))+"_icon1.png").getImage().getScaledInstance(330, 240, Image.SCALE_SMOOTH));
		// Icon preSortIcon= new ImageIcon(new ImageIcon(window.getDirectory()+String.join("",name.split(" "))+"_icon.png").getImage().getScaledInstance(330, 240, Image.SCALE_SMOOTH));
		// setRolloverIcon(preSortIcon);
		// setIcon(SortIcon);
		setFocusable(false);
		addActionListener(homeController.sortSelection(name));
		addComponentListener(new ComponentAdapter() {  
            // @Override
            // public void componentResized(ComponentEvent e) {
            //     JButton btn = (JButton) e.getComponent();
            //     btn.setRolloverIcon(new ImageIcon(((ImageIcon)preSortIcon).getImage().getScaledInstance(btn.getWidth(),btn.getHeight()-45, java.awt.Image.SCALE_SMOOTH)));
            //     btn.setIcon(new ImageIcon(((ImageIcon)SortIcon).getImage().getScaledInstance(btn.getWidth(),btn.getHeight()-45, java.awt.Image.SCALE_SMOOTH)));
            // }
            
        });
	}
}