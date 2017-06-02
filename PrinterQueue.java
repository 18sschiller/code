import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.PriorityQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * This class creates a GUI that shows a Printer Queue.
 * There are some people that are already waiting to print, but the user can add their own print jobs to the queue.
 * The queue is ordered on a priority basis, so a teacher has precedence over a student.
 * Every page printed takes two seconds, and there is a real-time clock for reference at the top to see.
 * @author 18sschiller
 *
 */
public class PrinterQueue extends JFrame
{
	private static final long serialVersionUID = 1L; // i don't know what this is
	private JPanel recpan, infoHold; // panels to hold the displays
	boolean isOpen = false; // runs the program until it is exited
	private JTextArea menudisplay; // displays the print queue
	private PriorityQueue<PrinterTask> tasks; // holds the data for the queue
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // stores the format of the time printed
	private int overallCount = 4; // counts how many print jobs have been completed
	private boolean ucount = false; // makes it so that printer updates every two seconds
	
	/**
	 * Main method to run the program.
	 * @param args
	 */
	public static void main(String[] args)
	{
		PrinterQueue g = new PrinterQueue();
		g.runRepeat();
	}
	
	/**
	 * Constructor for GUI. Sets up the display and initializes the list for printing.
	 */
	public PrinterQueue()
	{
		super("Printer"); // refers to parent constructor to set up JFrame with the name Printer
		makePanels();  // creates and initializes all of the JComponents of the GUI for display
		display(); // finalizes the display and puts up the display
		tasks = new PriorityQueue<PrinterTask>(); // initializes the list of print tasks in this list
		assignInitialTasks(); // sets up a few hard coded tasks so that the program starts with something
		menudisplay.setText(getPrintJobs()); // sets up the print queue and organizes the list, setting it up to be updated
	}
	
	/**
	 * Recursively runs update until the window closes.
	 * Updates both the clock and the printer queue
	 */
	public void runRepeat()
	{
		if(isOpen != true){ // the base case is the window closing
			ucount = !ucount; // reverses the boolean
			setTitle("Printer           " + sdf.format(Calendar.getInstance().getTime())); // sets the title of the JFrame as the current time
			if(ucount == false && !tasks.isEmpty()){ // if two seconds has elapsed and there are more printing tasks to do...
				PrinterTask i = tasks.remove(); // retrieves the next task and removes it from the queue
				if(i.getPages() > 1){ // if there are more than one pages left to print...
					i.setPages(i.getPages()-1); // sets it so that there is one less page; THIS LINE PRINTS THE PAGE
					tasks.add(i); // adds the task back if it has more pages
				} //  if there is 1 page left, the task is removed because the last page was printed
				menudisplay.setText(getPrintJobs()); // runs the update with the one less page or one less print job
			}
			if(tasks.isEmpty()) menudisplay.setText("Done."); // if there are no more print tasks, it will print out "Done." to the screen. Tasks can still be added after this occurs.
			try {Thread.sleep(1000);} // sleeps the loop for 1 second. The clock in the title will update every second and the print list will update every two.
			catch(InterruptedException e) {e.printStackTrace();} // mandatory catch clause for the system pause.
			runRepeat(); // recursively runs method again
		}
	}

	/**
	 * Prints the print queue and updates it every two seconds.
	 */
	private String getPrintJobs()
	{
		String str = " Name\t\tPage #\tTime";
		Calendar cal = Calendar.getInstance(); // retrieves an instance of the calendar
		PrinterTask pt; // initializes temporary printer task
		String[] kpt = new String[tasks.size()]; // initializes string array to hold them before printing them
		PriorityQueue<PrinterTask> ppo = new PriorityQueue<PrinterTask>(); // initializes a temporary queue to hold the tasks
		for (int i = 0; i < kpt.length; i++){ // runs to how many tasks there are
			pt = tasks.remove(); // removes the first tasks and holds it in the temporary
			if(i == 0){ // if it is the first task in the priority queue...
				kpt[i] = ("\n" + pt + "\t" + "Pending"); // sets the time as Pending
				for (int j = 0; j <= pt.getPages()%4; j++) // adds dots to the end of pending
					kpt[i] += "."; // it is based on how many pages there are left to print, so it changes each time
			}
			else kpt[i] = ("\n" + pt + "\t" + sdf.format(cal.getTime())); // sets all other rows as normal with the time next to it
			cal.set(Calendar.SECOND, cal.get(Calendar.SECOND)+(2*pt.getPages())); // changes the time to future time, with two seconds for every page
			ppo.add(pt); // adds the abandoned task to the new holder queue
		}
		tasks = ppo; // transfers the holder queue back to the original
		for (int i = 0; i < kpt.length; i++) // runs to how many tasks there are
			str += kpt[i]; // adds all the strings together into the menu display string
		return str; // returns the string
	}

	/**
	 * Makes and initializes the panels for display.
	 */
	private void makePanels()
	{
		recpan = new JPanel (); // Creates the new JPanel for the print queue
	    recpan.setBorder ( new TitledBorder ( new EtchedBorder (), "Queue" ) ); // generates pretty border around it with title
	    menudisplay = new JTextArea ( 18, 30 ); // makes the text area where the print queue will be displayed
	    menudisplay.setEditable ( false ); // set textArea non-editable
	    menudisplay.setLineWrap(true); // makes the text wrap around to the next line if needed
	    menudisplay.setEditable(false); // makes it so that user cannot edit the printer queue text box
	    menudisplay.setHighlighter(null); // sets it so that user cannot highlight text in printer queue because that was causing unexpected problems
	    menudisplay.setText("Name\t\tPage #\tTime"); // sets the beginning text as the label on the first line
	    recpan.add(new JScrollPane(menudisplay)); // adds the text area to the JPanel to be displayed
	    
	    JTextField jtf1 = new JTextField(15); // creates the text box for the user to input their name
		JTextField jtf2 = new JTextField(8); // creates text box for user to input the amount of pages to print
		String[] aiii = {"Student", "Teacher", "Principal", "President"}; // creates choices for occupation, which determines priority
		JComboBox<String> jtf3 = new JComboBox<String>(aiii); // makes the combo box with all of the choices
	    infoHold = new JPanel(); // makes the JPanel that contains all of the information retrievers
	    infoHold.setBorder(new TitledBorder(new EtchedBorder(), "  Name                                               Page #                   Occupation"));
		infoHold.add(jtf1, BorderLayout.EAST); // above line; makes border with labels as to what each box does
		infoHold.add(jtf2, BorderLayout.CENTER); // adds the input boxes so that
		infoHold.add(jtf3, BorderLayout.WEST);  // they are three in a row
		
		JButton but = new JButton("Submit"); // makes a submit button to enter in the new print job information
		but.addActionListener(new ActionListener(){ // creates the listener to tell the submit button what to do when clicked
			  public void actionPerformed(ActionEvent e){ // when it is clicked...
				  if(!jtf1.getText().equals("") || !jtf2.getText().equals("")){ // if both boxes are not empty
					  int k; // creates a temporary number to try to get a page number from the entry
					  overallCount++; // adds one more print job to the total number of print jobs done
					  if(jtf1.getText().equals("")) jtf1.setText("Unknown"); // if there is no name, make the name Unknown
					  if(!jtf1.getText().contains(".") && jtf1.getText().contains(" ")){ // if the name does not contain a . and has a space.
						  String[] al = jtf1.getText().split(" "); // it will take any two word names and split them into an array
						  al[1] = al[1].toUpperCase(); // it will make the last name capitalized
						  if(al[0].length() > 9) al[0] = jtf1.getText().substring(0, 9); // if the first name is over 9 characters, it will shorten it to 9
						  jtf1.setText(al[0] + " " + al[1].charAt(0) + "."); // sets the name as the first name with the first initial of the last name
					  }
					  if(jtf1.getText().length() > 12) jtf1.setText(jtf1.getText().substring(0, 12)); // if the name is over 12 characters, shorten to 12
					  try{k = Integer.parseInt(jtf2.getText());} // attempts to get a number out of the second text box for page number
					  catch(NumberFormatException exc){k = 1;} // if it cannot, it just sets it to a default 1 page
					  if(jtf3.getSelectedItem().equals("Student")) tasks.add(new PrinterTask(jtf1.getText(), 1000-tasks.size()-overallCount, k));
					  else if(jtf3.getSelectedItem().equals("Teacher")) tasks.add(new PrinterTask(jtf1.getText(), 2000-tasks.size()-overallCount, k));
					  else if(jtf3.getSelectedItem().equals("Principal")) tasks.add(new PrinterTask(jtf1.getText(), 3000-tasks.size()-overallCount, k));
					  else tasks.add(new PrinterTask(jtf1.getText(), 4000-tasks.size()-overallCount, k));
					  jtf1.setText(""); // above, adds the new task with their name, the priority, and the page number
					  jtf2.setText(""); // this clears the text written in the input boxes
				  }
			  }
		});
		
		getContentPane().add(recpan, BorderLayout.BEFORE_FIRST_LINE); // adds the printer queue first as the top
		getContentPane().add(infoHold, BorderLayout.CENTER); // adds the input boxes in the middle
		getContentPane().add(but, BorderLayout.SOUTH); // adds the submit button to the end of the GUI
	}
	
	/**
	 * Sets up the display by initializing the main parts of the JFrame
	 */
	private void display()
	{
	    addWindowListener(new WindowAdapter(){ // adds a window listener
            public void windowClosing(WindowEvent e){ // listens for the close of the screen
            	isOpen = true; // sets boolean so the loop at the top knows to stop running
                e.getWindow().dispose(); // disposes the window
            }
        });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets default close operation to normal
		pack(); // packs all of the things, i don't really know
		setVisible(true); // sets it visible.
	}
	
	/**
	 * Hard codes a few initial printer tasks so the user has something to start with.
	 */
	private void assignInitialTasks()
	{
		tasks.add(new PrinterTask("Simon S.", 1000, 7));
		tasks.add(new PrinterTask("Mrs. Fisher", 2000, 10));
		tasks.add(new PrinterTask("Jason S.", 999, 3));
		tasks.add(new PrinterTask("Donald Trump", 4000, 18));
		tasks.add(new PrinterTask("Mr. Steinberg", 1999, 2));
		tasks.add(new PrinterTask("Principal", 3000, 5));
	}
}
