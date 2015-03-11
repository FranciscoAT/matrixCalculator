package matrixCalculator;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;


public class GUI extends JFrame implements ActionListener {
	
	private JPanel topBar, logoBar;
	private JList matrixList;
	private DefaultListModel MLModel;
	private JScrollPane listScroller;
	private JButton createNewMatrix, selectMatrix, deleteMatrix, help;
		
	
	public GUI(){
		super("Matrix Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(980, 600);
		setResizable(true);
		Container c = getContentPane();
		c.setBackground(Color.lightGray);
		
		init();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void init(){
		leftBar();
	}
	
	public void leftBar(){
		logoBar = new JPanel();
		logoBar.setPreferredSize(new Dimension(200, getHeight()));
		
		JLabel title = new JLabel("Matrix Calculator");
		title.setPreferredSize(new Dimension(logoBar.getWidth(), 100));
		title.setOpaque(true);
		logoBar.add(title, BorderLayout.NORTH);
		
		
		MLModel = new DefaultListModel();
		for(int i = 0; i<10; i++){
			MLModel.addElement("Test"+i);
		}
		
		matrixList = new JList(MLModel);
		matrixList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listScroller = new JScrollPane(matrixList);
		listScroller.setBounds(10,10,50,50);
		
		logoBar.add(listScroller, BorderLayout.CENTER);
		add(logoBar, BorderLayout.WEST);

	}
}
