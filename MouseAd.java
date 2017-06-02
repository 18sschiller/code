import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * This class creates a button listener for every single button,
 * that has the same jobs: to count if there is a click, check
 * to see whether the user wins, moves the red button if the user
 * is close to it, and calculate a new and reasonable place for the
 * red button if it has to be moved.
 * @author 18sschiller
 *
 */
public class MouseAd implements MouseListener
{
	public static int spec = 123; //gets the position of the red button
	public static String code = "";
	public static int sleeper = 100;
	JButton jba[]; //holds all of the buttons
	int position; //holds the position of current button in question
	public static int whereRed = 0; //gets where the red one is
	
	/**
	 * Instantiates the new mouse listeners,
	 * by setting the array of buttons and the position in which it is referring.
	 * @param jba
	 * @param pos
	 */
	public MouseAd( JButton[] jba, int pos)
	{
		setButt(jba);
		setPos(pos);
	}
	/**
	 * This runs if the mouse enters a button.
	 * It will check if it is within a block of the red button and if it is,
	 * it will move the red button somewhere else.
	 */
    public void mouseEntered(MouseEvent e) 
        {
    	   if(UnClickableButton.cheatUsed == false){
    	   if(position == spec) //if you are over the red button
    		   if(!(UnClickableButton.totalBlocks>=815))
    		   {
	   	  	   		jba[spec].setBackground(Color.white); //sets the red button to white
		    		spec = newPlace(e.getXOnScreen(), e.getYOnScreen(), jba); //finds the next red button
		    		if(position<34 || position%34 == 0 || (position-33)%34 == 0 || position > 781) //if the button is on an edge
					{blacker(-1, jba); blacker(1, jba); blacker(35, jba); blacker(34, jba); blacker(33, jba); blacker(-33, jba); blacker(-34, jba); blacker(-35, jba); }
		    		UnClickableButton.totalBlocks++; //adds to the total blocks destroyed
    		   }   
    	   if(position-34 == spec || position+1 == spec || position-1 == spec || position+34 == spec) //if its right above or below or to the sides
    	   {
    		   try {Thread.sleep(sleeper);} catch (InterruptedException e1) {e1.printStackTrace();} //sleeps for a very short time
	  	    	jba[spec].setBackground(Color.white); //sets the red button to white
	  	  	    if(UnClickableButton.totalBlocks>=815) //if this is the last red block
	  	  	    {
	  	  	    	jba[spec].setBackground(Color.green);
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
	    		spec = newPlace(e.getXOnScreen(), e.getYOnScreen(), jba); //finds the next red button
	    		if(position<34 || position%34 == 0 || (position-33)%34 == 0 || position > 781) //if the button is on an edge
				{blacker(-1, jba); blacker(1, jba); blacker(35, jba); blacker(34, jba); blacker(33, jba); blacker(-33, jba); blacker(-34, jba); blacker(-35, jba); }
				UnClickableButton.totalBlocks++; //adds to the total blocks destroyed
    	   }
    	   else if(position-33 == spec || position-35 == spec || position+33 == spec || position+35 == spec) //if its on a diagonal
    	   {
    		   try {Thread.sleep(sleeper);} catch (InterruptedException e1) {e1.printStackTrace();} //sleeps for a bit longer
	  	    	jba[spec].setBackground(Color.white); 
	  	  	    if(UnClickableButton.totalBlocks>=815) //does the same thing as above
	  	  	    {
	  	  	    	jba[spec].setBackground(Color.green);
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
	    		spec = newPlace(e.getXOnScreen(), e.getYOnScreen(), jba);
				if(position<34 || position%34 == 0 || (position-33)%34 == 0 || position > 781) //if the button is on an edge
				{blacker(-1, jba); blacker(1, jba); blacker(35, jba); blacker(34, jba); blacker(33, jba); blacker(-33, jba); blacker(-34, jba); blacker(-35, jba); }
	    		UnClickableButton.totalBlocks++;
    	   }
        }}
	/**
	 * Runs if the mouse is clicked inside a button.
	 * It adds to the total number of clicks, sets the new button background to blue,
	 * and does something special if you win.
	 */
    public void mouseClicked(MouseEvent arg0) 
		{
    		cheatCode();
    		System.out.println(code);
			UnClickableButton.totalClicks++; //adds to the total number of clicks
			if(jba[position].getBackground().equals(Color.green) || position == whereRed) //IF THE USER CLICKS THE RED BUTTON AND WINS
			{
				for (int i = 0; i < 816; i++) 
				{
					if(i%4 == 0)
						jba[i].setBackground(Color.PINK);
					else if(i%4 == 1)
						jba[i].setBackground(Color.CYAN); ///////////////////////////THIS IS ALL A SECRET, ONLY FOR THE WINNERS
					else if(i%4 == 2)
						jba[i].setBackground(Color.GREEN);
					else
						jba[i].setBackground(Color.ORANGE);
				}
				String s = "Congratulations. You've wasted a total of " + UnClickableButton.resultLabel.getText() + " on this stupid game. You shoud be proud of yourself. <br>What are you going to do now with your life? Do you feel a new sense of purpose? <br>Do you feel good about yourself that you just wasted a part of your life trying to beat a game that was designed to waste your time? <br>I hope eventually you decide to one day turn your life around and spend your time on something worthwhile. <br>I pity you. You are worthless. I hope you have a great day and thank you for playing!";
				int res = 1;
				res = JOptionPane.showOptionDialog(null, "<html><div style='text-align: center;'>" + s + "</html>", "You Won!!!", JOptionPane.DEFAULT_OPTION,
				        JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(res==-1)
					res = JOptionPane.showOptionDialog(null, "<html><div style='text-align: center;'>" + "What the hell is wrong with you? <br>There was a completely reasonably placed OK button there <br>and you just went ahead a used the exit button. <br>Do you have any respect?<br>Don't take your anger out on my program." + "</html>", "You Won!!!", JOptionPane.DEFAULT_OPTION,
					        JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(res==-1)
					res = JOptionPane.showOptionDialog(null, "<html><div style='text-align: center;'>" + "You disgust me. <br>I am done wasting my time on you." + "</html>", "You Won!!!", JOptionPane.DEFAULT_OPTION,
					        JOptionPane.PLAIN_MESSAGE, null, null, null);
				UnClickableButton.window.dispose();
			}
			else
				if(!(jba[position].getBackground().equals(Color.white)))
					jba[position].setBackground(Color.BLUE); //sets the new background to blue for clicked buttons
		}
    /**
     * Checks to see if you are using the cheatcode.
     */
    public void cheatCode()
    {
    	Robot bot;
		try {
			bot = new Robot();
			bot.delay(12);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(position == 0)
			code += "0";
		else if(position == 782)
			code += "1";
		else if(position == 815)
			code += "2";
		else if(position == 33)
			code += "3";
		else if(position > 200)
			code = "";
		else
			code += "xxxxx";
		System.out.println(code + "\t" + position);
		if(code.equals("0123"))
		{
			UnClickableButton.cheatUsed = true;
			jba[spec].setBackground(Color.green);
		}
    }
	/**
	 * This method randomly decides on where the next red button should be based on
	 * mouse proximity, button availability, and if it is on an edge.
	 * It starts with very specific requests for the new spot, but becomes less and less
	 * demanding as the availability of buttons decreases.
	 * @param mouseX
	 * @param mouseY
	 * @param jba
	 * @return newSpec
 	*/
	public static int newPlace(int mouseX, int mouseY, JButton[] jba)
		{
			int newSpec = -999;
			int maybe;
			Random r = new Random(mouseX*mouseY); //randomizes based on where mouse is
			for (int i = 0; i < 816-UnClickableButtonRunner.used.size(); i++) //runs to the number of buttons minus how many have been used
			{
				maybe = r.nextInt(816); //randomizes one of the buttons
				if(!(UnClickableButtonRunner.used.contains(maybe))) //if it was already used, it skips this
					if(jba[maybe].getLocationOnScreen().x < mouseX-50 || jba[maybe].getLocationOnScreen().x > mouseX+50) //if it is outside of 50 pixels on the x
						if(jba[maybe].getLocationOnScreen().y < mouseY-50 || jba[maybe].getLocationOnScreen().y > mouseY+50) //if it is outside of 50 pixels on the y
						{
							newSpec = maybe; //sets it as the new button
							UnClickableButtonRunner.used.add(maybe); //adds it to the arraylist of used buttons
							whereRed = maybe; //sets this random static int to the new button, idk why this exists
							jba[maybe].setBackground(Color.red); //sets the new red buttons background to red
							i = 1000; //ends the loop
							if(newSpec<34 || newSpec%34 == 0 || (newSpec-33)%34 == 0 || newSpec > 781) //if the button is on an edge
							{
								jba[maybe].setBackground(Color.white); //sets the new red button to white
								UnClickableButton.totalBlocks++; //adds another to the used blocks
								newSpec = newPlace(mouseX, mouseY, jba); //runs this whole method again
							}}}				
			if(newSpec == -999) //if the last for-loop didn't find us a new red button
				for (int i = 0; i < 816; i++) //runs the whole thing again
					if(jba[i].getBackground().equals(Color.black)) //if the space isn't used yet
						if(i<34 || i%34 == 0 || (i-33)%34 == 0 || i > 781) //if it is on the edge
						{
							newSpec = i; //this one will do
							UnClickableButtonRunner.used.add(i); //adds this to the used list
							whereRed = i; //sets this thing to the new red button
							jba[i].setBackground(Color.red); //makes it red
							i = 1000; //ends the loop
						}		
			if(newSpec == -999) //if it still hasn't found a new red button
				for (int i = 0; i < 816; i++) //run the whole thing again
					if(jba[i].getBackground().equals(Color.black)) //if the button hasnt been used
					{
						newSpec = i; //this one is fine
						UnClickableButtonRunner.used.add(i); //adds it to the used list
						whereRed = i;
						jba[i].setBackground(Color.red); //makes it red
						i = 1000; //ends the loop
					}
			if(newSpec == -999) //if nothing else works
				newSpec = whereRed; //that's it then. that was the last spot.
			return newSpec; //returns the new spot
		}
	/**
	 * Sets the button array in the class to whichever is passed in.
	 * @param b
	 */
	public void setButt(JButton[] b)
	{this.jba = b;}
	/**
	 * Sets the position of the class to position passed in.
	 * @param p
	 */
	public void setPos(int p)
	{this.position = p;}
	/**
	 * Makes all of the buttons surrounding the new red button black 
	 * if they were turned blue by user.
	 * @param x
	 * @param jba
	 */
	public static void blacker(int x, JButton jba[]){
			if(jba[spec+x].getBackground().equals(Color.BLUE))
				jba[spec+x].setBackground(Color.black);}
	//These are all empty methods that the MouseListener interface forced me to have.
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
} 