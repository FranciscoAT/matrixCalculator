package matrixCalculator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class Screen extends JPanel implements ActionListener{
	
	private static int maxArrLength = 10;
	private JLabel title, currentMatrixValues[][];
	private JPanel topPanel, centerPanel, bottomPanel;
	private JButton createMatrixAButton, createMatrixBButton, cancel, next, createAsNew;
	private JTextField matrixName, matrixValues[][];
	private GUI runInstance;
	private int numNext;
	private Matrix newMatrix;
	
	private JComboBox numRows, numCol;
	
	public Screen(GUI runInstance){
		this.runInstance = runInstance;
		setLayout(new BorderLayout());
		setSize(new Dimension(780,525));
		
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		title = new JLabel("Hit \"Create\" to get started!");
		title.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
		title.setBounds(0, 0, topPanel.getWidth(), 50);
		title.setOpaque(false);
		
		next = new JButton("Next");
		next.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		createAsNew = new JButton("Create as new Matrix");
		createAsNew.addActionListener(this);
		
		topPanel.add(title);
		
		centerPanel = new JPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancel){
			reset();
		} else if (e.getSource() == next){
			if(numNext == 0)
				createNewMatrixB();
			else if(numNext ==1){
				createNewMatrixC();
			}
		} else if(e.getSource() == createAsNew){
			createAsNewM();
		}
	}
	
	public void createNewMatrixA(){
		reset();
		numNext = 0;
		String[] maxLength = new String[maxArrLength];
		for(int i = 0; i < maxLength.length; i++){
			maxLength[i] = ""+(i+1);
		}
		
		title.setText("Press \"Back\" button to stop creating new Matrix. Else fill out information and hit next.");
		
		JPanel[] filler = new JPanel[3];
		for(int i = 0; i<filler.length; i++){
			filler[i] = new JPanel();
			filler[i].setPreferredSize(new Dimension(2000, 5));
		}
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel numRowLabel = new JLabel("Select Number of Rows: ");
		JLabel numColLabel = new JLabel("Select Number of Columns: ");
		JLabel nameLabel = new JLabel("Enter name: ");
		numRows = new JComboBox(maxLength);
		numCol = new JComboBox(maxLength);
		matrixName = new JTextField();
		matrixName.setColumns(10);
		centerPanel.add(filler[2]);
		centerPanel.add(numRowLabel);
		centerPanel.add(numRows);
		centerPanel.add(filler[0]);
		centerPanel.add(numColLabel);
		centerPanel.add(numCol);
		centerPanel.add(filler[1]);
		centerPanel.add(nameLabel);
		centerPanel.add(matrixName);
		bottomPanel.add(next);
		bottomPanel.add(cancel);

		repaint();
		revalidate();
	}
	
	public void createNewMatrixB(){
		numNext = 1;
		int newMatrixRows = numRows.getSelectedIndex()+1;
		int newMatrixCol = numCol.getSelectedIndex()+1;
		String name = matrixName.getText();	
		newMatrix = new Matrix(name, newMatrixRows, newMatrixCol);
		reset();
		title.setText("Enter values for the new matrix");
		bottomPanel.add(next, BorderLayout.SOUTH);
		bottomPanel.add(cancel, BorderLayout.SOUTH);
		centerPanel.setLayout(new GridLayout(newMatrixRows, newMatrixCol));
		matrixValues = new JTextField[newMatrixRows][newMatrixCol];
		for(int i = 0; i<newMatrixRows; i++){
			for(int j = 0; j<newMatrixCol; j++){
				matrixValues[i][j] = new JTextField(i+";"+j);
				matrixValues[i][j].setPreferredSize(new Dimension(10,10));
				centerPanel.add(matrixValues[i][j]);
			}
		}
		
		repaint();
		revalidate();
	}
	
	public void createNewMatrixC(){
		double[][] doubleValues = new double[matrixValues.length][matrixValues[0].length];
		for(int i = 0; i<matrixValues.length; i++){
			for(int j = 0; j<matrixValues[0].length; j++){
				doubleValues[i][j] = Double.parseDouble(matrixValues[i][j].getText());
			}
		}
		newMatrix.setMatrixValues(doubleValues);
		runInstance.addMatrix(newMatrix);
		runInstance.setSelectedMatrix(newMatrix);
		displayMatrix(newMatrix);
		newMatrix = null;
	}
	
	public void displayMatrix(Matrix m){
		reset();
		title.setText("Matrix: "+m.getName());
		double[][] vals = m.getMatrix();
		currentMatrixValues = new JLabel[m.getNumRows()][m.getNumColumns()];
		centerPanel.setLayout(new GridLayout(m.getNumRows(), m.getNumColumns()));
		for(int i = 0; i<m.getNumRows(); i++){
			for(int j = 0; j<m.getNumColumns(); j++){
				currentMatrixValues[i][j] = new JLabel(""+vals[i][j], SwingConstants.CENTER);
				centerPanel.add(currentMatrixValues[i][j]);
			}
		}
		revalidate();
	}
	
	public void reset(){
		title.setText("Blank Page");
		runInstance.enableAll();
		centerPanel.removeAll();
		bottomPanel.removeAll();
		repaint();
		revalidate();
	}
	
	public void transpose(Matrix m){
		m.setMatrixValues(matrixMath.transpose(m.getMatrix()));
		title.setText("Matrix: "+m.getName()+" : Transposed");
		displayMatrix(m);
		bottomPanel.add(cancel);
		bottomPanel.add(createAsNew);
		revalidate();
	}
	
	public void createAsNewM(){
		String name = title.getText().substring(8);
		double vals[][] = new double[currentMatrixValues.length][currentMatrixValues[0].length];
		for(int i = 0; i<vals.length; i++){
			for(int j = 0; j<vals[0].length; i++){
				vals[i][j] = Double.parseDouble(currentMatrixValues[i][j].getText());
			}
		}
		Matrix m = new Matrix(name, currentMatrixValues.length, currentMatrixValues[0].length);
		runInstance.addMatrix(m);
	}
	
	

}
