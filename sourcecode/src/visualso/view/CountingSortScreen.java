package visualso.view;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import visualso.component.Visualizer;
import visualso.sorting.CountingSort;
import visualso.util.ArrayUtils;
import visualso.util.ColorUtils;

@SuppressWarnings("serial")
public class CountingSortScreen extends SortingScreen {
	public CountingSortScreen() {
		super();
		createName("COUNTING SORT");
		sortingController.setModel(new CountingSort(mainArray));
	}

	@Override
	public Visualizer sub(int[] array) {
		Visualizer sub =  new Visualizer(array) {
			int x =30;
			int width;
			int counter = 0;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) (getWidth()-30)/array.length;
				for (int i: array) {
					Font oldFont = g.getFont(); 
					g.setColor(ColorUtils.MY_ORANGE);
				    g.fillRect(x, getHeight()-60,ArrayUtils.min(width-padding,60),30);
				    g.setColor(Color.BLACK);
				    g.drawLine(x,getHeight()-30 , x+ArrayUtils.min(width-padding,60),getHeight()-30);
				    g.drawString(Integer.toString(counter), x+ArrayUtils.min(width-padding,60)/2, getHeight()-10);
				    g.setColor(Color.WHITE);
				    g.setFont(new Font("Sans", Font.BOLD, 17));
				    g.drawString(Integer.toString(i), x+ArrayUtils.min(width-padding,60)/2 -2, getHeight()-40);
				    g.setFont(oldFont);
				    x += ArrayUtils.min(width-padding,60)+padding ;
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
		int mainWidth =(int) main.getWidth()/mainArray.length;
		int subWidth = (int) (sub.getWidth()-30)/sub.getArray().length;
		
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
				    g.fillRect(mainIndex*ArrayUtils.min(mainWidth,60+padding)+(main.getWidth()
				    		   -ArrayUtils.min(mainWidth,60+padding)*mainArray.length)/2,
				    		   -(int)(main.getArray()[mainIndex]*unitHeight)
				    		   + main.getHeight(),ArrayUtils.min(mainWidth-padding,60),(int)(main.getArray()[mainIndex]*unitHeight));
				    g.setColor(Color.red);
				    g.fillRect(subIndex*ArrayUtils.min(subWidth,60+padding)+30,getHeight()-60, ArrayUtils.min(subWidth-padding,60),30);
				    g.setColor(Color.WHITE);
				    g.setFont(new Font("Sans", Font.BOLD, 17));
				    g.drawString(Integer.toString(sub.getArray()[subIndex]),(subIndex)*ArrayUtils.min(subWidth,60+padding)+26+ArrayUtils.min(subWidth,60+padding)/2, getHeight()-40);
				}
				
			}
		};
		animation.setBounds(45, 30,getWidth()-200, 520);
		animation.setBackground(new Color(0,0,0,0));
		return animation;
	}
	@Override
	public void updateMainArray(int[] array) {
		CountingSortScreen a = new CountingSortScreen(){
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
		return ColorUtils.MY_ORANGE;
	}
	public int getMaxValue() {
		return 20;
	}
	public String newHelpInfo() {
		 return "\nCounting Sort\n\n"
				 + "Counting sort is a sorting algorithm that sorts the elements of"
				 + " an array by counting the number of occurrences of each unique "
				 + "element in the array. The count is stored in an auxiliary "
				 + "array and the sorting is done by mapping the count as an index "
				 + "of the auxiliary array.\n\n"
				 + "Consider the Array n is the number of elements in the input array and k is the range of input. \n\n"
				 + "Time Complexity:\n\n"
				 + "     Worst Case: O(n+k)\n\n"
				 + "     Best Case: O(n)\n\n"
				 + "     Average case: O(n+k)\n\n"
				 + "Space Complexity: O(k)\n\n"
				 + "The array size in Counting Sort used for visualization has a max size of 100 elements.\n"
				 + "A valid array in Counting Sort only has the max value is 20, and it is non-negative numbers.";
	}
}
