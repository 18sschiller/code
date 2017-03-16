import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

public class Building 
{
	private int base; //sends in the base amount, aka what building it is
	private int x; //sends in the starting width
	private int y; //sends in the starting height
	private int width; //gets the unique building width
	private int height; //gets the unique building height
	private int saver; //saves a value for later, not important
	private ArrayList<Integer> SpacingY; //gets Y coordinates for all window rows
	private ArrayList<Integer> SpacingX; //gets X coordinates for all window columns
	
	public Building(int base1, int x1, int y1)
	{
		base = base1;
		x = x1;
		y = y1;
	}
	
/**
 * This function passes in the page, the number building it is at, and the random that decides if the building is there or not.
 * It will use a random factor to decide on random distances between buildings and random heights and widths.
 * Then, it will create an ArrayList of integers to record the spacing between the windows.
 * @param page
 * @param round
 * @param it
 */	
	   public void draw (Graphics page, int round, int it)
	   {
		  Random r = new Random(); //makes a new random number
		  int c = r.nextInt(100); //makes it between 0 and 150
		  round = round * 200; //adds space for each round through
	      page.setColor (Color.BLACK); //sets the color as white
	      if(it == 1) //if the it is passed in as zero, the building is not there
	      {
	    	  page.setColor(Color.blue); //still makes the building but sets it as black
	      }
	      int buildingStartX = base+round-(c/10); //makes the building start at this x coordinate
	      saver = buildingStartX; //saves it so i can use it in the next function
	      //this is the base number (60 pixels) which is regular spacing, plus what round it is to add 200,
	      // and then it subtracts a tenth of the random integer to move them around
	      int buildingStartY = y+c; //this makes a starting y coordinate at the original y, which is 60, plus the random
	      if(buildingStartY < 80) //if it is too tall
	    	  buildingStartY = buildingStartY + 50; //make it shorter
	      if(buildingStartY < 50) //if its a little too tall
	    	  buildingStartY = buildingStartY + 60; //make it a little bit shorter
	      if( c > 99) //if it is THE TRUMP TOWER
	      {
	    	  buildingStartY = buildingStartY - 20; //TRUMPIFY
	      	  buildingStartX = buildingStartX - 10;
	      	  x = x + 20;}
	      page.drawRect(buildingStartX, buildingStartY, x+c, 400-buildingStartY-8); //makes the building
	      page.setColor (Color.DARK_GRAY); //sets the color as white
	      if(it == 1) //if the it is passed in as zero, the building is not there
	    	  page.setColor(Color.blue); //still makes the building but sets it as black
	      if( c > 97) //if it is THE TRUMP TOWER
	    	  page.setColor(Color.magenta); //trumpify
	      page.fillRect(buildingStartX, buildingStartY, x+c, 400-buildingStartY-8);
	      //this makes a rectangle starting at the x and the y, and has a width of the original x (60) plus the random
	      //it has a height of the starting y minus 400 because the screen is 400 pixels tall
	      width = x+c; //finds the width by taking the x (60) and adding the random
	      height = (400-buildingStartY); //finds the height by subtracting the starting y-coordinate from the 400 pixel total
	      
	      SpacingY = returnFactor(height, buildingStartY); //finds the distance between windows and puts it in an array list
	      SpacingX = returnFactor(width, buildingStartX); //same as before but for the x-axis
	      
	      //page.drawLine(buildingStartX, buildingStartY, buildingStartX+(width/2), buildingStartY-40);
	      //page.drawLine(buildingStartX+(width/2), buildingStartY-40, buildingStartX+width, buildingStartY);
	      
	      int[] arrx = { (buildingStartX), (buildingStartX+(width/2)), (buildingStartX+width) }; //makes an int array for the top of the tower
	      int[] arry = { (buildingStartY), (buildingStartY-35), (buildingStartY) }; //makes the other one for the top
	      Polygon p = new Polygon(arrx, arry, 3); //makes the top of the tower
	      page.setColor (Color.black); //sets the color as white
	      if(it == 1) //if the it is passed in as zero, the building is not there
	    	  page.setColor(Color.blue); //still makes the building but sets it as black
	      page.drawPolygon(p); // draws the top of the tower
	      
	      //these two for-loops are the best way i could think of to make the edges of the building more defined
	      
	      for (int i = 0; i < 2; i++) 
	      {
			page.drawLine(buildingStartX, 400-height-i, buildingStartX+width, 400-height-i); 
			page.drawLine(buildingStartX, 400-height+i, buildingStartX+width, 400-height+i);
			page.drawLine(buildingStartX+i, 392, buildingStartX+i, 400-height); //makes a line just for fun
	        page.drawLine(buildingStartX+width-i, 392, buildingStartX+width-i, 400-height); //makes a line on the other side
		  }
	      for (int i = 0; i < 5; i++) 
	      {
	        page.drawLine(buildingStartX+i, 400-height, buildingStartX+(width/2), 400-height-32);
	        page.drawLine(buildingStartX+i, 400-height, buildingStartX+(width/2), 400-height-37);
	        page.drawLine(buildingStartX+width-i, 400-height, buildingStartX+(width/2), 400-height-32);
	        page.drawLine(buildingStartX+width-i, 400-height, buildingStartX+(width/2), 400-height-37);
	      }
	      
	      page.setColor (Color.DARK_GRAY); //sets the color as white
	      if(it == 1) //if the it is passed in as zero, the building is not there
	    	  page.setColor(Color.blue); //still makes the building but sets it as black
	      if( c > 97) //if it is THE TRUMP TOWER
	    	  page.setColor(Color.magenta); //trumpify
	      page.fillPolygon(p); //draws the top of the tower
	      if( c > 97) //if it is the trump tower
	      {
	    	  page.setColor(Color.black); //sets the font black
	    	  page.drawString("TRUMP", (buildingStartX+(width/2)-20), buildingStartY-10);} //writes TRUMP
	   }
	   
	   /**
	    * This function draws the window in relation to the building. It only runs if the building is there, or it is not 1.
	    * It runs to the rows and to the columns and it randomly decides if a window will be there or not.
	    * @param page
	    * @param it
	    */
	   
	   public void draw (Graphics page, int it)
	   {   
		   Random r = new Random(); // creates a new random for the windows
		   int p; //makes a temporary variable to be random to decide if the window is there
		   page.setColor(Color.white); //sets the page color as white
		   
		   if(it != 1) //if the random is not equal to one
		   {
			for (int i = 0; i < SpacingY.size(); i++) //runs to the how many rows there are
			{
			  for (int j = 0; j < SpacingX.size(); j++) //runs to how many columns there are
			  {
				 p = r.nextInt(150); //gets the next int to see if the window will be there
			     if(p < 100) //if the random is met, inconsequential
			     {
			       if(p < 20)
			    	   page.setColor(Color.cyan);
			       else if(p < 40)
			    	   page.setColor(Color.red);
			       else if(p < 60)
			    	   page.setColor(Color.magenta); //these randomize the window colors
			       else if(p < 80)
			    	   page.setColor(Color.green);
			       else
			    	   page.setColor(Color.yellow);
			       
				   if((SpacingX.get(j)+70) < (saver+width))
				   {
					   page.drawRect(SpacingX.get(j)+50, SpacingY.get(i)+50, 20, 20); //prints a window using the next draw function
					   page.fillRect(SpacingX.get(j)+50, SpacingY.get(i)+50, 20, 20); //fills the window with color
				   }
				   else if((SpacingX.get(j)+40) < (saver+width))
				   {
					   page.drawRect(SpacingX.get(j)+30, SpacingY.get(i)+50, 15, 20); //prints a window using the next draw function
					   page.fillRect(SpacingX.get(j)+30, SpacingY.get(i)+50, 15, 20); //fills the window with color
				   }
				   }}}}
	   }
	   
	   /**
	    * This private function is just used within my other functions. 
	    * This takes in the starting point of a building and the length/width of it,
	    * and then makes a list of coordinates where windows will be made.
	    * 
	    * @param whole
	    * @param start
	    * @return space
	    */
	   private ArrayList<Integer> returnFactor (int whole, int start)
	   {
		   ArrayList<Integer> space = new ArrayList<Integer>(); //creates an arraylist to hold the integer
		   whole = whole - 30; //subtracts 30 so that there is room on the sides
		   int remainder = whole % 40; //finds the remainder to see if there is room for a last row
		   int nxt = whole / 40; //divides it by how many rows/columns there are
		   if(remainder < 30) //if the remainder is less than 30
			   for (int i = 0; i < nxt; i++) //while it has not filled all the rows/columns
			   {
				   space.add(start + 15 + (40 * (i - 1))); //adds another row/column at the start plus the beginning space plus 40 for each new one
			   }	
		   else //if the remainder is regular
			   for (int i = 0; i < nxt-1; i++) //runs to one last row/column
			   {
				   space.add(start + 10 + (40 * (i-1))); //adds the same coordinate but with a bigger starting space
			   }
		   if(space.isEmpty()) //if there wasnt enough room, make a row anyways
			   space.add(start+5);
		   return space; //returns the ArrayList
	   }
	   
	   /**
	    * This draw function puts the finishing touches on the Applet. It adds a sun and it creates a floor.
	    * It is seperated so that the file is easier to read.
	    * @param page
	    */
	   	   
	   public void draw (Graphics page)
	   {
		  page.setColor(Color.yellow); //to make the sun
		  page.drawOval(-40, -40, 120, 120); //draws the sun
		  page.fillOval(-40, -40, 120, 120); //fills it in
	      page.setColor (Color.BLACK); //sets the color as white
	      page.drawRect(0, 392, 1300, 8); //makes the floor
	      page.fillRect(0, 392, 1300, 8); //fills it in
	   }
	   
	   public void draw ( Graphics page, boolean x)
	   {
		   Random r = new Random();
		   Color a = new Color(0,0,0);
		   ArrayList<Color> jq = new ArrayList<Color>();
		   int f;
		   int counter = 0;
		   
		   if(x == true)
		   {
			   
			   for (int i = 0; i < 255; i++) 
			   {
				for (int j = 0; j < 255; j++) 
				{
					for (int j2 = 0; j2 < 255; j2++) 
					{
						a = new Color(i,j,j2);
						jq.add(a);
					}
				}
			   }
			   
			   
			   
			   for (int i = 0; i < 400; i++) 
			   {
				for (int j = 0; j < 1300; j++) 
				{
					counter++;
					
					f = r.nextInt(12);
					System.out.println(f);
					
/*
					if( i % 2 == 0)
					{
						if( j % 2 == 0)
							f = 0;
						else
							f = 1;
					}
					else
					{
						if( j % 2 == 0)
							f = 1;
						else
							f = 0;
					}
					
*/
					
					if( f == 0)
						page.setColor(Color.blue);
					else if( f == 1)
						page.setColor(Color.red);
					else if( f == 2)
						page.setColor(Color.black);
					else if( f == 3)
						page.setColor(Color.lightGray);
					else if( f == 4)
						page.setColor(Color.white);
					else if( f == 5)
						page.setColor(Color.orange);
					else if( f == 6)
						page.setColor(Color.pink);
					else if( f == 7)
						page.setColor(Color.red);
					else if( f == 8)
						page.setColor(Color.white);
					else if( f == 9)
						page.setColor(Color.black);
					else if( f == 10)
						page.setColor(Color.BLUE);
					else if( f == 11)
						page.setColor(Color.CYAN);
					else if( f == 12)
						page.setColor(Color.GREEN);
					else
						page.setColor(Color.red);;
						
						
					page.setColor(jq.get(counter));
					
					page.drawLine(j, i, j, i);
				}
			   }
		   }
	   }
}
