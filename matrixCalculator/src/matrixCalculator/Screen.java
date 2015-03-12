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
	private JPanel topPanel;
	private JButton createMatrixAButton, createMatrixBButton;
	
	public Screen(){
		matrixList = new ArrayList<Matrix>();
		setSize(new Dimension(780,525));
		
		topPanel = new JPanel();
		
		title = new JLabel("Hit \"Create\" to get started!");
		title.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
		title.setBounds(0, 0, topPanel.getWidth(), 50);
		title.setOpaque(false);
		
		topPanel.add(title);
		add(topPanel, BorderLayout.NORTH);
		System.out.println(this.getWidth());
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
	public void add(Matrix m){
		matrixList.add(m);
	}
	
	public void createNewMatrixA(){
		String[] maxLength = new String[maxArrLength];
		for(int i = 0; i < maxLength.length; i++){
			maxLength[i] = ""+(i+1);
		}
		
		JComboBox numRows = new JComboBox(maxLength);
		
		add(numRows, BorderLayout.CENTER);
	}
	
	

}
