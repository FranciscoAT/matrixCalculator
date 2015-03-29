package matrixCalculator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class Screen extends JPanel implements ActionListener{
	
	private static int maxArrLength = 10;
	private ArrayList<Matrix> matrixList;
	private JLabel title;
	private JPanel topPanel, centerPanel;
	private JButton createMatrixAButton, createMatrixBButton, cancel, next;
	private JTextField matrixName;
	private GUI runInstance;
	private int numNext;
	
	private JComboBox numRows, numCol;
	
	public Screen(GUI runInstance){
		this.runInstance = runInstance;
		setLayout(new BorderLayout());
		matrixList = new ArrayList<Matrix>();
		setSize(new Dimension(780,525));
		
		topPanel = new JPanel();
		
		title = new JLabel("Hit \"Create\" to get started!");
		title.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
		title.setBounds(0, 0, topPanel.getWidth(), 50);
		title.setOpaque(false);
		
		next = new JButton("Next");
		next.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		topPanel.add(title);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(centerPanel, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancel){
			reset();
		} else if (e.getSource() == next){
			if(numNext == 0)
				createNewMatrixB();
			else if(numNext ==1){
				
			}
		}
	}
	
	public void add(Matrix m){
		matrixList.add(m);
	}
	
	public void createNewMatrixA(){
		numNext = 0;
		String[] maxLength = new String[maxArrLength];
		for(int i = 0; i < maxLength.length; i++){
			maxLength[i] = ""+(i+1);
		}
		
		title.setText("Press \"Back\" button to stop creating new Matrix. Else fill out information and hit next.");
		
		JLabel numRowLabel = new JLabel("Select Number of Rows: ");
		JLabel numColLabel = new JLabel("Select Number of Columns: ");
		JLabel nameLabel = new JLabel("Enter name: ");
		numRows = new JComboBox(maxLength);
		numCol = new JComboBox(maxLength);
		matrixName = new JTextField();
		centerPanel.add(numRowLabel);
		centerPanel.add(numRows);
		centerPanel.add(numColLabel);
		centerPanel.add(numCol);
		centerPanel.add(nameLabel);
		centerPanel.add(matrixName);
		centerPanel.add(next);
		centerPanel.add(cancel);

		repaint();
		revalidate();
	}
	
	public void createNewMatrixB(){
		numNext = 1;
		int newMatrixRows = numRows.getSelectedIndex()+1;
		int newMatrixCol = numCol.getSelectedIndex()+1;
		String name = matrixName.getText();	
		Matrix m = new Matrix(name, newMatrixRows, newMatrixCol);
		runInstance.addMatrix(m);
		reset();
		title.setText("Enter values for the new matrix");
		//centerPanel.add(next, BorderLayout.SOUTH);
		//centerPanel.add(cancel, BorderLayout.SOUTH);
		centerPanel.setLayout(new GridLayout(newMatrixRows, newMatrixCol));
		JTextField[][] matrixValues = new JTextField[newMatrixRows][newMatrixCol];
		for(int i = 0; i<newMatrixRows; i++){
			for(int j = 0; j<5; j++){
				matrixValues[i][j] = new JTextField(i+";"+j);
				matrixValues[i][j].setPreferredSize(new Dimension(10,10));
				centerPanel.add(matrixValues[i][j]);
			}
		}
		
		repaint();
		revalidate();
		
	}
	
	public void displayMatrix(Matrix m){
		
	}
	
	public void reset(){
		title.setText("Blank Page");
		runInstance.enableAll();
		centerPanel.removeAll();
		repaint();
		revalidate();
	}
	
	
	
	

}
