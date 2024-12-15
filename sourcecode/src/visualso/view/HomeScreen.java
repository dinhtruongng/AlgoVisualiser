package visualso.view;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import visualso.component.MyButton;
import visualso.component.SortButton;
import visualso.controller.HomeController;

@SuppressWarnings("serial")
public class HomeScreen extends BaseScreen {
	private final HomeController homeController;
	public HomeScreen() {
		super();
		homeController = new HomeController();
		JPanel homePane = new JPanel(new GridLayout(2,1));
		add(homePane, BorderLayout.CENTER);
		homePane.add(createInfoPanel());
		homePane.add(createSortSelectionPane());
		homePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 45, 0));
	}
	
	private JPanel createInfoPanel() {
		JPanel infoPane = new JPanel(new BorderLayout()); 
		infoPane.add(createLogoPane());
		infoPane.add(createHelpPane(),BorderLayout.SOUTH);
		infoPane.setBorder(BorderFactory.createEmptyBorder(70, 0, 70, 0));;
		return infoPane;
	}
	
	private JPanel createLogoPane() {
		JPanel logoPane = new JPanel(new BorderLayout());
		Icon visualSoIcon= new ImageIcon(new ImageIcon(ASSET_PATH+"VisualSO_icon1.png").getImage().getScaledInstance(420, 70, Image.SCALE_SMOOTH));
		JLabel logo = new JLabel(visualSoIcon);
		logoPane.add(logo, BorderLayout.CENTER);
		JLabel infoLabel = new JLabel("Visualizer Sorting Algorithms");
		JPanel desc = new JPanel();
		desc.add(infoLabel);
		logoPane.add(desc, BorderLayout.SOUTH);
		return logoPane;
	}
	
	private JPanel createHelpPane() {
		JPanel helpPane = new JPanel();

		MyButton btnHelp = new MyButton(50,30,Color.BLACK);
		btnHelp.setText("Help");
		btnHelp.addActionListener(baseController.helpButtonClicked("Help",helpInfo));
		helpPane.add(btnHelp);

		MyButton btnAbout = new MyButton(50,30,Color.BLACK);
		btnAbout.setText("About");
		btnAbout.addActionListener(baseController.helpButtonClicked("About",aboutInfo));
		helpPane.add(btnAbout);

		MyButton btnRecord = new MyButton(45, 45, Color.red);
		btnRecord.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		btnRecord.setText("Rec");
		btnRecord.setFont(new Font("Sans", Font.BOLD, 17));
		btnRecord.addActionListener(homeController.recordButtonClicked());
		helpPane.add(btnRecord,1,0);
		btnRecord.addActionListener(e -> {
			//toggle between red and green
			if (btnRecord.getBackground().equals(Color.RED)) {
				btnRecord.setBackground(Color.GREEN);
			} else {
				btnRecord.setBackground(Color.RED);
			}
		});
		MyButton btnSort = new MyButton(50, 30, Color.CYAN);
		btnSort.setText("Sort");
		btnSort.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		btnSort.addActionListener(homeController.sortButtonClicked());
		helpPane.add(btnSort,1);
		return helpPane;
	}
	
	private JPanel createSortSelectionPane() {
		JPanel sortSelectionPane = new JPanel(new GridLayout(2,3));
		SortButton btnMergeSort = new SortButton("Merge Sort",this,homeController);
		// SortButton btnCountingSort = new SortButton("Counting Sort",this,homeController);
		// SortButton btnRadixSort = new SortButton("Radix Sort",this,homeController);
		SortButton btnBubbleSort = new SortButton("Bubble Sort",this,homeController);
		SortButton btnInsertionSort = new SortButton("Insertion Sort",this,homeController);
		SortButton btnSelectionSort = new SortButton("Selection Sort",this,homeController);
		SortButton btnQuickSort = new SortButton("Quick Sort",this,homeController);
		SortButton btnShellSort = new SortButton("Shell Sort",this,homeController);
		sortSelectionPane.add(btnBubbleSort);
		sortSelectionPane.add(btnInsertionSort);
		sortSelectionPane.add(btnSelectionSort);
		sortSelectionPane.add(btnQuickSort);
		sortSelectionPane.add(btnShellSort);
		sortSelectionPane.add(btnMergeSort);
		// sortSelectionPane.add(btnCountingSort);
		// sortSelectionPane.add(btnRadixSort);
		return sortSelectionPane;
	}

	
}

