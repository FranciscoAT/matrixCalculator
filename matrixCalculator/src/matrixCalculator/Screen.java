package matrixCalculator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Francisco Trindade; francisco.ad.trindade@gmail.com
 * @since 13/04/15
 */

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
				try{
					createNewMatrixC();
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid inputs!", "Error", JOptionPane.ERROR_MESSAGE);
				}
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
		double count = 0;
		for(int i = 0; i<newMatrixRows; i++){
			for(int j = 0; j<newMatrixCol; j++){
				matrixValues[i][j] = new JTextField(""+count);
				matrixValues[i][j].setPreferredSize(new Dimension(10,10));
				matrixValues[i][j].setHorizontalAlignment(JTextField.CENTER);
				centerPanel.add(matrixValues[i][j]);
				count++;
			}
		}
		
		repaint();
		revalidate();
	}
	
	public void createNewMatrixC() throws NumberFormatException{
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
		title.setText(""+m.getName());
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
		Matrix tempM = new Matrix("", m.getNumColumns(), m.getNumRows());
		tempM.setMatrixValues(matrixMath.transpose(m.getMatrix()));
		displayMatrix(tempM);
		title.setText(m.getName()+" : Transposed");
		bottomPanel.add(cancel);
		bottomPanel.add(createAsNew);
		revalidate();
	}
	
	public void getDet(Matrix m){
		reset();
		bottomPanel.add(cancel);
		double tempD = matrixMath.getDet(m.getMatrix());
		String output;
		if(tempD == Double.NaN)
			output = "Unable to obtain det, lengths are not equal!";
		else
			output = ""+tempD;
		title.setText("Det of Matrix: "+m.getName());
		JLabel tempLabel = new JLabel("Det = "+output);
		centerPanel.add(tempLabel);
		revalidate();
	}
	
	public void createAsNewM(){
		String name = title.getText();
		double vals[][] = new double[currentMatrixValues.length][currentMatrixValues[0].length];
		for(int i = 0; i<vals.length; i++){
			for(int j = 0; j<vals[0].length; j++){
				vals[i][j] = Double.parseDouble(currentMatrixValues[i][j].getText());
			}
		}
		Matrix m = new Matrix(name, currentMatrixValues.length, currentMatrixValues[0].length);
		m.setMatrixValues(vals);
		runInstance.addMatrix(m);
		reset();
	}
	
	public void getRowReduce(Matrix m){
		Matrix tempM = new Matrix("", m.getNumRows(), m.getNumColumns());
		tempM.setMatrixValues(matrixMath.rowReduce(m.getMatrix(), m.getNumColumns()));
		displayMatrix(tempM);
		title.setText(m.getName()+" : Row Reduced");
		bottomPanel.add(cancel);
		bottomPanel.add(createAsNew);
		revalidate();
	}
	
	public void getInverse(Matrix m){
		double[][] tempVal = matrixMath.getInverse(m.getMatrix());
		if(tempVal != null){
			Matrix tempM = new Matrix("", m.getNumRows(), m.getNumColumns());
			tempM.setMatrixValues(tempVal);
			displayMatrix(tempM);
			title.setText(m.getName()+" : Inverse");
			bottomPanel.add(createAsNew);
		} else {
			reset();
			title.setText(m.getName()+" : Inverse");
			JLabel tempLabel = new JLabel("Unable to get Inverse of selected matrix!");
			centerPanel.add(tempLabel);
			title.setText(m.getName()+" : Inverse");
		}
		bottomPanel.add(cancel);
		revalidate();
	}
	
	

}
