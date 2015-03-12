package matrixCalculator;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Screen extends JPanel {
	
	private ArrayList matrixList;
	private JLabel title;
	private JPanel topPanel;
	
	public Screen(){
		setSize(new Dimension(780,525));
		
		topPanel = new JPanel();
		//topPanel.setPreferredSize(new Dimension(780, 50));
		topPanel.setBackground(Color.CYAN);
		
		title = new JLabel("Hit \"Create\" to get started!");
		title.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
		title.setBounds(0, 0, topPanel.getWidth(), 50);
		title.setOpaque(false);
		
		topPanel.add(title);
		add(topPanel, BorderLayout.NORTH);
		System.out.println(this.getWidth());
	}
	
	

}
