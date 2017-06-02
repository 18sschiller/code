import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class NewDictionary extends JFrame
{
	private JPanel recpan; // panels to hold the displays
	private JTextArea menudisplay; // displays the print queue
	private ArrayList<Entry> masterList;
	
	public static void main(String[] args)
	{
		NewDictionary g = new NewDictionary();
	}
	
	public NewDictionary()
	{
		super("Dictionary"); // refers to parent constructor to set up JFrame with the name Printer
		makePanels();  // creates and initializes all of the JComponents of the GUI for display
		display(); // finalizes the display and puts up the display
		masterList = new ArrayList<Entry>();
		try { masterList = processData(readFile(new File("dict.txt")));
		}catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
	public static ArrayList<String> readFile(File file) throws FileNotFoundException 
	{
		Scanner input = new Scanner(file); //makes a new file scanner
		ArrayList<String> data = new ArrayList<String>(); //temporary string
		while (input.hasNextLine()) //checker to make sure it doesn't screw up
			data.add(input.nextLine()); //adds to the data
		input.close(); //closes the scanner stream
		return data; //returns all the data
	}
	
	public ArrayList<Entry> processData(ArrayList<String> data)
	{
		ArrayList<Entry> are = new ArrayList<Entry>();
		int k = Integer.parseInt(data.get(0));
		String str = "#############################################" + "\n--------------------------------Word List---------------------------------\n" + "#############################################\n";
		for (int i = 1; i <= k; i++){
			str += " " + data.get(i) + "\t";
			if(i % 3 == 0) str += "\n\n";
			are.add(new Entry(data.get(i), "Undefined.", (50+(((i-1)/3)*32)), ((i%3)-1)*87));
			if(i%3 == 0) are.get(are.size()-1).setWordCoStartX(174);
		}
		str += "\n#############################################" + "\n-------------------------------Definitions--------------------------------\n" + "#############################################";
		for( int i = 0; i < k; i++){
			str += "\n" + modString(data.get(i + k +1), 1);
			are.get(i).setDefinition(modString(data.get(i+k+1), 2));
			are.get(i).setPartOfSpeech(Integer.parseInt(modString(data.get(i+k+1),3)));
			are.get(i).setBeginDef(str.length()+are.get(i).getWord().length() + are.get(i).getDefinition().length());
		}
		menudisplay.setText(str);
		return are;
	}
	
	public String modString(String str, int justDef)
	{
		String[] a = str.split("##");
		if(justDef == 1){
			return a[0] + ":\n" + a[1] + "\n------------------------------------------------------------------------------";
		}
		else if(justDef == 3){
			return a[2];
		}
		return a[1];
	}

	private void makePopup(int num)
	{
        JFrame frame = new JFrame("" + masterList.get(num).getWord());
        frame.setResizable(false);
        frame.setLocation(370, 170);
        frame.addWindowFocusListener(new WindowAdapter(){public void windowLostFocus(WindowEvent e){e.getWindow().dispose();}});
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new TabbedPaneDemo(masterList, num), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
	}
	
	private void makePanels()
	{
		recpan = new JPanel (); // Creates the new JPanel for the print queue
	    recpan.setBorder ( new TitledBorder ( new EtchedBorder (), " " ) ); // generates pretty border around it with title
	    menudisplay = new JTextArea ( 18, 30 ); // makes the text area where the print queue will be displayed
	    menudisplay.setEditable ( false ); // set textArea non-editable
	    menudisplay.setLineWrap(true); // makes the text wrap around to the next line if needed
	    menudisplay.setHighlighter(null);
	    menudisplay.setEditable(false); // makes it so that user cannot edit the printer queue text box
	    recpan.add(new JScrollPane(menudisplay)); // adds the text area to the JPanel to be displayed
	    menudisplay.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e){
	        	System.out.println("clicked X:  " + e.getX() + "\tY:  " + e.getY());
	        	if(e.getY() > 50 && e.getY() <= masterList.get(masterList.size()-1).getWordCoStartY() + 15)
	        		for(int h = 0; h < masterList.size(); h++)
	        			if(e.getY() >= masterList.get(h).getWordCoStartY()-10 && e.getY() <= masterList.get(h).getWordCoStartY() + 25 && e.getX() >= masterList.get(h).getWordCoStartX() && e.getX() <= masterList.get(h).getWordCoStartX() + 87){
	        	        	makePopup(h);
	        				System.out.println(masterList.get(h).getWord());
	        				//menudisplay.select(me.getBeginDef(), me.getBeginDef()+1);   
	    }}}); //85, 175
	    JButton but = new JButton("Add Word"); // makes a submit button to enter in the new print job information
		but.addActionListener(new ActionListener(){ // creates the listener to tell the submit button what to do when clicked
			  public void actionPerformed(ActionEvent e){ // when it is clicked...
				  System.out.println("Button Pressed");		
				  String s = (String)JOptionPane.showInputDialog(null, "", "Dictionary Addition", JOptionPane.PLAIN_MESSAGE, null, null, "New Word");
				  if(s != null){
					  String d = (String)JOptionPane.showInputDialog(null, "", "Dictionary Addition", JOptionPane.PLAIN_MESSAGE, null, null, "Definition");
					  if(d != null) masterList.add(new Entry(s, d));
		}}});
		getContentPane().add(recpan, BorderLayout.CENTER); // adds the printer queue first as the top
		getContentPane().add(but, BorderLayout.SOUTH); // adds the submit button to the end of the GUI
	}
	
	private void display()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets default close operation to normal
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.out.println("Closed");
                filator();
                e.getWindow().dispose();
		}});
		pack(); // packs all of the things, i don't really know
		setVisible(true); // sets it visible.
	}	
	
	public void filator()
	{
		String str = "" + masterList.size() + "\n";
		Collections.sort(masterList, (p1, p2) -> p1.getWord().toLowerCase().compareTo(p2.getWord().toLowerCase()));
		for( Entry k : masterList)
			str += k.getWord() + "\n";
		for( Entry u : masterList)
			str += u.getWord() + "##" + u.getDefinition() + "##" + "1" + "##" + "b" + "##" + "c" + "\n";
		try { BufferedWriter output = new BufferedWriter(new FileWriter(new File("dict.txt")));
			output.write(str); //writes out the data
			output.close(); //closes the output
		}catch (IOException e) {e.printStackTrace();}
	}
	
	public class TabbedPaneDemo extends JPanel 
	{
	    public TabbedPaneDemo(ArrayList<Entry> masterList, int num) {
	        super(new GridLayout(1, 1));
	        this.setPreferredSize(new Dimension(350,130));
	        setResizable(false);
	        JTabbedPane tabbedPane = new JTabbedPane();
	        java.net.URL imgURL = TabbedPaneDemo.class.getResource("images/middle.gif");
	        ImageIcon icon = null;
	        try{
	        	icon = new ImageIcon(imgURL);
	        } catch(NullPointerException c){};
	        
	        
	        JComponent panel11 = new JPanel(false);
	        JLabel filler12 = new JLabel();
	        filler12.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 250, masterList.get(num).getDefinition()));
	        filler12.setHorizontalAlignment(JLabel.CENTER);
	        panel11.setLayout(new GridLayout(1, 1));
	        panel11.add(filler12);
	        tabbedPane.addTab("Definition", icon, panel11);
	         
	        JComponent panel2 = makeTextPanel("Part of Speech:  " + pOS(masterList.get(num).getPartOfSpeech(), "***"));
	        tabbedPane.addTab("Information", icon, panel2);
	        
	        JPanel panel3 = new JPanel(false);
	        panel3.setPreferredSize(new Dimension(30,10));
	        JTextArea posChange = new JTextArea(1,20);
	        JTextArea posChange2 = new JTextArea(1,20);
	        JTextArea posChange3 = new JTextArea(1,20);
	        JButton butforCh = new JButton("Submit");
	        butforCh.addActionListener(new ActionListener()
	        {
	        	  public void actionPerformed(ActionEvent e)
	        	  {
	        	    if(posChange.getText().length() > 4){
	        	    	
	        	    }
	        	    if(posChange2.getText().length() > 7){ //A small freshwater fish of the minnow family.
	        	    	masterList.get(num).setDefinition(posChange2.getText());
	        	    	filler12.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 250, masterList.get(num).getDefinition()));
	        	    	filator();
	        	    }
	        	    if(posChange3.getText().length() >= 8)
	        	    	System.out.println(posChange3.getText());
	        	    posChange.setText("");
	        	    posChange2.setText("");
	        	    posChange3.setText("");
	        	  }
	        });
	        panel3.add(new JLabel("Part of Speech:  "));
	        panel3.add(posChange);
	        panel3.add(new JLabel("Definition:  "));
	        panel3.add(posChange2);
	        panel3.add(new JLabel("Sentence:  "));
	        panel3.add(posChange3);
	        panel3.add(butforCh);
	        tabbedPane.addTab("Edit", icon, panel3); //panel3
	         
	        JComponent panel4 = makeTextPanel(
	                "Panel #4 (has a preferred size of 410 x 50).");
	        panel4.setPreferredSize(new Dimension(300, 100));
	        tabbedPane.addTab("Word List", icon, panel4);
	        add(tabbedPane);
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    }
	    
	    private String pOS(int num, String str)
	    {
	    	String hello = "";
	    	if(str.equals("***")){
		    	switch(num){
		    	case 1: hello = "Noun"; break;
		    	case 2: hello = "Verb"; break;
		    	case 3: hello = "Pronoun"; break;
		    	case 4: hello = "Adverb"; break;
		    	case 5: hello = "Adjective"; break;
		    	case 6: hello = "Conjunction"; break;
		    	case 7: hello = "Interjection"; break;
		    	case 8: hello = "Preposition"; break;
		    	default: hello = "Unknown";
		    	break;
		    }}
	    	return hello;
	    }
	    protected JComponent makeTextPanel(String text) {
	        JPanel panel = new JPanel(false);
	        JLabel filler = new JLabel(text);
	        filler.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 250, text));
	        filler.setHorizontalAlignment(JLabel.CENTER);
	        panel.setLayout(new GridLayout(1, 1));
	        panel.add(filler);
	        return panel;
	    }
	}
}
