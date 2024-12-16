package visualso.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import visualso.component.Visualizer;
import visualso.sorting.RadixSort;
import visualso.util.ArrayUtils;
import visualso.util.ColorUtils;

@SuppressWarnings("serial")
public class RadixSortScreen extends SortingScreen {
	public RadixSortScreen() {
		super();
		createName("RADIX SORT");
		sortingController.setModel(new RadixSort(mainArray));
	}
	
	@Override
	public Visualizer sub(int[] array) {
		Visualizer sub =  new Visualizer(array) {
			int x =30;
			int counter = 0;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i: array) {
					Font oldFont = g.getFont(); 
					g.setColor(ColorUtils.MY_YELLOW);
				    g.fillRect(x, getHeight()-60,60,30);
				    g.setColor(Color.BLACK);
				    g.drawLine(x,getHeight()-30 , x+60,getHeight()-30);
				    g.drawString(Integer.toString(counter), x+30, getHeight()-10);
				    g.setColor(Color.WHITE);
				    g.setFont(new Font("Sans", Font.BOLD, 17));
				    g.drawString(Integer.toString(i), x+28, getHeight()-40);
				    g.setFont(oldFont);
				    x += 60+padding ;
				    counter++;
				}
				x = 30;
				counter = 0;
		    }
	     };
	    sub.setBounds(45, 300, getWidth()-200, 250);
	    return sub;
	}
	@Override
	public JPanel animation(Visualizer main, Visualizer sub, int[] step) {
		int width =(int) main.getWidth()/mainArray.length;

		JPanel animation =  new JPanel() {
			int mainIndex;
			int subIndex;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(step[0] >= 0&& step[1]>=0) {
				    mainIndex =step[0];
				    subIndex = step[1];
					g.setColor(ColorUtils.MY_GREEN);
				    g.fillRect(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -(int)(main.getArray()[mainIndex]*unitHeight)
				    		   + main.getHeight(),ArrayUtils.min(width-padding,60),(int)(main.getArray()[mainIndex]*unitHeight));
				    g.setColor(Color.red);
				    g.fillRect(subIndex*(60+padding)+30,getHeight()-60, 60,30);
				    g.setColor(Color.WHITE);
				    g.setFont(new Font("Sans", Font.BOLD, 17));
				    g.drawString(Integer.toString(sub.getArray()[subIndex]),subIndex*(60+padding)+58, getHeight()-40);
				}
				
			}
		};
		animation.setBounds(45, 30,getWidth()-200, 520);
		animation.setBackground(new Color(0,0,0,0));
		return animation;
	}
	
	@Override
	public void updateMainArray(int[] array) {
		RadixSortScreen a = new RadixSortScreen() {
			@Override
			public void setMainArray(int[] arr) {
				this.mainArray = array;
			}		
		};
		if (this.getExtendedState()==MAXIMIZED_BOTH) {
			a.setExtendedState(MAXIMIZED_BOTH);
		}
		else {
		    a.setSize(this.getWidth(),this.getHeight()); }
		dispose();
	}	
	
	@Override
	public Color getColor() {
		return ColorUtils.MY_YELLOW;
	}
	@Override
	public int getMaxValue() {
		return 1000;
	}
	public String newHelpInfo() {
		//TODO modify the text 
		return "\nRadix Sort\n\n"
			 + "Radix sort algorithm is a non-comparative sorting algorithm in computer "
			 + "science. It avoids comparison by creating and categorizing elements based "
			 + "on their radix. For elements with more than one significant digit, it "
			 + "repeats the bucketing process for each digit while preserving the previous "
			 + "step's ordering until all digits have been considered.\n\n"
			 + "Consider the Array has size n and the number of digits in the largest number d in base b\n\n"
			 + "Time Complexity:\n\n"
			 + "     Worst Case: O(n*d)\n\n"
			 + "     Best Case: O(n*d)\n\n"
			 + "     Average case: O(n*d)\n\n"
			 + "Space Complexity: O(n+d)\n\n"
			 + "The array size in Radix Sort used for visualization has a max size of 100 elements.\n"
			 + "A valid array in Radix Sort only has the max value is 1000, and it is non-negative numbers.";
	}
}
