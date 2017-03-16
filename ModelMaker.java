import java.awt.Polygon;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This class will make a SinglyLinkedList and use it to create a shape from a list of points.
 * It also has the ability to evaluate every point and remove points from the shape in order
 * of importance. It also can do a few other things that you can read about more below.
 * 
 * @author 18sschiller
 *
 */

public class ModelMaker 
{
	LinkedList ll; // master list of all of the points
	String[] options = {"Apple", "Bone", "Butterfly", "Octopus", "Swirl"};
	
	/**
	 * Constructor for the ModelMaker. It will accept a file name from the
	 * user and read in from said file a list of 100 points and store it in
	 * a SinglyLinkedList as PointPluses.
	 * @param fn
	 */
	public ModelMaker(int fn)
	{
		ll = new LinkedList(); // instantiates the main list
		readFromFile(fn); // reads the coordinate info from file onto the LinkedList
	} 
	
	/**
	 * This function is so that the DrawShape class can directly change the file being used
	 * instead of creating a new object every time.
	 * @param fileName
	 */
	public void readFromFile(int fileName)
	{
		ll.clear(); // clears the existing LinkedList in case old points remain
		String hold = ""; // initializes a string for holding
		try{
			Scanner in = new Scanner(new FileReader(options[fileName] + ".txt")); // sets up scanner to read file chosen by user
			while(in.hasNext()){ // reads to the end of file
				hold = in.nextLine(); // reads the next line of file to string
				ll.insertFront(new PointPlus(Integer.parseInt(hold.substring(0, 3)), Integer.parseInt(hold.substring(4, hold.length()))));
			} // above, adds to the LinkedList a new PointPlus, made up of the coordinates received from the file
			in.close(); // the file has a format of XXX YYY, so this reads it in correctly
		} //I should catch a NumberFormatException, but I am the one who offers the files and know they are all properly formatted.
		catch (FileNotFoundException e) {e.printStackTrace();} //catches a FileNotFoundException
	}
	
	/**
	 * This class uses the SinglyLinkedList ll of Points to create a Polygon.
	 * @return Polygon
	 */
	private Polygon makeTheShape()
	{
		Node nd = ll.getHeadNode(); // sets the Node nd as the first node of the list
		PointPlus px = null; //initializes a PointPlus for later
		int[] x = new int[ll.size()]; // makes an array for x-coordinates
		int[] y = new int[ll.size()]; // makes an array for y-coordinates
		
		for (int i = 0; i < ll.size(); i++) { // runs to how many nodes there are
			px = (PointPlus) nd.getData(); // sets the temporary Point as current node to access data of Point properly
			x[i] = (int) px.getX(); // retrieves x-coordinate data from current Point
			y[i] = (int) px.getY(); // retrieves y-coordinate data from current Point
			nd = nd.getNext(); // iterates to the next node
		}
		return new Polygon(x, y, ll.size()); // returns a Polygon with all Points and proper size
	}
	
	/**
	 * Helper method for makeTheShape() because problems occurred when trying to reverse the orientation
	 * of the Polygon, so this flips the orientation clockwise ninety degrees by switching x and y.
	 * @return PolygonFlipped
	 */
	public Polygon creator()
	{
		Polygon p = makeTheShape(); // runs the method to make the Polygon
		return new Polygon(p.ypoints, p.xpoints, p.npoints); // flips the orientation
	}
	
	/**
	 * Helper method for sigFinder(). Runs the method as many times as the user input it for.
	 * @param i
	 */
	public void pixelator(int i)
	{
		for (int j = 0; j < (100 - i); j++)  // runs 100 - i times because if the user wants 30 points,
			sigFinder(); 					 // this program will remove 70 points, running 70 times.
	}
	
	/**
	 * Calculates the value of every Point in the Polygon and then erases the least important Point.
	 */
	private void sigFinder()
	{
		Node ka = ll.getHeadNode(); // sets Node ka as the first Node in the LinkedList
		int o = 0; // initializes counter to zero
		PointPlus oneBefore = null; // sets up random Point for later use
		PointPlus hol, j, ellen; // sets up all of these for the same reason
		if(ll.size() > 2){ // only runs this if there are still points left and there are at least 2 points
			while(o != -998){ // while this value is not met
				hol = (PointPlus) ka.getData(); // sets PointPlus hol as the current Node's data
				if(o == 0) // if it is the first time through
					hol.setImportance(valueCalc((PointPlus)ll.getTailNode().getData(), (PointPlus)ka.getData(), (PointPlus)ka.getNext().getData()));
				else{ // above, it will use the last node as the one before it because it needs a before and after reference
					try{
						ellen = (PointPlus) ka.getNext().getData(); // gets the data for the point right after it
					}
					catch(NullPointerException exception){ // if it is the last term, there won't be data for the next Node
						ellen = (PointPlus) ll.getHeadNode().getData(); // gets the data from the head Node if it is the last one
						o = -999; // ends the while loop
					}
					hol.setImportance(valueCalc(oneBefore, (PointPlus)ka.getData(), ellen)); // sets the importance of the point using the points around it
				}
				ka.setData(hol); // sets the current node as the new data with the Importance added to the PointPlus
				o++; // adds to the counter for basically no reason at all
				oneBefore = (PointPlus)ka.getData();  // sets the oneBefore as the current Node for the next cycle
				ka = ka.getNext(); // iterates to the next Node to start the cycle over again
			}			
			
			j = (PointPlus) ll.getHeadNode().getData(); // sets j as the first PointPlus in the LinkedList
	        double min = j.getImportance(); // sets that as the minimum importance
	        ka = ll.getHeadNode(); // sets ka as the head node again to reinitialize it.
	        
	        for(int f = 0; f < ll.size()-1; f++){ // this is a standard linear search for a minimum
	        	j = (PointPlus) ka.getNext().getData(); //sets j as the next node's data
	        	if(min > j.getImportance()) // if the minimum is greater than the one in question...
	        		min = j.getImportance(); // it will be set as the new minimum
	        	ka = ka.getNext(); // iterates to the next Node to repeat the cycle
	        }
	        
	        ka = ll.getHeadNode(); // resets ka as the first Node again
	        boolean uu = false; // sets random int to 0 to run a loop
	        while (ka != null && uu == false){ // will run while it hasn't reached min yet and there are still more
	            j = (PointPlus) ka.getData(); // sets j as the current node's PointPlus data
	            if (min == j.getImportance()){ // if the current data matches the minimum....
	            	recalibrate(ka); // runs the recalibrate() method to erase that node
	            	uu = true; // ends the loop
	            }
	            ka = ka.getNext(); // iterates to the next node
	        }
		}
	}
	
	/**
	 * Finds a specific node in the LinkedList and erases the node by removing the pointer to it.
	 * @param pos
	 */
	private void recalibrate(Node pos)
	{
		Node f = ll.getHeadNode(); // sets Node f as the first node in the LinkedList
		if(f == pos) // if the one being erased is the first node...
			ll.removeFront(); // erase the first node
		else{ // otherwise...
			while( f.getNext() != pos) // while it is not the one being erased
				f = f.getNext(); // iterate to the next node
			f.setNext(pos.getNext()); // if the one being erased is the next node, it will set the reference of
		} // the current node to the one after the next node and lose any pointer to the node being erased.
	}
	
	/**
	 * Calculates the importance of any given Point by referencing the Points around it.
	 * If these points make triangle LPR, The worth of Point P is calculated by the difference
	 * between the sums of the distance from P to the other two points and the distance between
	 * the two points themselves. Basically, it is calculating how much spatial value that specific
	 * point is adding to the Polygon.
	 * @param l
	 * @param p
	 * @param r
	 * @return importance
	 */
	private double valueCalc( PointPlus l, PointPlus p, PointPlus r)
	{
		return l.distance(p) + p.distance(r) - r.distance(l);
	}
}