package matrixCalculator;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
	
	private JPanel topBar, logoBar;
	private JList matrixList;
	private DefaultListModel<String> MLModel;
	private JScrollPane listScroller;
	private JButton createMatrix, selectMatrix, deleteMatrix, helpButton, transposeButton, detButton, rowRedButton, inverseButton;
	private Screen mainScreen;	
	private Matrix selectedMatrix;
	private ArrayList<Matrix> matrixArray;
	
	public GUI(){
		super("Matrix Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		matrixArray = new ArrayList();
		
		setSize(980, 600);
		setResizable(true);
		Container c = getContentPane();
		c.setBackground(Color.lightGray);
		
		init();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createMatrix){
			createMatrix();
		} else if (e.getSource() == selectMatrix){
			displayMatrix();
		} else if (e.getSource() == deleteMatrix){
			deleteSelectedMatrix();
		} else if(e.getSource() == transposeButton){
			function_transpose();
		}
		
	}
	
	public void init(){
		leftBar();
		topBar();
		mainScreen = new Screen(this);
		add(mainScreen, BorderLayout.CENTER);
		
	}
	
	public void leftBar(){
		logoBar = new JPanel();
		logoBar.setPreferredSize(new Dimension(200, getHeight()));
		logoBar.setLayout(new BorderLayout());
		logoBar.setBackground(new Color(214, 33, 36));
		
		MLModel = new DefaultListModel<String>();
		
		matrixList = new JList(MLModel);
		matrixList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listScroller = new JScrollPane(matrixList);
		listScroller.setBounds(10,10,50,50);		
		logoBar.add(listScroller, BorderLayout.CENTER);
		
		JPanel buttonBar = new JPanel();
		buttonBar.setPreferredSize(new Dimension(logoBar.getWidth(), 75));
		buttonBar.setBackground(new Color(214, 33, 36));
		
		createMatrix = new JButton("Create");
		createMatrix.setPreferredSize(new Dimension(75,30));
		createMatrix.addActionListener(this);
		buttonBar.add(createMatrix);
		
		selectMatrix = new JButton("Select");
		selectMatrix.setPreferredSize(new Dimension(75,30));
		selectMatrix.addActionListener(this);
		buttonBar.add(selectMatrix);
		
		deleteMatrix = new JButton("Delete");
		deleteMatrix.setPreferredSize(new Dimension(75,30));
		deleteMatrix.addActionListener(this);
		buttonBar.add(deleteMatrix);
		
		helpButton = new JButton("Help");
		helpButton.setPreferredSize(new Dimension(75,30));
		helpButton.addActionListener(this);
		buttonBar.add(helpButton);
		
		logoBar.add(buttonBar, BorderLayout.SOUTH);
				
		add(logoBar, BorderLayout.WEST);
	}
	
	public void topBar(){
		topBar = new JPanel();
		topBar.setPreferredSize(new Dimension(100,75));
		topBar.setLayout(new GridLayout(1,0));
		topBar.setBackground(new Color(214, 33, 36));
		
		JLabel title = new JLabel("Matrix Calculator");
		title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		title.setOpaque(true);
		title.setBackground(new Color(214, 33, 36));
		title.setFont(new Font("Serif", Font.BOLD, 22));
		topBar.add(title);
		
		transposeButton = new JButton("Transpose M");
		topBar.add(transposeButton);
		
		detButton = new JButton("Get Det of M");
		topBar.add(detButton);
		
		rowRedButton = new JButton("Row Reduce M");
		topBar.add(rowRedButton);

		inverseButton = new JButton("Inverse M");
		topBar.add(inverseButton);
		
		add(topBar, BorderLayout.NORTH);
	}
	
	public void createMatrix(){
		JButton disableButtons[] = {helpButton};
		disableAllBut(disableButtons);
		mainScreen.createNewMatrixA();
	}
	
	public void addMatrix(Matrix m){
		matrixArray.add(m);
		MLModel.addElement(m.getName());
		revalidate();
	}
	
	public void setSelectedMatrix(Matrix m){
		selectedMatrix = m;
	}
	
	public void disableAllBut(JButton[] b){
		JButton allButton[] = {createMatrix, selectMatrix, deleteMatrix, helpButton, transposeButton, detButton, rowRedButton, inverseButton};
		for(int i = 0; i<allButton.length; i++){
			boolean disable = true;
			for(int j = 0; j<b.length; j++){
				if(b[j] == allButton[i])
					disable = false;
			}
			if(disable)
				allButton[i].setEnabled(false);
		}	
	}
	
	public void enableAll(){
		JButton allButton[] = {createMatrix, selectMatrix, deleteMatrix, helpButton, transposeButton, detButton, rowRedButton, inverseButton};
		for(int i = 0; i<allButton.length; i++){
			allButton[i].setEnabled(true);
		}
	}
	
	public void displayMatrix(){
		Matrix m = matrixArray.get(matrixList.getSelectedIndex());
		mainScreen.displayMatrix(m);
		selectedMatrix = m;
	}
	
	public void deleteSelectedMatrix(){
		matrixArray.remove(matrixList.getSelectedIndex());
		MLModel.remove(matrixList.getSelectedIndex());
		mainScreen.reset();
		selectedMatrix = null;
	}
	
	public void function_transpose(){
		if(selectedMatrix != null){
			mainScreen.transpose(selectedMatrix);
		}
	}
}
