package matrixCalculator;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GUI extends JFrame implements ActionListener {
	
	private JPanel topBar, logoBar;
	private JScrollPane matrixList;
	
	
	public GUI(){
		super("TEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(980, 600);
		setResizable(false);
		Font textFont = new Font("Courier New", Font.BOLD, 200);
		
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.lightGray);
		
		logoBar = new JPanel();
		logoBar.setBounds(0, 0,(int)(getWidth()*0.2), getHeight());

		
		matrixList = new JScrollPane();
		matrixList.setBounds(0, 200, logoBar.getWidth(), getHeight() - 200);
		add(matrixList);
		add(logoBar);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
