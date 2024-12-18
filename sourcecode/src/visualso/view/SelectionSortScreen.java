package visualso.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import visualso.component.Visualizer;
import visualso.sorting.SelectionSort;
import visualso.util.ArrayUtils;
import visualso.util.ColorUtils;

@SuppressWarnings("serial")
public class SelectionSortScreen extends SortingScreen {
    public SelectionSortScreen() {
        super();
        demonstratePseudoCode.setText("repeat (numOfElements - 1) times\n"
        		+ "    set the first unsorted element as the minimum\n"
        		+ "    for each of the unsorted elements\n"
        		+ "        if element < currentMinimum\n"
        		+ "            set element as new minimum\n"
        		+ "    swap minimum with the first unsorted position");
		demonstratePseudoCode.setFont(new Font("Sora", Font.ITALIC,14));                
        createName("SELECTION SORT");
        sortingController.setModel(new SelectionSort(mainArray));
    }

    @Override
    public Visualizer sub(int[] array) {
        Visualizer sub = new Visualizer(array) {
            int x = 0;
            int y = 0;
            int width;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                width = (int) getWidth() / array.length;
                x = (getWidth() - ArrayUtils.min(width, 60 + padding) * array.length) / 2;
                for (int i : array) {
                    g.setColor(ColorUtils.MY_ORANGE);
                    g.fillRect(x, y - (int) (i * unitHeight) + getHeight(),
                            ArrayUtils.min(width - padding, 60), (int) (i * unitHeight));
                    x += ArrayUtils.min(width, 60 + padding);
                }
                x = 0;
            }

        };
        sub.setBounds(45, 300, getWidth() - 200, 250);
        sub.setBackground(new Color(0, 0, 0, 0));
        return sub;
    }

    @Override
    public JPanel animation(Visualizer main, Visualizer sub, int[] step) {
        int width = (int) main.getWidth() / mainArray.length;
        JPanel animation = new JPanel() {
            int currentIndex;
            int minIndex;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (step[0] >= 0) {
                    currentIndex = step[0];
                    g.setColor(ColorUtils.MY_GREEN);
                    g.fillRect(currentIndex * ArrayUtils.min(width, 60 + padding)
                                    + (main.getWidth() - ArrayUtils.min(width, 60 + padding) * mainArray.length) / 2,
                            -(int) (main.getArray()[currentIndex] * unitHeight) + main.getHeight(),
                            ArrayUtils.min(width - padding, 60), (int) (main.getArray()[currentIndex] * unitHeight));
                }
                if (step[1] >= 0) {
                    minIndex = step[1];
                    g.setColor(Color.BLUE);
                    g.fillRect(minIndex * ArrayUtils.min(width, 60 + padding)
                                    + (main.getWidth() - ArrayUtils.min(width, 60 + padding) * mainArray.length) / 2,
                            -(int) (main.getArray()[minIndex] * unitHeight) + main.getHeight(),
                            ArrayUtils.min(width - padding, 60), (int) (main.getArray()[minIndex] * unitHeight));
                }
            }
        };
        animation.setBounds(45, 30, getWidth() - 200, 520);
        animation.setBackground(new Color(0, 0, 0, 0));
        return animation;
    }

    @Override
    public void updateMainArray(int[] array) {
        SelectionSortScreen newScreen = new SelectionSortScreen() {
            @Override
            public void setMainArray() {
                this.mainArray = array;
            }
        };
        if (this.getExtendedState() == MAXIMIZED_BOTH) {
            newScreen.setExtendedState(MAXIMIZED_BOTH);
        } else {
            newScreen.setSize(this.getWidth(), this.getHeight());
        }
        dispose();
    }

    @Override
    public Color getColor() {
        return ColorUtils.MY_ORANGE;
    }

    public int getMaxValue() {
        return 1000;
    }

    public String newHelpInfo() {
        return "\nSelection Sort\n\n"
                + "Selection Sort is a simple sorting algorithm. It repeatedly selects the smallest element from an unsorted part of the array and places it at the beginning.\n\n"
                + "Consider the Array has size n \n\n"
                + "Time Complexity:\n\n"
                + "     Worst Case: O(n^2)\n\n"
                + "     Best Case: O(n^2)\n\n"
                + "     Average Case: O(n^2)\n\n"
                + "Space Complexity: O(1)\n\n"
                + "The array size in Selection Sort used for visualization has a max size of 100 elements.\n"
                + "A valid array in Selection Sort only has the max value of 1000, and it is non-negative numbers.";
    }
}