import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.awt.Color;

/**
 * 
 * @author 18sschiller
 *
 *	This class generates a GUI that will display a Polygon read in from file.
 *	It will take user input through MouseListeners to determine how many points
 *	of the given Polygon will be displayed, in order of importance.
 *
 *	NOTE: make a button that will rotate the image used
 */
public class DrawShape extends JFrame 
{
	private static final long serialVersionUID = 1L; // mandatory serialization
	public static int WIDTH = 700; // constant width
	public static int HEIGHT = 700; // constant height
	public int fileName; // takes user input for file name from user
	public int pointAmount; // stores the amount of points determined by user
	public boolean bll = false; // toggles the strobe effect
	public ModelMaker m; // makes the ModelMaker object to create the shape

	public static void main(String[] args) 
	{
		Object[] options = {"Apple", "Bone", "Butterfly", "Octopus", "Swirl"}; // all of the file options available for the user
		int x = JOptionPane.showOptionDialog(new JFrame(),"Which picture " + "would you like to use?", "Pixelator", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[4]);		
		if(x == -1) x = 1; // if the user cancels out, it will still provide an acceptable input
		DrawShape drawing = new DrawShape(x); // makes a new DrawShape object, passing in the file name
		drawing.setVisible(true); // makes the GUI visible
	}
	
	/**
	 * Sets the amount of points the Polygon will be drawing.
	 * @param am
	 */
	public void setAm( int am)
	{
		this.pointAmount = am;
	}
	/**
	 * Sets the file name from the user input, and also resets ModelMaker to use that file.
	 * @param str
	 */
	public void setFn( int str)
	{
		this.fileName = str;
		m = new ModelMaker(str);
	}
	
	/**
	 * Constructor for DrawShape. Makes the GUI and sets the Listeners.
	 * This method will also run the paint() function, and will set the
	 * necessary instance data for the rest of the program to use.
	 * @param t
	 */
	public DrawShape(int x) 
	{
		super("Picture"); // runs the constructor for JFrame, which will run the paint() method
		setAm(100); // sets the initial amount of points in the Polygon as 100
		setFn(x); // sets the file that will be used as the file the user selected
		setSize(WIDTH, HEIGHT); // sets the size of the JFrame as the constant width and height
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets the native method close as regular
		getContentPane().setBackground(Color.BLACK); // sets the background color as black
		
	    TheListener dlsn = new TheListener(); // initializes my custom dual-action Mouse/MouseMotion Listener
	    addMouseListener (dlsn); // adds the version of the listener for just clicks
	    addMouseMotionListener(dlsn); // adds the version for the mouse motion to the JFrame
	}

	/**
	 * Paint method that will draw all my stuff on the JFrame.
	 * This will be called every time the method repaint() is called.
	 * @param g
	 */
	public void paint(Graphics g) 
	{
		super.paint(g); // calls the super, which is the JFrame paint() method that will do a good job initializing all the stuff
        Graphics2D g2 = (Graphics2D) g; // i don't really know what this does, but it makes all my drawing work
        Color[] carr = {Color.darkGray, Color.blue, Color.green, Color.YELLOW, Color.ORANGE, Color.red, Color.magenta, Color.pink, Color.white, Color.BLUE, Color.cyan, Color.CYAN, Color.lightGray, Color.GREEN, Color.MAGENTA, Color.orange, Color.yellow, Color.pink, Color.PINK, Color.RED};
        for(int y = 0; y < 9; y++){ // uses color array above to draw 9 evenly spaced rectangles to create a color meter for points
        	g2.setColor(carr[y]); // sets the color as the next color in the array (to make a rainbow)
        	g2.fillRect(20+(y*28), HEIGHT-60, 28, 40); // fills the rectangle, starting 20 pixels from the left of the JFrame, 
        } // each box is 28 pixels wide and 20 pixels tall
        g2.drawString("Number of Points", 100, HEIGHT-70); // writes a string over top of the slider to tell user what it is
        g2.drawString("0", 31, HEIGHT-34); // labels the lowest box on the slider as zero points, even though the lowest is three points
        g2.setColor(Color.darkGray); // sets the color as dark gray to contrast the white on the other side
        g2.fillRect(WIDTH-100, HEIGHT-40, 90, 20); // makes a rectangle for the Toggle Strobe button on the other side of the screen
        g2.fillRect(WIDTH-100, HEIGHT-65, 90, 20); // creates rectangle to change the file being used
        g2.setColor(Color.black); // makes the color black again for the labels
        g2.drawString("Toggle Strobe", WIDTH-92, HEIGHT-25); // writes the Toggle Strobe label to turn on and off the color changing
        g2.drawString("Change Picture", WIDTH-98, HEIGHT-51);
        g2.drawString("100", 247, HEIGHT-34); // labels the last box on the slider as 100
        
        m.readFromFile(fileName); // resets the ModelMaker so it has all of the points again to recalculate
        m.pixelator(pointAmount); // runs the pixelator which will remove the unwanted points
        g2.setColor(Color.red); // sets the default color to red to make the shape
        if(bll == false){ // if the strobe effect is on
            Random r = new Random(); // creates a new random number for the strobe effect
        	g2.setColor(carr[r.nextInt(20)]); // randomly picks a color from the color array
        }
        g2.fillPolygon(m.creator()); // makes the Polygon and displays it
	}
	
	/**
	 * This subclass is a listener for the slider and the various buttons on the
	 * screen that I didn't feel like making as regular buttons and sliders.
	 * It will repaint the JFrame if the mouse is dragged or clicked in various
	 * places on the screen.
	 * @author 18sschiller
	 *
	 */
	private class TheListener implements MouseListener, MouseMotionListener
    {
		/**
		 * Sets the amount of points left on the Polygon to a new amount and repaints the JFrame
		 * @param numb
		 */
		public void refactor(int numb)
		{
			setAm(numb);
			repaint();
		}
		/**
		 * Checks if the mouse is currently on the slider and where, and then
		 * determines what to do from there.
		 * 
		 * If it is in the first 80% of the slider, it will be incrementing the points at a rate of
		 * 3 points for every 20 pixels. For the next 8% of the slider, it will be incrementing at a
		 * rete of 10 points for every 11 pixels. For the last 12% of the slider, it will increment
		 * at a rate of 5 points for every 3 pixels. This is because as there are more of the less
		 * relevant points, it is harder to notice to an observer.
		 * 
		 *  0% to  80% - 20 pixel :  3 points  ,  6.66 pixels/point
		 * 80% to  88% - 10 pixel : 11 points  ,  0.91 pixels/point
		 * 88% to 100% -  3 pixel :  5 points  ,  0.60 pixels/point
		 * @param xco
		 * @param yco
		 */
		public void checkIfOn(int xco, int yco)
		{
			if(xco > 272 || xco < 20)
				System.out.print(""); // do nothing if it is not on the slider
			else if(xco > 20 && xco < 220)
				refactor((((xco-20)*3)/20)+3); // decides a value based on chart in Javadoc
			else if(xco > 220 && xco < 242)
				refactor((((xco-220)*10)/11)+30); // decides a value based on chart in Javadoc
			else if(xco > 242 && xco < 272)
				refactor((((xco-242)*5)/3)+50); // decides a value based on chart in Javadoc
		}
		
		/**
		 * When mouse is pressed, checks if it is over any of the other two buttons.
		 * One of the buttons will rotate the image being used, and the other will toggle
		 * the strobe color function, turning it on and off.
		 */
	    public void mousePressed (MouseEvent event)
	    {
			if(event.getX() > WIDTH-100 && event.getX() < WIDTH-10){ // if it within the width of the two buttons...
				if(event.getY() > HEIGHT-40 && event.getY() < HEIGHT-20){  // if it is the height of the Toggle Strobe button...
					if(bll==true) bll = false; // if it is currently off, turn it on
					else bll = true; // if it is currently on, turn it off
				}
				if(event.getY() > HEIGHT-65 && event.getY() < HEIGHT-45){ // if it is the height of the file changer button...
					if(fileName >= 4) fileName = 0; // if it is the last file in the array, go to the beginning
					else fileName++; // otherwise, go to the next file
					refactor(100); // repaint it with all 100 points
				}		
			}
	    }
		/**
		 * When mouse is dragged, checks if it is on the slider so it knows to run the point remover.
		 */
		public void mouseDragged(MouseEvent arg0)
		{
			if(arg0.getY() > HEIGHT-60 && arg0.getY() < HEIGHT-20) // if it is at the same y-value of the slider
				checkIfOn(arg0.getX(),arg0.getY()); // checks where it is on the slider to remove points
		}
		/**
		 * When mouse is clicked, checks if it is on the slider so it knows to run the point remover.
		 */
		public void mouseClicked(MouseEvent arg0) 
		{
			if(arg0.getY() > HEIGHT-60 && arg0.getY() < HEIGHT-20) // if it is the same height as the slider
				checkIfOn(arg0.getX(),arg0.getY()); // runs code to check where it is on the slider
		}
		/**
		 * All methods below this point are inherited and unused methods.
		 */
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		public void mouseMoved(MouseEvent arg0) {}
    }
}