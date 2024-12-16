package visualso.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class MySlider extends JSlider{
	public MySlider(int orientation, int min, int max, int value,int width, int height,ChangeListener change) {
		super(orientation, min,max,value);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width,height));
		addChangeListener(change);
		//https://stackoverflow.com/questions/7095428/jslider-clicking-makes-the-dot-go-towards-that-direction
	    addMouseListener(new MouseAdapter() {
	          @Override
	          public void mousePressed(MouseEvent e) {
	             Point p = e.getPoint();
	             double percent = p.x / ((double) getWidth());
	             int range = getMaximum() - getMinimum();
	             double newVal = range * percent;
	             int result = (int)(getMinimum() + newVal);
	             setValue(result);
	          }
	       });
	}
}

