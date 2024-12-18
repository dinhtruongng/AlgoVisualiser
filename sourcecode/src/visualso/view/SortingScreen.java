package visualso.view;

import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import visualso.component.DemonstratePane;
import visualso.component.MyButton;
import visualso.component.MyControlButton;
import visualso.component.MyLabel;
import visualso.component.MySideButton;
import visualso.component.MySlider;
import visualso.component.MyTextField;
import visualso.component.Visualizer;
import visualso.controller.SortingController;
import visualso.util.ArrayUtils;
import visualso.util.ColorUtils;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.List;
// import visualso.util.DataUtils;

@SuppressWarnings("serial")
public abstract class SortingScreen extends BaseScreen {

	final SortingController sortingController;
	protected int[] mainArray;
	protected String sortInfo = "Welcome to our Sorting Algorithms Visualizer";
	JLayeredPane visualizer;
	JPanel container;
    Visualizer main;
    Visualizer sub;
	JPanel animation;
	MySlider processSlider;
	Timer timer;
	DemonstratePane demonstratePane;
	DemonstratePane demonstratePseudoCode;
	MySideButton btnCreate;
	MySideButton btnRandom;
	MyLabel A;
	MyTextField inputArrayField;
	MySideButton btnGo;
	MySideButton btnSort;
	MyLabel errorLabel;
	public final String placeHolder = "Ex: 1, 2, 3, 4, 8";
	
	protected double unitHeight;
	protected int padding = 5;
	
	public SortingScreen() {
		super();
		sortingController = new SortingController(this);
		setMainArray();

		if (ArrayUtils.max(mainArray) !=0) {
			unitHeight = ((double)250) / ((double)ArrayUtils.max(mainArray));}
			else {unitHeight = 0;}
		addBackBtnToTopBar();
		add(createCenter());
		add(createRightSideBar(), BorderLayout.EAST);
		add(createLeftSideBar(), BorderLayout.WEST);
		add(createBottom(), BorderLayout.SOUTH);
		addComponentListener(sortingController.changeWindowSize());
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e){
				((SortingScreen)e.getSource()).requestFocus();
			}
		});
	}
	
	private JLayeredPane createCenter() {
		//VISUALIZER
		visualizer = new JLayeredPane();
		container = new JPanel();
		container.setLayout(null);
		main = main(mainArray, getColor());
		container.add(main);
		sub =  new Visualizer(mainArray);
		container.add(sub);
		animation= new JPanel();
		visualizer.add(container, JLayeredPane.DEFAULT_LAYER);
		
		errorLabel = new MyLabel("");
		errorLabel.setForeground(Color.red);
		visualizer.add(errorLabel, JLayeredPane.DRAG_LAYER);
		//LEFTSIDEBAR
		//create button "Create(A)" belong to generate data button
		btnCreate = new MySideButton(150,33,ColorUtils.MY_BLUE,"Create(A)",SwingConstants.LEFT,sortingController);
		btnCreate.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		visualizer.add(btnCreate,JLayeredPane.MODAL_LAYER);
		//random button belong to create button
		btnRandom = new MySideButton(78,28,ColorUtils.MY_BLUE,"Random",SwingConstants.CENTER,sortingController);
		visualizer.add(btnRandom,JLayeredPane.MODAL_LAYER);
		//A= label belong to create button
		A = new MyLabel("A =");
		visualizer.add(A,JLayeredPane.MODAL_LAYER);
		//input array belong to create button
		inputArrayField = new MyTextField(placeHolder);
		visualizer.add(inputArrayField,JLayeredPane.MODAL_LAYER);

		//go button belong to create button
		btnGo = new MySideButton(50,28,ColorUtils.MY_BLUE,"Go",SwingConstants.CENTER,sortingController);
		visualizer.add(btnGo,JLayeredPane.MODAL_LAYER);
		//sort button belong to generate data button
		btnSort =new MySideButton(150,32,ColorUtils.MY_BLUE,"Sort",SwingConstants.LEFT,sortingController);
		btnSort.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		visualizer.add(btnSort,JLayeredPane.MODAL_LAYER);
		//RIGHTSIDEBAR
		demonstratePane =  new DemonstratePane(380,65,ColorUtils.MY_GREEN, sortInfo);
		demonstratePane.setForeground(Color.BLACK);
		visualizer.add(demonstratePane,JLayeredPane.MODAL_LAYER);
		
		demonstratePseudoCode = new DemonstratePane(0, 0, ColorUtils.MY_GREEN, "");
		visualizer.add(demonstratePseudoCode,JLayeredPane.MODAL_LAYER);
		return visualizer;
	}
	
	private JPanel createBottom() {
		//CONTROL BAR
		JPanel controlBar = new JPanel(new BorderLayout());
		controlBar.setPreferredSize(new Dimension(1000,45));
		controlBar.setBackground(Color.BLACK);

		controlBar.add(createControlPane(), BorderLayout.CENTER);
		controlBar.add(createSpeedPane(), BorderLayout.WEST);
		controlBar.add(createGuidePane(), BorderLayout.EAST);
		return controlBar;
	}
	
	private JPanel createControlPane() {
		//controlpane
		JPanel controlPane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));	
		controlPane.setBackground(Color.BLACK);
		
		//process slide
		processSlider = new MySlider(JSlider.HORIZONTAL,0,0,0,350,20,sortingController.changeProgressSlider());
		processSlider.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
		timer =new Timer(1000-sortingController.getSpeed()*10,sortingController.setTimer());
		
		//play button
		Icon pauseIcon= new ImageIcon(new ImageIcon(ASSET_PATH+"pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		MyControlButton btnPlay = new MyControlButton(30,45,Color.BLACK,pauseIcon,"Play",sortingController);
		//forward button
		Icon forwardIcon = new ImageIcon(new ImageIcon(ASSET_PATH +"forward.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnForward = new MyControlButton(30,45,Color.BLACK,forwardIcon,"Forward",sortingController);
		//backward button
		Icon backwardIcon = new ImageIcon(new ImageIcon(ASSET_PATH+"backward.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnBackward = new MyControlButton(30,45,Color.BLACK,backwardIcon,"Backward",sortingController);
		btnBackward.setIcon(backwardIcon);		
		//end button
		Icon endIcon = new ImageIcon(new ImageIcon(ASSET_PATH+"end.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnEnd = new MyControlButton(30,45,Color.BLACK,endIcon,"End",sortingController);
		//start button
		Icon startIcon = new ImageIcon(new ImageIcon(ASSET_PATH+"start.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnStart = new MyControlButton(30,45,Color.BLACK,startIcon,"Start",sortingController);
				
		controlPane.add(btnStart);
		controlPane.add(btnBackward);
		controlPane.add(btnPlay);
		controlPane.add(btnForward);
		controlPane.add(btnEnd);
		controlPane.add(processSlider);
		
		return controlPane;
	}
	private JPanel createSpeedPane() {
		//speed pane
		JPanel speedPane = new JPanel(new BorderLayout());
		JLabel count = new JLabel(Integer.toString(sortingController.getSpeed()));
		MySlider speedSlider = new MySlider(JSlider.HORIZONTAL,0,100,sortingController.getSpeed(),150,20,sortingController.changeSpeed(count));

		speedPane.setPreferredSize(new Dimension(255,45));
		speedPane.setBackground(Color.BLACK);
		speedPane.setBorder(BorderFactory.createEmptyBorder(5, 70, 0, 0));
						
		count.setForeground(Color.WHITE);
		speedPane.add(speedSlider,BorderLayout.WEST);
		speedPane.add(count,BorderLayout.EAST);
		return speedPane;
	}
	private JPanel createGuidePane() {
		//guidepane
		JPanel guidePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		guidePane.setPreferredSize(new Dimension(180,45));
		guidePane.setBackground(Color.BLACK);
		//Help button
		MyButton btnHelp = new MyButton(50,45,Color.BLACK);
		btnHelp.setText("Help");
		btnHelp.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		btnHelp.addActionListener(baseController.helpButtonClicked());
		//About button
		MyButton btnAbout = new MyButton(50,45,Color.BLACK);
		btnAbout.setText("About");
		btnAbout.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		btnAbout.addActionListener(baseController.aboutButtonClicked("About",aboutInfo));
		guidePane.add(btnHelp);
		guidePane.add(btnAbout);
		return guidePane;
	}
	
	private JPanel createLeftSideBar() {
		//LEFTSIDEBAR 
		JPanel leftSideBar = new JPanel(new BorderLayout());
		leftSideBar.setPreferredSize(new Dimension(45,610));
		leftSideBar.setBackground(Color.BLACK);
		leftSideBar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//Generate data button ">" belong to leftsidebar
		MyButton btnGenData = new MyButton(45,65,ColorUtils.MY_BLUE);
		btnGenData.setText(">");
		btnGenData.setFont(new Font("Sora",Font.BOLD,22));
		btnGenData.addActionListener(sortingController.leftSideButtonClicked());
		leftSideBar.add(btnGenData,BorderLayout.SOUTH);
		return leftSideBar;
	}
	
	private JPanel createRightSideBar() {
		//RIGHTSIDEBAR
		JPanel rightSideBar = new JPanel(new BorderLayout());
		rightSideBar.setPreferredSize(new Dimension(45,610));
		rightSideBar.setBackground(Color.BLACK);
		rightSideBar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//demonstrate button "<" belong to rightsidebar
		MyButton btnDemonstrate = new MyButton(45,250,ColorUtils.MY_GREEN);
		btnDemonstrate.setText("<");
		btnDemonstrate.setFont(new Font("Sora",Font.BOLD,22));
		btnDemonstrate.addActionListener(sortingController.rightSideButtonClicked());
		rightSideBar.add(btnDemonstrate,"South");
		return rightSideBar;
	}
	public void createName(String sortType) {
		MyLabel name = new MyLabel(sortType);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("Sora",Font.BOLD,20));
		name.setVisible(true);
		topBar.add(name,BorderLayout.CENTER);
	}
	public void addBackBtnToTopBar() {
		MyButton btnBack = new MyButton(80,45,Color.BLACK);
		btnBack.setText("Back");
		btnBack.setFont(new Font("Sans", Font.BOLD, 17));
		btnBack.addActionListener(sortingController.backButtonClicked());
		buttonGroup.add(btnBack,2,0);
	}
	public Visualizer main(int[] array, Color color) {

		int width =((int) getWidth()-200)/mainArray.length;
		
		Visualizer main =  new Visualizer(array) {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 0; i< array.length;i++) {
					g.setColor(color);
				    g.fillRect(i*ArrayUtils.min(width,60+padding)+(getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -(int)(getArray()[i] * unitHeight)
				    		   + getHeight(),ArrayUtils.min(width-padding,60),(int)(getArray()[i]*unitHeight));
				}
				
			}
		};
		main.setBounds(45, 30, getWidth()-200, 250);
		return main;
	}
	public abstract Visualizer sub(int[] array);
	public abstract JPanel animation(Visualizer main, Visualizer sub,int[] step);
	public abstract void updateMainArray(int[] array);
	public abstract Color getColor();
	public abstract int getMaxValue();
	public abstract String newHelpInfo();
	
	
	
	public int[] getMainArray() {
		return mainArray;
	}
	
	public void setMainArray() {
		// Define the file path
		String filePath = new File("sourcecode\\assets").getAbsolutePath() + "\\output.txt";

		// Declare the variable outside the try block
		String secondLine = "";

		try {
			// Read all lines from the file
			Path path = Paths.get(filePath);
			List<String> lines = Files.readAllLines(path); // Reads file content into a list of lines

			// Assign the first line to the variable
			if (!lines.isEmpty()) {
				secondLine = lines.get(1); // Get the first line
			}
		} catch (IOException e) {
			System.err.println("An error occurred while reading the file: " + e.getMessage());
		}
		String[] stringNumbers = secondLine.replaceAll("[\\[\\]\\s]", "").split(",");

		// Convert the string array to an int array
		int[] array = Arrays.stream(stringNumbers)
				.mapToInt(Integer::parseInt)
				.toArray();
		mainArray = array;
	}
	
	public JSlider getProcessSlider() {
		return processSlider;
	}


	public MyButton getBtnCreate() {
		return btnCreate;
	}

	public MyButton getBtnRandom() {
		return btnRandom;
	}

	public JLabel getA() {
		return A;
	}
	public void setA(MyLabel a) {
		A = a;
	}
	public JTextField getInputArrayField() {
		return inputArrayField;
	}

	public MyButton getBtnGo() {
		return btnGo;
	}

	public MyButton getBtnSort() {
		return btnSort;
	}

	public Timer getTimer() {
		return timer;
	}
	
	public JLayeredPane getVisualizer() {
		return visualizer;
	}

	public Visualizer getMain() {
		return main;
	}
	public void setMain(Visualizer main) {
		this.main = main;
	}
	public Visualizer getSub() {
		return sub;
	}
	public void setSub(Visualizer sub) {
		this.sub = sub;
	}
	public JPanel getAnimation() {
		return animation;
	}
	public void setAnimation(JPanel animation) {
		this.animation = animation;
	}
	public DemonstratePane getDemonstratePane() {
		return demonstratePane;
	}

	public DemonstratePane getDemonstratePseudoCode() {
		return demonstratePseudoCode;
	}
	
	public JPanel getContainer1() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	public MyLabel getErrorLabel() {
		return errorLabel;
	}


	public SortingController getController() {
		return sortingController;
	}
}
