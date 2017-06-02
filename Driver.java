import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Driver 
{	
	public static void main(String[] args) 
	{	    
		Object[] options = {"Selection", "Insertion", "Bubble"};
		int x = JOptionPane.showOptionDialog(null,"Which sort " + "would you like to see?", "Sort Demonstration", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[2]);
		Object[] optionstwo = {"0.5", "1", "2", "3"};
		int y = JOptionPane.showOptionDialog(null,"How many seconds " + "would you like your delay to be?", "Sort Demonstration", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, optionstwo, options[2]);
		double jj = (double)(y);
		if(jj == 0.0)
			jj = 0.5;
		else if(jj < 0)
			jj = 0.1;
		JFrame frame = new JFrame("Bars");
        Maker m = new Maker(frame);
        for (int i = 0; i < 10; i++) 
        	m.doAgain(i, x, jj);
	}
}
