import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Maker 
{	
	public JFrame fri;
	/**
	 * This is the constructor for the GUI Maker. It takes in a JFrame and
	 * sets all of the desired conditions, and then creates the first array
	 * of bars with random heights in a random order.
	 * @param f
	 * @param ar
	 */
	public Maker(JFrame f)
	{
		JFrame frame = f; // makes a new frame
        frame.setBackground(Color.black); //sets the background black
        frame.setResizable(false); //sets it so it cannot be resized
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close
        frame.setSize(414, 350); //sets the size
        frame.setLocation(480, 209); //(1366-width)/2 = perfectly centered
        frame.setVisible(true); //sets visible
		DrawPane p = new DrawPane(); //draws the first pane
        frame.add(p); //adds it to the frame
	}
	/**
	 * Sorts the rectangles using selection sort.
	 * @param arr
	 * @param i
	 * @return arr
	 */
	public ArrayList<Rectangle> selectionSort(ArrayList<Rectangle> arr, int i)
	{
		int index = i; //runs as if the paint function is the enclosing for-loop
        for (int j = i + 1; j < arr.size(); j++) //runs every term past the one it is currently on
        	if(arr.get(j).getHeight() < arr.get(index).getHeight()) //if any term later than it is smaller than it...
				index = j; //that one is the new smallest
        Rectangle smaller = new Rectangle(arr.get(i).xstart,5,arr.get(i).width,5); //makes a temporary new rectangle
        smaller.setAll(arr.get(i));         // temp = a
        arr.get(i).setAll(arr.get(index));  // a = b
        arr.get(index).setAll(smaller);     // b = temp
        return arr;
	}
	/**
	 * Sorts the rectangles using insertion sort.
	 * @param arr
	 * @param i
	 * @return
	 */
	public ArrayList<Rectangle> insertionSort(ArrayList<Rectangle> arr, int i)
	{
		Rectangle t = new Rectangle(arr.get(i).getXstart(),arr.get(i).getYstart(),arr.get(i).getWidth(),arr.get(i).getHeight());
		int j; //sets a temporary rectangle above
		for(j = i -1; j >= 0 && t.getHeight() < arr.get(j).getHeight(); j--)
		{//runs from term right before current or until it is in the right spot
			arr.get(j+1).setAll(arr.get(j)); //sets it in the right spot
		}
		arr.get(j+1).setAll(t); //sets the one it left as the one it replaced
		return arr;
	}
	/**
	 * Sorts the rectangles using bubble sort.
	 * @param arr
	 * @param i
	 * @return
	 */
	public ArrayList<Rectangle> bubbleSort(ArrayList<Rectangle> arr, int i)
	{
		if(i <= arr.size()) //runs only if it is less than or equal to size of array
		{
			Rectangle t = new Rectangle(arr.get(i).getXstart(),arr.get(i).getYstart(),arr.get(i).getWidth(),arr.get(i).getHeight());
			int j; //sets a temporary rectangle
			for(j = 1; j < arr.size(); j++) //runs to size from 1
				if(arr.get(j-1).getHeight() > arr.get(j).getHeight())
				{ //if the term before j is bigger than the term
					t.setAll(arr.get(j-1));				// temp = a
					arr.get(j-1).setAll(arr.get(j));    // a = b
					arr.get(j).setAll(t);				// b = temp
				} //classic switcharoo
		}
		return arr;
	}
	
	/**
	 * Runs the switch function so that it moves.
	 * @param i
	 * @param choice
	 * @param delay
	 */
	public void doAgain(int i,int choice, double delay)
	{
		ArrayList<Rectangle> arr = new ArrayList<Rectangle>(); //makes an ArrayList of Rectangles
        try {Thread.sleep((int)(delay * 1000));} catch (InterruptedException e) {e.printStackTrace();} //pause so user can see
        try {arr = Rectangle.readFile();} catch (FileNotFoundException e) {e.printStackTrace();} //reads in all the rectangle positions
        if(choice == 0) //this all has to do with a JOptionPane over on the driver, you can add more sorts if you want.
        	arr = selectionSort(arr, i); //runs selection sort
        else if(choice == 1)
        	arr = insertionSort(arr, i); //runs insertion sort
        else if(choice == 2)
        	arr = bubbleSort(arr, i); 	// runs bubble sort
        fri = new JFrame("Bars - #" + (i+1)); //sets a new JFrame for each new display
		fri.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //regular stuff
        fri.setSize(414, 350); //sets size
        fri.setLocation(480, 209); // remember, (1366-width)/2 is perfectly centered
        fri.setVisible(true); 
        fri.setBackground(Color.black); //all the normal stuff
        fri.setResizable(false);
        
        fri.add(new DrawNewThing(arr)); //adds the paint component and paints it
        Rectangle.fileToString(arr); //writes out the newly switched rectangles to file
	}
	/**
	 * This draws the original display and randomly makes bars.
	 * @author 18sschiller
	 *
	 */
	class DrawPane extends JComponent
    {
        public void paintComponent(Graphics g) // I STILL HAVE NO IDEA WHAT THIS REALLY IS
        {
	        	ArrayList<Rectangle> arr = new ArrayList<Rectangle>(); //makes an ArrayList of Rectangles
	        	int tallest = 0, shortest = 300; //sets the original tallest and shortest
	        	int shortx = 0, tallx = 0, height = 0; //this is just some changeable data
	        	Random generator = new Random(); //makes a randomizer
	        	g.setColor(Color.black); //makes the background black
	        	g.fillRect(0, 0, 414, 400); //fills in the background
	        	g.setColor(Color.blue); //sets the painters color to blue
	        	int x = 9; //sets the gap space to 9
	    		for( int count = 0; count < 10; count++	)
	    		{ //runs to size of list
	    			height = generator.nextInt(295) + 1; //generates a random height
	    			g.fillRect(x, 300-height, 30, height); //fills the rectangle of the bar
	    			arr.add(new Rectangle(x, 300-height, 30, height)); //adds it to the list
	    			if( height > tallest){
	    				tallx = x;
	    				tallest = height; //this just keeps track of the tallest and shortest bar
	    			}
	    			if( height < shortest){
	    				shortx = x;
	    				shortest = height;
	    			}
	    			x = x + 39; //puts the space in between each Rectangle
	    		}
	    		g.setColor(Color.red); //fills in the tallest as red
	    		g.fillRect(tallx, 300-tallest, 30, tallest);
	    		g.setColor(Color.yellow);  // fills in the shortest as yellow
	    		g.fillRect(shortx, 300-shortest, 30, shortest);
	            Rectangle.fileToString(arr); //writes all rectangles out to file
         }
     }
	/**
	 * This creates a new window for each new iteration of the sort.
	 * @author 18sschiller
	 *
	 */
	class DrawNewThing extends JComponent
    {
	 	public ArrayList<Rectangle> arr; //sets instance data
	 	public DrawNewThing(ArrayList<Rectangle> art)
	 	{
	 		arr = art; //makes it so paint component has transferable data
	 	}
        public void paintComponent(Graphics g) //someone please tell me what this is
        {
        	g.setColor(Color.black); //sets background to black
        	g.fillRect(0, 0, 414, 400); //fills in the background
        	g.setColor(Color.blue);
    		for (int i = 0; i < arr.size(); i++) //this for loop makes all of the bars on file
    			g.fillRect(arr.get(i).getXstart(), arr.get(i).getYstart(), arr.get(i).getWidth(), arr.get(i).getHeight());
    		Rectangle nn = Rectangle.findOther(arr); //this finds the shortest bar
    		g.setColor(Color.yellow); // this sets the shortest bar as yellow
    		g.fillRect(nn.getXstart(),nn.getYstart(),nn.getWidth(),nn.getHeight());
    		Rectangle mm = Rectangle.findOne(arr); //this finds the tallest bar
    		g.setColor(Color.red); //this sets the tallest bar as red
    		g.fillRect(mm.getXstart(),mm.getYstart(),mm.getWidth(),mm.getHeight());
        }
    }
}
