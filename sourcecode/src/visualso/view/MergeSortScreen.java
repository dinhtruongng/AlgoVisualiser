package visualso.view;

 
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import visualso.component.Visualizer;
import visualso.sorting.MergeSort;
import visualso.util.ArrayUtils;
import visualso.util.ColorUtils;

@SuppressWarnings("serial")
public class MergeSortScreen extends SortingScreen{
	public MergeSortScreen() {
		super();
		createName("MERGE SORT");
		sortingController.setModel(new MergeSort(mainArray));
	}


	@Override
	public Visualizer sub(int[] array) {
		Visualizer sub =  new Visualizer(array) {
			int x = 0;
			int y = 0;
			int width;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/array.length;
				x = (getWidth()- ArrayUtils.min(width,60+padding)*array.length)/2;
				for (int i:array) {
					g.setColor(ColorUtils.MY_PINK);
				    g.fillRect(x, y-(int)(i*unitHeight)+ getHeight(),ArrayUtils.min(width-padding,60),(int)(i*unitHeight));
				    x += ArrayUtils.min(width,60+padding) ;
				}
				x = 0;
		    }
	     };
		sub.setBounds(45, 300, getWidth()-200, 250);
		sub.setBackground(new Color(0,0,0,0));
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
				if(step[0] >= 0) {
				    mainIndex = step[0];
					g.setColor(ColorUtils.MY_GREEN);
				    g.fillRect(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -(int)(main.getArray()[mainIndex]*unitHeight)
				    		   + main.getHeight(),ArrayUtils.min(width-padding,60),(int)(main.getArray()[mainIndex]*unitHeight));
				}
				if (step[1] >= 0) {
					subIndex = step[1];
					g.setColor(Color.red);
				    g.fillRect(subIndex*ArrayUtils.min(width,60+padding)+(sub.getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -(int)(sub.getArray()[subIndex]*unitHeight)
				    		   + sub.getHeight()+270,ArrayUtils.min(width-padding,60),(int)(sub.getArray()[subIndex]*unitHeight));
				}	
			}
		};
		animation.setBounds(45, 30,getWidth()-200, 520);
		animation.setBackground(new Color(0,0,0,0));
		return animation;
	}
	@Override
	public void updateMainArray(int[] array) {
		MergeSortScreen a = new MergeSortScreen(){
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
		return ColorUtils.MY_PINK;
	}
	public int getMaxValue() {
		return 1000;
	}
	public String newHelpInfo() {
		 return "\nMerge Sort\n\n"
				 + "Merge sort is one of the most efficient sorting algorithms. "
				 + "It is based on the divide-and-conquer strategy - breaks down a big problem into smaller,"
				 + "more manageable pieces that look similar to the initial problem. "
				 + "It then solves these subproblems recursively and puts their solutions "
				 + "together to solve the original problem. Merge sort continuously cuts down a list into multiple sublists until each has only one item, then merges those sublists into a sorted list.\n\n"
				 + "Consider the Array has size n \n\n"
				 + "Time Complexity:\n\n"
				 + "     Worst Case: O(n*log(n))\n\n"
				 + "     Best Case: O(n*log(n))\n\n"
				 + "     Average case: O(n*log(n))\n\n"
				 + "Space Complexity: O(n)\n\n"
				 + "The array size in Merge Sort used for visualization has a max size of 100 elements.\n"
				 + "A valid array in Merge Sort only has the max value is 1000, and it is non-negative numbers.";
	}
}
