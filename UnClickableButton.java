import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class makes the game GUI and runs the whole show.
 * @author 18sschiller
 */
public class UnClickableButton 
{
	public static int consttt = 200; //constant for if you want to cheat and win
	public static boolean cheatUsed = false;
    public static JFrame window; //frame, static so it can be modified externally
    public JPanel panel2; //panel with all my stuff
    public JLabel inputLabel, outputLabel, clicksLabel; //all the labels for game stats
    public static JLabel resultLabel; //time label that updates every second
    public static int totalClicks; //counts total number of clicks
    public static int totalBlocks; //counts all of the blocks destroyed 
    /**
     * Creates the actual game GUI and creates all of the listeners
     * in order to make the game interactive.
     */
    public UnClickableButton()
    {
    	totalClicks = 0; //zeros the counters
    	totalBlocks = 0; //zeros the blocks
    	window = new JFrame("THE GAME"); //makes new JFrame
    	window.addWindowListener(new WindowAdapter()
        {
    		/**
    		 * This function is if if/when the use quits the game.
    		 * It listens for when the person exits the window,
    		 * and then it creates a pop-up message that basically
    		 * just insults the user.
    		 */
            @Override
            public void windowClosing(WindowEvent e)
            {
                e.getWindow().dispose();
                String so = "Suprise, suprise. You lost. <br>How could I have ever guessed? <br>You?? Giving up?? I'm so utterly shocked. <br>You made " + totalClicks + " attempts on that button and missed. <br>Every. Single. Time. <br>I am embarrassed that you even used my program. <br>You just wasted " + resultLabel.getText() + " of your life that you will never get back.<br>Now that you are done wasting your time on my program,<br>Go ask your mother how she gave birth to such a dissapointment. <br>Thank you and have a good day!";
				int res = 1;
				res = JOptionPane.showOptionDialog(null, "<html><div style='text-align: center;'>" + so + "</html>", "You lose.", JOptionPane.DEFAULT_OPTION,
				        JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(res==-1)
					res = JOptionPane.showOptionDialog(null, "<html><div style='text-align: center;'>" + "What the hell is wrong with you? <br>There was a completely reasonably placed OK button there <br>and you just went ahead a used the exit button. <br>Do you have any respect?<br>Don't take your anger out on my program." + "</html>", "You Won!!!", JOptionPane.DEFAULT_OPTION,
					        JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(res==-1)
					res = JOptionPane.showOptionDialog(null, "<html><div style='text-align: center;'>" + "You disgust me. <br>I am done wasing my time on you." + "</html>", "You Won!!!", JOptionPane.DEFAULT_OPTION,
					        JOptionPane.PLAIN_MESSAGE, null, null, null);            }
        });
        window.setPreferredSize (new Dimension(1300, 720)); //sets the size of the window
        inputLabel = new JLabel ("# of Blocks Missed:  " + totalBlocks); //will show how many blocks were turned white
        clicksLabel = new JLabel("          # of Clicks:  " + totalClicks); //will display the total amount of times user clicked the mouse
        outputLabel = new JLabel ("          Time Wasted: "); //will display the amount of time wasted
        resultLabel = new JLabel (""); //created for the time later to update it
        JButton jba[] = new JButton[816]; //JButton array used to create the grid
        UnClickableButtonRunner.used.add(123); //adds 123 to the used array because the button starts of 123 and it is used
        for(int i=0;i<jba.length;i++) //runs to the size of the button array
        {
        	jba[i] = new JButton(); //instantiates the new button
        	jba[i].setText(" "); //sets the text to nothing
        	jba[i].setBackground(Color.black); //makes it the color black
        	jba[i].addMouseListener(new MouseAd(jba, i)); //makes a new mouse listener for every single button, refer to MouseAd.java
        	jba[i].addMouseMotionListener(new MouseMotionAdapter() 
        	{
        		/**
        		 * This function is to stop the user from dragging their mouse into the correct button spot.
        		 * It will change the position of the button 5 times a second while you are dragging your mouse
        		 * over other buttons.
        		 * @param MouseEvent evt
        		 */
        		    public void mouseDragged(MouseEvent evt) 
        		    {
        		    	jba[123].setBackground(jba[124].getBackground()); //sets 123 as the background of the button next to it
        		    	//this ^ accounts for if the person starts of by dragging, the 123 button will stay red without this.
	  	  	  	    	jba[MouseAd.whereRed].setBackground(Color.white); //sets the old red button to white
	  		  	  	    if(UnClickableButton.totalBlocks>=815) //does the same thing as above
	  		  	  	    {
	  		  	  	    	jba[MouseAd.whereRed].setBackground(Color.green);
	  	  					@SuppressWarnings("unused")
	  						int res = JOptionPane.showOptionDialog(null, "<html><div style='text-align: center;'>" + "Did you really think that would work?" + "</html>", "JUST KIDDING!!!", JOptionPane.DEFAULT_OPTION,
	  	  					        JOptionPane.PLAIN_MESSAGE, null, null, null);
	  		  	  	    	for (int i = 0; i < jba.length; i++) 
	  		  	  	    	{
	  							jba[i].setBackground(Color.black);
	  							UnClickableButton.totalBlocks=0;
	  							UnClickableButtonRunner.used.clear();
	  						}
	  		  	  	    }
	  	  	  	        MouseAd.whereRed = MouseAd.newPlace(evt.getXOnScreen(), evt.getYOnScreen(), jba); //runs the newPlace function, which picks a new place for the red button
	  	 	    		jba[MouseAd.whereRed].setBackground(Color.red); //sets the new button location as red
	  	 	    		MouseAd.spec = MouseAd.whereRed; //sets spec as the new red, to make sure data transfer goes smoothly
	  		    		MouseAd.blacker(1, jba); MouseAd.blacker(-1, jba); MouseAd.blacker(35, jba); MouseAd.blacker(34, jba); MouseAd.blacker(33, jba); MouseAd.blacker(-33, jba); MouseAd.blacker(-34, jba); MouseAd.blacker(-35, jba); 
	  	 	    		//this ^ makes sure that all of the spaces around the new button that were blue become black
	  		    		UnClickableButton.totalBlocks++; //adds the the new total number of blocks destroyed
	  	 	    	    Robot i; //creates a new robot
						try {
							i = new Robot(); //this lets you do a bunch of neat tricks
		  	 	    	    i.delay(consttt); //delays the entire program .2 seconds
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        		    }
        	});
        }
        jba[123].setBackground(Color.red); //sets the beginning red button to 123
        Container pane = new Container(); //makes a new container to hold all of my buttons
        pane.setLayout(new GridLayout(24, 34)); //sets it as a grid layout of 24 rows and 34 columns
        for (int i = 0; i < 34*24; i++) //runs to the size of this grid
        	pane.add(jba[i]); //adds all of the buttons
        panel2 = new JPanel(); //instantiates the panel to hold all my stuff
        panel2.setBackground (Color.red); //sets the background of everything as red
        panel2.add (inputLabel);
        panel2.add (clicksLabel);
        panel2.add (outputLabel); //these lines add all of my stuff to the panel
        panel2.add (resultLabel);
        panel2.add(pane);
        window.getContentPane().add (panel2); //this adds the panel to the content pane so it shows up	
        
        HelloRunnable timer = new HelloRunnable(); //makes a new HelloRunnable object, reference below for details      
        display(); //this displays the JFrame, below for details
        timer.run(); //runs the timer, reference subclass below for more information
    }
   
    /**
     * This class is for updating the game statistics. 
     * It opens a new stream, along with the normal
     * JFrame one, to count seconds, and then it updates the timer, the clicks, and the blocks destroyed.
     * @author 18sschiller
     */
    public class HelloRunnable implements Runnable 
    {
    	public int count = 0;
	   /**
	    * This program counts seconds (which are really 0.85 seconds each), 
	    * and every second, it refreshes the display with the new time, the total number of clicks, and the total
	    * number of blocks that were destroyed.
	    */
    	public void run() 
	    {
	    	while(window.isEnabled()) //runs while the window is open
			{
	    		count++;			
				resultLabel.setText(elapsedTime());
				clicksLabel.setText("          # of Clicks:  " + totalClicks);
				inputLabel.setText("# of Blocks Missed:  " + totalBlocks);
				resultLabel.repaint(); //these lines update all the game stats
				window.repaint(); 
				try {
					Thread.sleep(850); //this makes it update every second, or .85 seconds.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    }
    	/**
    	 * This is the toString method for the time. 
		 * It takes the amount of seconds and puts them in a pretty format.
    	 * @return str
    	 */
        public String elapsedTime() 
        {
        	String str = ""; int minutes = (int) (count / 60); int secs = (int) (count%60);
        	if(minutes<10)
        		str+="0";
        	str += minutes; str += ":"; 
        	if(secs<10)
        		str+="0";
        	str += secs;
        	return str;
        }
	}
    /**
     * This function displays the JFrame
     */
    public void display()
    {
       window.pack();
       window.setVisible(true);
    }
}
