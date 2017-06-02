	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	
	public class PEMDASgui 
	{
	  private int WIDTH = 500;
	  private int HEIGHT = 75;
	  private JFrame frame;
	  private JPanel panel;
	  private JLabel resultLabel;
	  private JTextField fahrenheit;

	  
	  public PEMDASgui() {
	    frame = new JFrame("Equation Solver");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    resultLabel = new JLabel("---");
	    fahrenheit = new JTextField(20);
	    fahrenheit.addActionListener(new TempListener());
	    panel = new JPanel();
	    panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    panel.setBackground(Color.gray);
	    panel.add(new JLabel("Enter Equation:"));
	    panel.add(fahrenheit);
	    panel.add(new JLabel("Answer:"));
	    panel.add(resultLabel);
	    frame.getContentPane().add(panel);
	    display();
	  }
	
	  public void display(){
	    frame.pack();
	    frame.setVisible(true);
	  }
	  
	  private class TempListener implements ActionListener {public void actionPerformed(ActionEvent event){resultLabel.setText("" + PEMDASolver.getAnswer(fahrenheit.getText()));}}
	}