import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Please do not look at this atrocity. It is big and it is convoluted and it is not neat, but it WORKS. 
 * Make sure to explore all of the features of this GUI, including the About tab and the Owner tab in the Info panel.
 * Please do not try to find the administrator password and reset my CookBook. There is no backup file.
 * 
 */

@SuppressWarnings({ "serial", "unused" })
public class CookBook extends JPanel
{	
	private ArrayList<Recipe> recipe;
	private String owner;
	private String lastupdate;
	private String saverr;
    JList<String> list;
	JButton selectButton;
	DefaultListModel<String> model;
	ArrayList<String> jl;
	int holder =  0;
	private ArrayList<Recipe> tempr;
	private int WIDTH = 800;
    private int HEIGHT = 500;
    private int aaaa = 0;
    private int makesure = 0;
    private int makesuree = 0;
    private int clearerone = 0;
    private int clearertwo = 0;
    ArrayList<String> listofcountries; //alphabetizes the erasor tab
    private JFrame f, aboutt, ownerr, contactt, resett, iframe, fn; //all the frames used
    private JPanel panel, MyPanel1, pancats, recpan, panan, panan2, check, jpn, hsub, refpan, aboutpanel, ownerpanel, resetpanel, contactpanel, contentPanef;
    private JLabel picLabel, label, jbl, thanks, thank2s, thank3s, lb, ql, qlb, qlc; //lotsa labels
    private JSlider slider; //slider for the submit
    private JButton x2, x3, x4, subr, x6, soup, salad, fish, chicken, dessert, breakfast, burger, vegan, kosher, glutenFree, submit, refresh, sub;
    private JCheckBox m1, m2, m3, m4, m5, m6, m7, m8, m9, m10; //all the individual checkboxes for the submit
    private BufferedImage myPicture; //background image
    private JTextArea menudisplay, menuudisplay, menuuudisplay; //jtextareas for the jtexts
    private JTextField input1i, input2i, jtf, jtff; //jtextfields used for inputs
    private JPasswordField jk, kjv; //password fields used for passwords.
    private Font font = new Font("Courier", Font.BOLD, 12); //regular font that is used a lot.
    private JColorChooser tcc; //for the background setter, not in use right now.
    JCheckBox[] mm = { m1, m2, m3, m4, m5, m6, m7, m8, m9, m10 }; //check boxes for submitting recipes.
    private Color b = new Color(210, 180, 140); //regular color, used a lot
    
	/**
	 * Constructor.
	 * @param Hello
	 * @param Hey
	 * @param Hi
	 * @throws IOException
	 */
	public CookBook(ArrayList<Recipe> Hello, String Hey, String Hi) throws IOException
	{
		setRecipe(Hello);
		setOwner(Hey);
		setLastupdate(Hi);
		
	     f = new JFrame (Hey + "'s CookBook                                                                                                                                                  Last Updated: " + Hi);
	     f.setLocation(10, 10);
	     getPanels();
	     f.getContentPane().add( MyPanel1, "East");
	     addLabels();
	     f.getContentPane().add(label);
	     f.getContentPane().add(thanks);
	     f.getContentPane().add(thank2s);
	     f.getContentPane().add(thank3s);
	     f.getContentPane().add(refpan);
	     refpan.setVisible(false);
	     f.getContentPane().add(pancats);
	     f.getContentPane().add(recpan);
	     
		 jpn = new JPanel();
		 ListModelExample();
		 jpn.setBounds(50, 50, 200, 200);
		 f.getContentPane().add(jpn);
		 jpn.setVisible(false);
	     f.getContentPane().add(panan);
	     f.getContentPane().add(panan2);
	     f.getContentPane().add(check);
	     f.getContentPane().add(hsub);
	     f.getContentPane().add(panel, "Center"); 
	     
	     f.addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	        	 File f = new File("file.txt");
	        	 String str = "";
	        	 for (int i = 0; i < recipe.size(); i++) 
	        	 {
	        		 if(!(recipe.get(i).getName().equals("")))
	        		 {
	        			 if(!(recipe.get(i).getAuthor().equals("")))
	        			 {
	        				 if(!(recipe.get(i).getPassword().equals("")))
	        				 {
	        					 if(recipe.get(i).getIngredients().length > 1)
	        						 str += recipe.get(i).toooString();
	        				 }
	        				 else
	        				 {
	    	        			 recipe.get(i).setPassword("password");
	    	        			 if(recipe.get(i).getIngredients().length > 1)
	        						 str += recipe.get(i).toooString();
	        				 }
	        			 }
	        			 else
	        			 {
		        			 recipe.get(i).setAuthor("Unknown");
		        			 if(recipe.get(i).getIngredients().length > 1)
        						 str += recipe.get(i).toooString();
	        			 }
	        		 }
	        		 else
	        		 {
	        			 recipe.get(i).setName("Unnamed");
	        			 if(recipe.get(i).getIngredients().length > 1)
    						 str += recipe.get(i).toooString();
	        		 }
				 }
	             try {
					fileWriter( f, str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	             e.getWindow().dispose();
	         }
	     });
	}
	/**
	 * Makes some of the labels for things to be used.
	 */
	public void addLabels()
	{
		String[] str = owner.split(" ");
        label = new JLabel(str[0] + "'s CookBook", JLabel.CENTER);
        saverr = str[0] + "'s CookBook";
        Font font = new Font("Courier", Font.BOLD, 16);
        label.setFont(font);
        label.setSize(300, 30);
        label.setLocation(100, 60);
        
        thanks = new JLabel("THANK YOU");
        thanks.setFont(new Font("Courier", Font.BOLD, 50));
        thanks.setSize(400, 400);
        thanks.setLocation(420, 0);
        thank3s = new JLabel("<html><center>Your recipe was deleted to the cookbook.<br>Please choose what you would like to do next from the menu in the sidebar.<br>Please close the program and reopen it to allow for your recipe's full deletion.</center></html>");
        thank3s.setFont(new Font("Courier", Font.ITALIC, 11));
        thank3s.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        thank3s.setSize(300, 400);
        thank3s.setLocation(408, 70);
        thank3s.setVisible(false);
        thank2s = new JLabel("<html><center>Your recipe was submitted to the cookbook.<br>Please choose what you would like to do next from the menu in the sidebar.</center></html>");
        thank2s.setFont(new Font("Courier", Font.ITALIC, 11));
        thank2s.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        thank2s.setSize(300, 400);
        thank2s.setLocation(408, 55);
        thanks.setVisible(false);
        thank2s.setVisible(false);
	}
	/**
	 * Makes most of the GUI components so that the other functions can implement them.
	 * @throws IOException
	 */
	public void getPanels() throws IOException
	{		
	   	 MyPanel1 = new JPanel();
	   	 MyPanel1.setLayout(new BoxLayout(MyPanel1, BoxLayout.PAGE_AXIS));
	   	 MyPanel1.setBackground(Color.BLACK);

	   	 thingyInstantiator();
	     JButton[] xn = { x2, x3, x4, x6 };
	   	
	   	 for (int i = 0; i < xn.length; i++)
	   	 {
			xn[i].setBackground( b);
			xn[i].setAlignmentX(Component.CENTER_ALIGNMENT);
		   	MyPanel1.add(Box.createRigidArea(new Dimension(0, 80)));
		   	MyPanel1.add(xn[i]);
		 }
	   	 
	   	 pancats = new JPanel();
	   	 pancats.setBounds(200, 90, 100, 340);
	   	 pancats.setBackground( b);
	   	 pancats.add(soup); pancats.add(Box.createRigidArea(new Dimension(0, 23))); pancats.add(salad); pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(fish); pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(chicken); pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(dessert); pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(breakfast); pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(burger);pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(vegan); pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(kosher); pancats.add(Box.createRigidArea(new Dimension(0, 30))); pancats.add(glutenFree); pancats.add(Box.createRigidArea(new Dimension(0, 30)));
	   	 
		recpan = new JPanel ();
		recpan.setBounds(440, 65, 240, 370);
	    recpan.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
	    recpan.setForeground(b);
	    recpan.setBackground( b);
	    menudisplay = new JTextArea ( 21, 30 );
	    menudisplay.setBackground(b);
	    menudisplay.setEditable ( false ); // set textArea non-editable
	    menudisplay.setLineWrap(true);
	    Font font = new Font("Courier", Font.ITALIC, 11);
	    menudisplay.setFont(font);	    
	    recpan.setFont(font);
	    JScrollPane scroll = new JScrollPane ( menudisplay );
	    scroll.setBackground(b);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    recpan.add(scroll);
	    recpan.setVisible(true);
	    
		panan = new JPanel ();
		panan.setBounds(440, 170, 240, 100);
	    panan.setBorder ( new TitledBorder ( new EtchedBorder (), "Ingredients" ) );
	    panan.setForeground(b);
	    panan.setBackground( b);
	    menuudisplay = new JTextArea ( 4, 30 );
	    menuudisplay.setBackground(b);
	    menuudisplay.setEditable ( true ); // set textArea non-editable
	    menuudisplay.setLineWrap(true);
	    menuudisplay.setText("Please enter your ingredients.\nSeparate by a new line.");
	    menuudisplay.setFont(font);	    
        menuudisplay.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(clearerone == 0)
            		menuudisplay.setText("");
                clearerone++;                
            }
        });
	    panan.setFont(font);
	    JScrollPane scrolll = new JScrollPane ( menuudisplay );
	    scrolll.setBackground(b);
	    scrolll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrolll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    panan.add(scrolll);
	    panan.setVisible(true);
	    
		panan2 = new JPanel ();
		panan2.setBounds(440, 275, 240, 100);
	    panan2.setBorder ( new TitledBorder ( new EtchedBorder (), "Steps" ) );
	    panan2.setForeground(b);
	    panan2.setBackground( b);
	    menuuudisplay = new JTextArea ( 4, 30 );
	    menuuudisplay.setBackground(b);
	    menuuudisplay.setEditable ( true ); // set textArea non-editable
	    menuuudisplay.setLineWrap(true);
	    menuuudisplay.setText("Please enter your steps, \nseparated by a new line.");
        menuuudisplay.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(clearertwo == 0)
            		menuuudisplay.setText("");
                clearertwo++;                
            }
        });
	    menuuudisplay.setFont(font);	    
	    panan2.setFont(font);
	    JScrollPane scrollll = new JScrollPane ( menuuudisplay );
	    scrollll.setBackground(b);
	    scrollll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scrollll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    panan2.add(scrollll);
	    panan2.setVisible(true);
	   	 
	   	myPicture = ImageIO.read(new File("pic2.jpg"));
	   	panel = new JPanel();
	    panel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
	    panel.setBackground(Color.black);
	    picLabel = new JLabel(new ImageIcon(myPicture));
	    picLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    picLabel.setLocation(0, 0);
	    panel.add(picLabel);  
	    
	     check = new JPanel();
	     check.setLayout(new BoxLayout( check, BoxLayout.PAGE_AXIS));
	     check.setBackground(b);
	     
	   	 check.setBounds(177, 90, 23, 340);
	     
	     check.add(Box.createRigidArea(new Dimension(0,8)));
	     for( int i = 0; i < 10; i++)
	     {
	    	 mm[i] = new JCheckBox();
	    	 mm[i].setAlignmentX(Component.LEFT_ALIGNMENT);
	    	 mm[i].setBackground(b);
	    	 mm[i].setOpaque(false);
	    	 mm[i].setVisible(true);
	    	 check.add(mm[i]);
	    	 check.add(Box.createRigidArea(new Dimension(0, 13)));
	     }
	     check.setVisible(false);
	     
        submit = new JButton(new ButtonAction("Submit", KeyEvent.VK_A));
		submit.setBackground(new Color(139, 69, 19));
		submit.setForeground(Color.white);
		submit.setFont(font);
		submit.setLocation(100, 100);
		submit.setVisible(true);
		
		hsub = new JPanel();
		hsub.add(submit);
		hsub.setLocation(520, 420);
		hsub.setSize(90, 40);
		hsub.setVisible(false);
		hsub.setOpaque(false);
		
		refpan = new JPanel();
		refpan.add(refresh);
		refpan.setLocation(415, 441);
		refpan.setSize(55, 35);
		refpan.setVisible(true);
		refpan.setOpaque(false);
	}
	/**
	 * All the good ol' getters and setters.
	 * @return
	 */
	public ArrayList<Recipe> getRecipe() {
		return recipe;
	}
	public void setRecipe(ArrayList<Recipe> recipe) {
		this.recipe = recipe;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	/**
	 * Displays the main JFrame.
	 */
	public void display()
	{
	   f.pack();
	   f.setVisible(true);
	    f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    f.setResizable(false);
	}
	/**
	 * Makes all the buttons for the stuff.
	 */
	public void thingyInstantiator()
	{
        Font font = new Font("Courier", Font.ITALIC, 11);
		
		 x3 = new JButton("Cooker");
		 x3.addActionListener( new ButtonListener(10));
	   	 x4 = new JButton("Eraser");
	   	 x4.addActionListener( new ButtonListener(12));
	   	 x6 = new JButton("Info");
	   	 x6.addActionListener( new ButtonListener(14));
	   	 x2 = new JButton("Home");
	   	x2.addActionListener( new ButtonListener(11));
	   	 
       refresh = new JButton("Refresh");
       refresh.setBackground(new Color(139, 69, 19));
       refresh.setForeground(Color.white);
       refresh.setFont(new Font("Courier", Font.BOLD, 11));
       refresh.setLocation(300, 20);
       refresh.setVisible(true);
       
       refresh.addActionListener(new ActionListener()
       {
         public void actionPerformed(ActionEvent e)
         {
           refresh();
         }
       });
	   	
        soup = new JButton("Soup");
		soup.setBackground(new Color(139, 69, 19));
		soup.setForeground(Color.white);
		soup.setFont(font);
		soup.setLocation(120, 80+(0*20));
		
	    soup.addActionListener( new ButtonListener(0));
		
        salad = new JButton("Salad");
		salad.setBackground(new Color(139, 69, 19));
		salad.setForeground(Color.white);
		salad.setFont(font);
		salad.setLocation(120, 80+(1*20));
		
	    salad.addActionListener( new ButtonListener(1));

		
        fish = new JButton("Fish");
		fish.setBackground(new Color(139, 69, 19));
		fish.setForeground(Color.white);
		fish.setFont(font);
		fish.setLocation(120, 80+(2*20));
		
	    fish.addActionListener( new ButtonListener(2));

		
        chicken = new JButton("Chicken");
		chicken.setBackground(new Color(139, 69, 19));
		chicken.setForeground(Color.white);
		chicken.setFont(font);
		chicken.setLocation(120, 80+(3*20));
		
		chicken.addActionListener( new ButtonListener(3));

		
        dessert = new JButton("Dessert");
        dessert.setBackground(new Color(139, 69, 19));
		dessert.setForeground(Color.white);
		dessert.setFont(font);
        dessert.setLocation(120, 80+(4*20));
        
        dessert.addActionListener( new ButtonListener(4));

        
        breakfast = new JButton("Breakfast");
        breakfast.setBackground(new Color(139, 69, 19));
		breakfast.setForeground(Color.white);
		breakfast.setFont(font);
        breakfast.setLocation(120, 80+(5*20));
        
        breakfast.addActionListener( new ButtonListener(5));

        burger = new JButton("Burger");
        burger.setBackground(new Color(139, 69, 19));
		burger.setForeground(Color.white);
		burger.setFont(font);
        burger.setLocation(120, 80+(7*20));
        
        burger.addActionListener( new ButtonListener(6));
        
        vegan = new JButton("Vegan");
		vegan.setBackground(new Color(139, 69, 19));
		vegan.setForeground(Color.white);
		vegan.setFont(font);
		vegan.setLocation(120, 80+(6*20));
		
		vegan.addActionListener( new ButtonListener(7));
        
        kosher = new JButton("Kosher");
		kosher.setBackground(new Color(139, 69, 19));
		kosher.setForeground(Color.white);
		kosher.setFont(font);
		kosher.setLocation(120, 80+(8*20));
		
		kosher.addActionListener( new ButtonListener(8));
		
        glutenFree = new JButton("Gluten-Free");
        glutenFree.setBackground(new Color(139, 69, 19));
        glutenFree.setForeground(Color.white);
        glutenFree.setFont(font);
        glutenFree.setLocation(120, 80+(9*20));
        
        glutenFree.addActionListener( new ButtonListener(9));
	}
	/**
	 * Prints the date and time.
	 */
	public static String printDate()
	{
		Date date = new Date();		
        SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy '--' hh:mm");
    	return ft.format(date);
	}
	/**
	 * Operator for all of the category buttons (1-10) and all of the side panel buttons as well.
	 * @author 18sschiller
	 *
	 */
   private class ButtonListener implements ActionListener
   {
	   private int num;
	   public ButtonListener( int x)
	   {
		   setNum(x);
	   }
		public void setNum(int x) {
			this.num = x;
		}
      public void actionPerformed (ActionEvent event)
      {
    	 if( num < 10)
    	 {	
    		 categorize(num);
	    	 menudisplay.setText("");
	    	 String temp = "";
	    	 for (int i = 0; i < tempr.size(); i++) 
	    	 {
				temp += tempr.get(i).tooString();
	    	 }
	    	 menudisplay.setText(temp);
	    	 f.repaint();
    	 }
    	 else if( num == 10)
    	 {
    		thanks.setVisible(false);
    		thank3s.setVisible(false);
			f.getContentPane().remove(recpan);
			f.getContentPane().remove(panel);
			f.getContentPane().remove(label);
			f.getContentPane().remove(jpn);
			refpan.setVisible(true);
	        JLabel input1 = new JLabel("Name:");
	        input1.setFont(font);
	        input1.setSize(300, 30);
	        input1.setLocation(475, 40);
	        input1.setVisible(true);
	        
	        input1i = new JTextField();
	        input1i.setSize(80, 20);
	        input1i.setLocation(530, 45);
	        input1i.setVisible(true);
	        
	        JLabel input2 = new JLabel("Chef:");
	        input2.setFont(font);
	        input2.setSize(300, 30);
	        input2.setLocation(475, 75);
	        input2.setVisible(true);
	        
	        input2i = new JTextField();
	        input2i.setSize(80, 20);
	        input2i.setLocation(530, 80);
	        input2i.setVisible(true);
	        
	        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);

	        slider.setMinorTickSpacing(5);
	        slider.setMajorTickSpacing(20);
	        slider.setPaintTicks(true);
	        slider.setPaintLabels(true);
	        slider.setLabelTable(slider.createStandardLabels(20));
	        slider.setSize(180, 45);
	        slider.setLocation(430, 115);
	        slider.setBackground(b);
	        slider.setForeground(Color.black);
	        slider.setVisible(true);

	        slider.addChangeListener(new BoundedChangeListener());
	        
		     JLabel jl = new JLabel("<html><u>Prep-Time</u><br>&nbsp&nbsp&nbsp&nbsp min</html>");
		     jl.setFont(font);
		     jl.setSize(100, 100);
		     jl.setLocation(625, 85);
		     jl.setVisible(true);
		     
		     jbl = new JLabel("" + 0);
		     jbl.setSize(100, 100);
		     jbl.setLocation(637, 93);
		     jbl.setVisible(true);
		     
		    JLabel passw = new JLabel("Password:");
	        passw.setFont(font);
	        passw.setSize(300, 30);
	        passw.setLocation(475, 383);
	        passw.setVisible(true);
	        
	        jk = new JPasswordField(10);
	        jk.setSize(80, 20);
	        jk.setLocation(545, 388);
	        jk.setVisible(true);
		    
	        label.setText("Recipe Maker");
	        f.getContentPane().add(input1i);
	        f.getContentPane().add(passw);
	        f.getContentPane().add(jk);
	        f.getContentPane().add(input2i);
	        f.getContentPane().add(input2);
	        f.getContentPane().add(label);
	        f.getContentPane().add(input1);
	        f.getContentPane().add(slider);
	        f.getContentPane().add(jl);
	        
	        panan.setVisible(true);
	        panan2.setVisible(true);
	        check.setVisible(true);
	        hsub.setVisible(true);
	        f.getContentPane().remove(panan);
	        f.getContentPane().remove(panan2);
	        f.getContentPane().remove(check);
	        f.getContentPane().remove(hsub);
	        f.getContentPane().add(panan);
	        f.getContentPane().add(panan2);
	        f.getContentPane().add(check);
	        f.getContentPane().add(hsub);
	        f.getContentPane().add(panel, "Center");
	        makesure++;
    		refresh();
	        f.repaint();
    	 }
    	 else if( num == 11) //home
    	 {
    		 refresh();
    		 f.getContentPane().removeAll();
    		 f.getContentPane().add( MyPanel1, "East");
    		 label.setText(saverr);
    		 f.getContentPane().add(label);
    		 f.getContentPane().add(pancats);
    	     f.getContentPane().add(recpan);
    	     f.getContentPane().add(thanks);
    	     thanks.setVisible(false);
    	     f.getContentPane().add(thank2s);
    	     thank2s.setVisible(false);
    	     f.getContentPane().add(thank3s);
    	     thank3s.setVisible(false);
    	     f.getContentPane().add(refpan);
    	     refpan.setVisible(false);
    	     f.getContentPane().add(panel, "Center"); 
    	     f.repaint();
    	 }
    	 else if( num == 12) //eraser
    	 {    	
    		 refresh();
    		 jpn = new JPanel();
    		 ListModelExample();
    		 jpn.setBounds(50, 50, 200, 200);
    		 f.getContentPane().add(jpn);
    		 jpn.setVisible(false);
    		 f.getContentPane().removeAll();
    		 f.getContentPane().add(jpn);
    		 jpn.setVisible(true);
    		 jpn.setOpaque(false);
    		 jpn.setLocation(460, 120);
    	     f.getContentPane().add(refpan);
    	     refpan.setVisible(true);
    		 f.getContentPane().add( MyPanel1, "East");
    		 label.setText("Recipe Eraser");
    		 f.getContentPane().add(label);
    		 f.getContentPane().add(pancats);
    	     f.getContentPane().add(thanks);
    	     thanks.setVisible(false);
    	     f.getContentPane().add(thank2s);
    	     thank2s.setVisible(false);
    	     f.getContentPane().add(thank3s);
    	     thank3s.setVisible(false);
    	     f.getContentPane().add(panel, "Center"); 
    	     makesuree++;
    	     f.repaint();
    	 }
    	 else if( num == 14 )
    	 {
	    	iframe = new JFrame("Info");
	        iframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        iframe.setLayout(new BorderLayout());
	        contentPanef = new JPanel();
	        contentPanef.setLayout(new SpringLayout());
	        contentPanef.setBackground(b);
	        contentPanef.setForeground(b);
	        JButton about = new JButton("About");
	        about = fixer(about);
	        about.addActionListener( new ButtonnListener(1));
	        JButton owner = new JButton("Owner");
	        owner = fixer(owner);
	        owner.addActionListener( new ButtonnListener(2));
	        JButton authors = new JButton("Background");
	        authors = fixer(authors);
	        authors.addActionListener( new ButtonnListener(3));
	        JButton contact = new JButton("Contact");
	        contact = fixer(contact);
	        contact.addActionListener( new ButtonnListener(4));
	        JButton reset = new JButton("Reset");
	        reset = fixer(reset);
	        reset.addActionListener( new ButtonnListener(5));
	        
	        contentPanef.add(about);
	        contentPanef.add(owner);
	        contentPanef.add(authors);
	        //contentPane.add(editor);
	        contentPanef.add(contact);
	        contentPanef.add(reset);

	        SpringUtilities.makeCompactGrid(contentPanef, 5, 1, 6, 18, 6, 18);
	       
	        iframe.getContentPane().add(contentPanef);
	        iframe.pack();
	        iframe.setSize(166, 300);  //228
	        iframe.setLocation(910, 10);
	        iframe.setVisible(true);  	
	        
	        iframe.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                e.getWindow().dispose();
	               
	                ownerr.setVisible(false);
	                aboutt.setVisible(false);
	                contactt.setVisible(false);
	                resett.setVisible(false);
	            }
	        });
    	 }
      }
   }
   /**
    * Puts all of the recipes in the right category to be displayed.
    * @param x
    */
   private void categorize( int x )
   {
	   tempr = new ArrayList<Recipe>();
	   tempr.clear();
	   if( x == 0)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(0) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 1)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(1) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 2)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(2) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 3)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(3) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 4)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(4) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 5)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(5) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 6)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(6) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 7)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(7) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else if( x == 8)
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(8) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
	   else
		   for (int i = 0; i < recipe.size(); i++) 
		   {
			   for (int j = 0; j < recipe.get(i).getCategories().size(); j++) 
			   {
				   if( recipe.get(i).getCategories().contains(9) == true )
					   	if(!(tempr.contains(recipe.get(i))))
					   		tempr.add(recipe.get(i));
			   }
		   }
   }
   /**
    * Listener for the slider.
    * @author 18sschiller
    *
    */
   class BoundedChangeListener implements ChangeListener 
   {
	   public void stateChanged(ChangeEvent changeEvent) 
	   {
		 int p = 0;
	     Object source = changeEvent.getSource();
	     
	     if (source instanceof JSlider) 
	     {
	       JSlider theJSlider = (JSlider) source;
	       if (!theJSlider.getValueIsAdjusting()) 
	       {
	         p = theJSlider.getValue();
	       }
	     }
	     f.getContentPane().remove(jbl);
	     f.getContentPane().remove(panel);
	     jbl.setText("" + p);
	     f.getContentPane().add(jbl);
	     f.getContentPane().add(panel, "Center"); 
	     aaaa = p;
	     f.repaint();
	   }
	 }
    /**
    * Writes to file.
    * @param whole
    * @param x
    * @return
    */
   public static void fileWriter(File file, String data) throws IOException 
   {
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		output.write(data); //writes out the data
		output.close(); //closes the output
   }
   /**
    * Splits the input up for the new recipes.
    * @param whole
    * @return
    */
   public static String[] splitter( String whole)
   {
	   String [] str2 = null;
	   str2 = whole.split("\n");
	   return str2;
   }
    /**
     * Checks to see if the boxes are checked for the Submit button when you submit a new recipe to read the categories.
     * @param mm
     * @return go
     */
   public static ArrayList<Integer> checker( JCheckBox[] mm)
     {
    	 ArrayList<Integer> go = new ArrayList<Integer>();
  	   boolean[] ba = {mm[0].isSelected(), mm[1].isSelected(), mm[2].isSelected(), mm[3].isSelected(), mm[4].isSelected(), mm[5].isSelected(), mm[6].isSelected(), mm[7].isSelected(), mm[8].isSelected(), mm[9].isSelected() };
  	   for (int i = 0; i < ba.length; i++) 
  	   {
		 if(ba[i])
			 go.add(i);
  	   }
  	   return go;
     }
    /**
     * Makes the Eraser tab using SpringUtilities and it makes it look all cool and stuff.
     */
    public void ListModelExample() 
    {
    	refresh();
  	 jpn = new JPanel(); 
  	 jl = new ArrayList<String>();
      jpn.setLayout(new BorderLayout());
      model = new DefaultListModel<String>();
      list = new JList<String>(model);
      list.setBackground(new Color(210, 180, 140));
      list.setFont(new Font("Courier", Font.BOLD, 12));
      JScrollPane pane = new JScrollPane(list);
      pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      listofcountries = new ArrayList<String>();
      ArrayList<String> olist = new ArrayList<String>();
      for (int i = 0; i < recipe.size(); i++) 
    	  listofcountries.add(recipe.get(i).getName());
      Collections.sort(listofcountries);
      for (int i = 0; i < listofcountries.size(); i++) 
      {
		for (int j = 0; j < recipe.size(); j++) 
		{
			if(recipe.get(j).getName().equals(listofcountries.get(i)))
				olist.add(recipe.get(j).getPassword());
		}
      }
      for (int i = 0; i < recipe.size(); i++) 
      {
  		model.addElement(listofcountries.get(i));
  		jl.add(olist.get(i));
  	  }
      
      ListSelectionListener sl = new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
           if (e.getValueIsAdjusting() == false) {
               String str = (String)list.getSelectedValue();
               for (int i = 0; i < recipe.size(); i++) 
               {
				if(str.equals(listofcountries.get(i)))
					holder = i;
			}
           }
          }
       };

       list.addListSelectionListener(sl);
       
      selectButton = new JButton("Select");
      selectButton.setBackground(new Color(210, 180, 140));
      selectButton.setFont(new Font("Courier", Font.BOLD, 12));

      selectButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) 
          {
            selectButton.setText("Enter Password");
            kjv = new JPasswordField(10);
            jpn.add(kjv, BorderLayout.SOUTH);
            jpn.repaint();
            selectButton.addActionListener(new ActionListener() 
            {
          	  public void actionPerformed(ActionEvent e)
          	  {
          		  String y = "";
          		  char[] tt = kjv.getPassword();
          		  for (int i = 0; i < tt.length; i++)
          			  y += tt[i];
          	      if( y.equals(jl.get(holder)))
  	    		  {
          	    	  recipe.remove(holder);
     	    		 f.getContentPane().removeAll();
    	    		 label.setText(saverr);
    	    		 f.getContentPane().add( MyPanel1, "East");
    	    		 f.getContentPane().add(label);
    	    		 f.getContentPane().add(pancats);
    	    	     f.getContentPane().add(thanks);
    	    	     thanks.setVisible(false);
    	    	     f.getContentPane().add(thank3s);
    	    	     thank3s.setVisible(false);
    	    	     f.getContentPane().add(panel, "Center"); 
    	            thanks.setVisible(true);
    	            thank3s.setVisible(true);
    	            f.repaint();
  	    		  }
          	  }
            });
            f.repaint();
            }
           });
      
      pane.setBackground(new Color(210, 180, 140));
      jpn.add(pane, BorderLayout.NORTH);
      jpn.add(selectButton, BorderLayout.CENTER);
      jpn.setBackground(new Color(210, 180, 140));
      
      jpn.setVisible(true);
    }
    /**
     * Refreshes the screen.
     */
    public void refresh()
    {
    	clearerone = 0;
    	clearertwo = 0;
    	if(makesure > 0)
    	{
    	if(input1i.isShowing())
    		input1i.setText("");
    	if(input2i.isShowing())
    		input2i.setText("");
    	if(jbl.isShowing())
    		jbl.setText("" + 0);
    	if(jk.isShowing())
    		jk.setText("");
    	}
	    for (int i = 0; i < mm.length; i++) 
	    	mm[i].setSelected(false);
	    menudisplay.setText("");
	    menuudisplay.setText("Please enter your ingredients.\nSeparate by a new line.");
	    menuuudisplay.setText("Please enter your steps, \nseparated by a new line.");
	    if(makesuree > 0)
	    {
	    	model.clear();
	    	jl.clear();
	    	
	        listofcountries = new ArrayList<String>();
	        ArrayList<String> olist = new ArrayList<String>();
	        for (int i = 0; i < recipe.size(); i++) 
	      	  listofcountries.add(recipe.get(i).getName());
	        Collections.sort(listofcountries);
	        for (int i = 0; i < listofcountries.size(); i++) 
	        {
	  		for (int j = 0; j < recipe.size(); j++) 
	  		{
	  			if(recipe.get(j).getName().equals(listofcountries.get(i)))
	  				olist.add(recipe.get(j).getPassword());
	  		}
	        }
	        for (int i = 0; i < recipe.size(); i++) 
	        {
	    		model.addElement(listofcountries.get(i));
	    		jl.add(olist.get(i));
	    	}
	    }
        f.repaint();
    }
    /**
     * Corrects a JButton to look pretty.
     * @param bt
     * @return
     */
    public static JButton fixer(JButton bt)
    {
        bt.setBackground(new Color(139, 69, 19));
        bt.setForeground(Color.white);
        bt.setFont(new Font("Courier", Font.BOLD, 14));
        return bt;
    }
    /**
     * This is for the Submit button. It gathers all of the screen data and puts it into a new recipe.
     * @author 18sschiller
     *
     */
    private class ButtonAction extends AbstractAction 
    {
    	public ButtonAction(String name, Integer mnemonic) {
           super(name);
           putValue(MNEMONIC_KEY, mnemonic);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
           ArrayList<Integer> catt = new ArrayList<Integer>();
      	 catt = checker(mm);
      	String[] ingr = splitter(menuudisplay.getText());
      	String[] steps = splitter(menuuudisplay.getText());
      	char[] c = jk.getPassword();
      	String pass = "";
      	for (int i = 0; i < c.length; i++) 
      	{
  			pass += c[i];
  		}
          Recipe temp = new Recipe(input1i.getText(), ingr, input2i.getText(), pass, steps, aaaa, catt);
          if(!(recipe.contains(temp)))	
          	recipe.add(temp);
  		 f.getContentPane().removeAll();
  		 label.setText(saverr);
  		 f.getContentPane().add( MyPanel1, "East");
  		 f.getContentPane().add(label);
  		 f.getContentPane().add(pancats);
  	     f.getContentPane().add(thanks);
  	     thanks.setVisible(false);
  	     f.getContentPane().add(thank2s);
  	     thank2s.setVisible(false);
  	     f.getContentPane().add(refpan);
  	     refpan.setVisible(false);
  	     f.getContentPane().add(panel, "Center"); 
          thanks.setVisible(true);
          thank2s.setVisible(true);
          f.repaint();
        }
     }
    /**
     * This is a listener for the background changer, that changes the background of everything based on what is clicked. This is not being used right now.
     * @author 18sschiller
     *
     */
    private class doer implements ChangeListener 
    {
    	public void stateChanged(ChangeEvent e) {
            Color newColor = tcc.getColor();
            f.setBackground(newColor);
            f.setForeground(newColor);
            panel.setBackground(newColor);
            MyPanel1.setBackground(newColor);
            pancats.setBackground(newColor);
            recpan.setBackground(newColor);
        	aboutpanel.setBackground(newColor);
    		resetpanel.setBackground(newColor);
    		contactpanel.setBackground(newColor);
    		ownerpanel.setBackground(newColor);
            contentPanef.setBackground(newColor);
            tcc.setBackground(newColor);            
            f.repaint();
        }    	
    }
     /**
     * This Button listener is used to the Info tab and creates the different JFrames and everything in them based on what button is pressed.
     * @author 18sschiller
     *
     */
    private class ButtonnListener implements ActionListener
    {
 	   private int num;
 	   public ButtonnListener( int x)
 	   {
 		   setNum(x);
 	   }
 		public void setNum(int x) {
 			this.num = x;
 		}
       public void actionPerformed (ActionEvent event)
       {
    	   if( num == 1)
    	   {
    		   aboutt = new JFrame("About");
    		   aboutt = affect(aboutt, 0);
    		   aboutpanel = new JPanel ();
    		   	aboutpanel = upper(aboutpanel);
    		    JTextArea temp = new JTextArea ( 7, 43 );
    		    temp = downer(temp);
    		    temp.setText("Hello. My name is Simon Schiller. I createdthis code and have made it possible for youto have this CookBook. I know this code is not beautiful and it is a little bit all \nover the place, but it works. A wise man \nonce told me, \"If the code works, then it  is fine.\" This program was completed in \nWissahickon High School at 10:15 AM during Study Hall on Thursday, May 26th, 2016. \nI would like to thank my amazing Computer  Science teacher, Mr. Stienberg for his helpand support throughout this process.");
    		    JScrollPane scroll = new JScrollPane ( temp );
    		    scroll.setBackground(b);
    		    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    		    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    		    aboutpanel.add(scroll);
    		    aboutpanel.setVisible(true);
    		    aboutt.getContentPane().add(aboutpanel);
    	   }
    	   else if( num == 2)
    	   {
    		   Font fontt = new Font("Courier", Font.ITALIC, 14);
    		   ownerr = new JFrame("Owner");
    		   ownerr = affect(ownerr, 1);
    		   ownerpanel = new JPanel();
    		   ownerpanel.setLocation(50, 50);
    		   ownerpanel.setSize(50, 50);
    	       ownerpanel.setForeground(new Color(210, 180, 140));
    		   ownerpanel.setBackground(new Color(139, 69, 19));
    		   JLabel l = new JLabel("The current owner of the");
    		   lb = new JLabel("CookBook is " + owner + ".");
    		   JLabel lc = new JLabel("Please enter the new owner below.");
    		   l.setFont(fontt);
    		   l.setForeground(Color.white);
    		   l.setLocation(20, 20);
    		   lb.setFont(fontt);
    		   lb.setForeground(Color.white);
    		   lb.setLocation(20, 24);
    		   lc.setFont(fontt);
    		   lc.setForeground(Color.white);
    		   lc.setLocation(20, 28);
    		   JButton sub = new JButton("Submit");
    		   jtf = new JTextField(12);
    		   sub = fixer(sub);
    		   sub.addActionListener(new ActionListener()
    		   {
    		     public void actionPerformed(ActionEvent e)
    		     {
    		       owner = jtf.getText();
    				String[] str = owner.split(" ");
    		        label.setText(str[0] + "'s CookBook");
    		       f.setTitle(jtf.getText() + "'s CookBook                                                                                                                                                  Last Updated: " + lastupdate);
    		       File f = new File("filetwo.txt");
    		       try {
					fileWriter(f, jtf.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		       lb.setText("CookBook is " + owner + ".");
    		     }
    		   });
    		   ownerpanel.add(l);
    		   ownerpanel.add(lb);
    		   ownerpanel.add(lc);
    		   ownerpanel.add(jtf);
    		   ownerpanel.add(sub);
	   		   ownerr.getContentPane().add(ownerpanel);
	   		   ownerpanel.setVisible(true);
    	   }
    	   else if( num == 3)
    	   {
    		   System.out.println("I chose to remove this panel for better program flow.");
    		   /*
			   fn = new JFrame("Background");
			   fn.setBackground(b);
		        fn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			   tcc = new JColorChooser(Color.black);
			   tcc.setBackground(b);
			   tcc.setForeground(b);
			   tcc.setPreviewPanel(new JPanel());
			   tcc.getSelectionModel().addChangeListener(new doer());
			   AbstractColorChooserPanel[] k = tcc.getChooserPanels();
			   for (int i = 0; i < k.length; i++) 
				   k[i].setBackground(b);
			   tcc.setChooserPanels(k);
			   AbstractColorChooserPanel[] panels = tcc.getChooserPanels();
			   for (AbstractColorChooserPanel accp : panels)
				   if(!(accp.getDisplayName().equals("RGB"))) 
			    	  tcc.removeChooserPanel(accp);
			   
  			    fn.add(tcc, BorderLayout.PAGE_START);
		        fn.pack();
		        fn.setSize(450, 205);
		        fn.setLocation(910, 335);
		        fn.setResizable(false);
		        fn.setVisible(true);
		      */  			   
    	   }
    	   else if( num == 4)
    	   {
    		   contactt = new JFrame("Contact");
    		   contactt = affect(contactt, 2);
    		   contactpanel = new JPanel();
    		   contactpanel.setLocation(50, 50);
    		   contactpanel.setSize(50, 50);
    		   contactpanel.setForeground(new Color(210, 180, 140));
    		   contactpanel.setBackground(new Color(139, 69, 19));
    		   JLabel ml = new JLabel("<html><center>If you have questions or comments,<br>please contact me using the following:<br>Email: 18simon.schiller@gmail.com<br>Cell: (267)315-4725<br>Address: 936 Sturgis Lane<br>         Ambler, PA 19002</center></html>");
    		   ml.setForeground(Color.white);
    		   ml.setFont(font);
    		   ml.setVisible(true);
    		   contactpanel.add(ml);
    		   contactt.getContentPane().add(contactpanel);
    		   contactpanel.setVisible(true);
    	   }
    	   else
    	   {
    		   resett = new JFrame("Reset");
    	    	resett.pack();
    	    	resett.setSize(220, 168);
    	    	resett.setLocation(10+(375*3), 555);
    	    	resett.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    	resett.setVisible(true);    	    	
     		   Font fontt = new Font("Courier", Font.ITALIC, 14);
     		   resetpanel = new JPanel();
     		   resetpanel.setLocation(50, 50);
     		   resetpanel.setSize(50, 50);
     	       resetpanel.setForeground(new Color(210, 180, 140));
     		   resetpanel.setBackground(new Color(139, 69, 19));
     		   ql = new JLabel("HARD RESET");
     		   qlb = new JLabel("Are you sure you want to");
     		   qlc = new JLabel("erase all " + recipe.size() + " recipes?");
     		   ql.setFont(new Font("Courier", Font.ITALIC, 25));
     		   ql.setForeground(Color.white);
     		   ql.setLocation(20, 20);
     		   qlb.setFont(fontt);
     		   qlb.setForeground(Color.white);
     		   qlc.setFont(fontt);
     		   qlc.setForeground(Color.white);
     		   qlc.setLocation(20, 28);
     		   subr = new JButton("Reset");
     		   jtff = new JTextField(12);
     		   subr = fixer(subr);
    		   subr.addActionListener(new ActionListener()
    		   {
    		     public void actionPerformed(ActionEvent e)
    		     {
    		    	 qlb.setText("Admin Password Required");
    		    	 resetpanel.remove(qlc);
    		    	 resetpanel.remove(subr);
    		    	 resetpanel.add(jtff);
    		    	 JButton conf = new JButton("Confirm");
    		    	 conf = fixer(conf);
    	    		 conf.addActionListener(new ActionListener()
    	    		 {
    	    		   public void actionPerformed(ActionEvent e)
    	    		   {
    	    			   String str = SpringUtilities.hider(1234567654);
    	    			   if(jtff.getText().equals(str))
    	    			   {
	    	    			 ql.setText("THANK YOU");
	    	    		     qlb.setText("The CookBook");
	    	    		     qlb.setFont(new Font("Courier", Font.ITALIC, 17));
	    	    		     qlc.setText("was cleared.");
	    	    		     qlc.setFont(new Font("Courier", Font.ITALIC, 17));
	    	    		     resetpanel.removeAll();
	    	    		   	 resetpanel.add(ql);
	    	    		   	 resetpanel.add(qlb);
	    	    		   	 resetpanel.add(qlc);
	    	    		     resetpanel.repaint();
	    	    		     recipe.clear();
	    	    		     File m = new File("file.txt");
	    	    		     try {
								fileWriter(m, "");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    	    			   }
    	    		    }
    	    		 });
    	    		 resetpanel.add(conf);
    		    	 resetpanel.repaint();
    		     }
    		   });
     		   resetpanel.add(ql);
     		   resetpanel.add(qlb);
     		   resetpanel.add(qlc);
     		   resetpanel.add(subr);
 	   		   resett.getContentPane().add(resetpanel);
 	   		   resetpanel.setVisible(true);
    	   }
       }
    }     
    /**
     * Sets the necessary features of the JFrames for the Info pane.
     * @param fl
     * @param time
     * @return
     */
    private static JFrame affect(JFrame fl, int time)
    {
    	fl.pack();
    	fl.setSize(365, 168);
    	fl.setLocation(10+(375*time), 555);
    	fl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	fl.setVisible(true);
    	return fl;
    }
    /**
     * Sets all the necessary features for certain JPanels in the program.
     * @param jp
     * @return jp
     */
    private static JPanel upper(JPanel jp)
    {
    	 jp.setBorder ( new EtchedBorder () );
		 jp.setForeground(new Color(210, 180, 140));
	     jp.setBackground(new Color(139, 69, 19));
	     return jp;
    }
     /**
     * This function sets all of the necessary features of the JTextArea's that are used in a certain place in the program.
     * @param jp
     * @return jp
     */
    private static JTextArea downer(JTextArea jp)
    {
    	jp.setBackground(new Color(139, 69, 19));
	    jp.setEditable ( false ); // set textArea non-editable
	    jp.setLineWrap(true);
	    Font font = new Font("Courier", Font.ITALIC, 11);
	    jp.setFont(font);	
	    jp.setForeground(Color.white);
	    return jp;
    }
}