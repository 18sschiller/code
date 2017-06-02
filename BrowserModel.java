import java.util.Stack;

/**
 * This class will gives specific instructions for the Home, Forward, and Back buttons. It will also control
 * what each of the hyper-links will do. The most important job of this class is to keep a record of the previous
 * links visited and be able to access the history in order to use the Back and Forward buttons. The history can
 * also become very cluttered easily, so double clicking the Home button will reset the entire history.
 * @author 18sschiller
 */
public class BrowserModel 
{
	BrowserView view; // creates the object that allows all of the magic stuff to happen
	Stack<Integer> previous; // holds all of the available previous lines for the Back button
	Stack<Integer> future; // stores all future positions available by Forward button
	Integer current; // stores the current position of the user by line
	
	/**
	 * Constructor. Sets up the instance data and sets everything to zero.
	 * @param view2
	 */
	public BrowserModel( BrowserView view2)
	{
		view = view2; // sets new BrowserView
		previous = new Stack<Integer>(); // sets stack for the previous links to zero
		future = new Stack<Integer>(); // sets the stack for the future links to zero
		current = 0; // sets the current position as zero
	}
	
	/**
	 * If possible, goes to the previous link that the user was at, and saves current location to future.
	 */
	public void back()
	{
		if(previous.size() > 0){ // if there are links to go back to...
			future.push(current); // adds current place to the future list
			current = previous.peek(); // sets the current place as the previous location
			view.update(previous.pop()); // updates the gui and removes the previous location from stack
		}
	}
	
	/**
	 * If possible, goes forward one link to the user's location before they went backward.
	 */
	public void forward()
	{
		if(future.size() > 0){ // if there is one to go to in the future stack...
			previous.push(current); // puts current location in the previous stack
			current = future.peek(); // sets current location as the next place in future stack
			view.update(future.pop()); // sets gui to future location and removes location from stack
		}
	}
	
	/**
	 * Returns user to the top of the web page, and stores as current location, and saves previous location.
	 * If home button is pressed twice, clears the memory.
	 */
	public void home()
	{
		if( current != 0) previous.push(current); // if it is not currently at the top, goes to the top
		else{ // if it is already at the top, that means it is a double click on the home button
			previous.clear(); // clears the previous stack
			future.clear(); // clears the future stack
		}
		view.update(0); // sets the gui as the top of the file
		current = 0; // sets current position as the top of the file
	}
	
	/**
	 * Follows the link to the next location and sets it as the current location, and stores the previous location.
	 * @param n
	 */
	public void followLink( int n)
	{
		if( current != n) previous.push(current); // if it is not currently there, put current position in stack
		view.update(n); // sets the GUI as the new link location
		current = n; // sets current position as the new position based on link
	}
	
	/**
	 * Determines if the Back button will work based on whether or not there are previous links available.
	 * @return previous.size
	 */
	public boolean hasBack()
	{
		return previous.size() > 0;
	}
	
	/**
	 * Determines if the Forward button is activated based on whether or not there are pages to go forward to.
	 * @return forward
	 */
	public boolean hasForward()
	{
		return future.size() > 0;
	}
}
