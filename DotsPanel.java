/**
 * QUESTIONS
 * 
 * 1. I measured the following:
 * 		- The dog's tounge, 223 pixels / 4.79 cm / 6*10^-18 light year
 * 		- The dog's eye, 35 pixels / 0.75 cm / 1*10^-18 light year    
 * 		- Ear to nose and back, 1046 pixels / 22.45 cm / 2.9*10^-17 light year
 * 		- Smallest visible tooth, 9 pixels / 0.19 cm / 0 light year
 * 		- Shakily traced whole dog, 2112 pixels / 45.4 cm / 5.9*10^-17 light year
 * 
 * 2. The scope of an object is important because the paint function needs to
 * be called every single time you need to add anything to the screen, which resets
 * all of the current things, so being able to keep track of the necessary
 * information is important.
 * 
 * 3. The most difficult part was trying to do things that were almost impossible.
 * The first impossible task I tried to do was find a direct conversion from pixels
 * to centimeters, which some claim exists and others don't. I ended up physically
 * measuring out my screen and finding it through resolution. Another impossible
 * thing I tried to do was shorten the distance between the dots when you drag your
 * mouse really fast by optimizing code. I couldn't shorten it at all.
 * 
 * 4. Measurements of this type could be useful for stuff like using proportions or
 * for tracing objects that can't be measured with a ruler like a dog's face. It can
 * also be used for how well certain things will fit on the screen, like if you were
 * designing an unrelated GUI and wanted to see how it would all fit together.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;


/**
 * Makes the GUI for the panel.
 * @author 18sschiller
 *
 */
public class DotsPanel extends JPanel
{
   private final int WIDTH = 1200, HEIGHT = 650; //dimensions
   private final int RADIUS = 6; //radius of dot

   private ArrayList<Point> pointList; //array to store point positions
   private long total; //counts the distance traveled
   private BufferedImage img; //dog picture
   private int resetCount; //counts how many times the reset button was pushed
   private int othLis;
   private Color[] colors = {
		   Color.RED, Color.CYAN,
		   Color.GREEN, Color.BLUE, //color array to change drawing color
		   Color.ORANGE, Color.blue,
		   Color.MAGENTA, Color.gray
   };
   private int colorPicker;   //changes color if reset is clicked
   
   private DecimalFormat[] d = {
		   new DecimalFormat("##.##"),
		   new DecimalFormat("##.####"),   //number formats for the distances displayed
		   new DecimalFormat("#.##################")
   };
   //-----------------------------------------------------------------
   //  Sets up this panel to listen for mouse events.
   //-----------------------------------------------------------------
   public DotsPanel()
   {
      try {
           img = ImageIO.read(new File("img.jpg"));  //reads in the picture of the dog
      } catch(IOException e) {
           e.printStackTrace();
      }
	  total = 0;
	  othLis = 0;
	  resetCount = 0; //sets all the counters
	  colorPicker = 0;
      pointList = new ArrayList<Point>();
      pointList.add(new Point(1,1));
      DotsListener dlsn = new DotsListener();
      addMouseListener (dlsn);
      addMouseMotionListener(dlsn);

      setBackground (Color.black);
      setPreferredSize (new Dimension(WIDTH, HEIGHT));
   }

   //-----------------------------------------------------------------
   //  Draws all of the dots stored in the list.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent(page); //inherited parent class paint component
      page.drawImage(img, 0, 0, getWidth(), getHeight(), this); //draws the dog background
      
      if(resetCount > 1) //if the button is clicked
      {
    	  resetCount = 0;
    	  total = 0;		//resets
    	  colorPicker++;
    	  pointList.clear();
    	  pointList.add(new Point(1,1));
      }
      
      page.setColor (colors[colorPicker%8]); //sets the line color
      for (Point drawPoint : pointList)
      {
         page.fillOval (drawPoint.x - RADIUS, drawPoint.y - RADIUS, //fills in all of the line space
                        RADIUS * 2, RADIUS * 2);
      }
      
      page.setColor(Color.black);
      page.setFont(new Font("TimesRoman", Font.BOLD, 15)); //sets the text font and format

      page.drawString ("Pixels:   " + total, 5, 15); //prints out pixels traveled
      page.drawString ("CM:        " + d[0].format(total * 0.02145853), 5, 35); //centimeters traveled
      page.drawString("Miles:    " + d[1].format((total * 0.02145853) * 0.00000621371), 5, 55); //miles
      page.drawString("Light Years:  " + d[2].format((total * 0.0000000000000000000279666882771)), 5, HEIGHT-15); //light years
      page.fillRect(WIDTH-75, 0, 75, 40); //makes the reset button
      page.setColor(Color.white);
      page.setFont(new Font("TimesRoman", Font.PLAIN, 14));
      page.drawString("Click here", WIDTH-68, 17);
      page.drawString("to reset!", WIDTH-60, 30); //makes the text for resetting
   }

   //*****************************************************************
   //  Represents the listener for mouse events.
   //*****************************************************************
   private class DotsListener implements MouseListener, MouseMotionListener
   {
      //--------------------------------------------------------------
      //  Adds the current point to the list of points and redraws
      //  whenever the mouse button is pressed.
      //--------------------------------------------------------------
      public void mousePressed (MouseEvent event)
      {
         pointList.add (event.getPoint());
         if(event.getPoint().getX() > WIDTH-75 && event.getPoint().getY() < 40)
         {
        	 resetCount++;
         }
         repaint();
      }
      //---------------------------------------------------------------
      //  Provide empty definitions for unused event methods.
      //---------------------------------------------------------------
      public void mouseClicked (MouseEvent event) {
          if(event.getPoint().getX() > WIDTH-75 && event.getPoint().getY() < 40)
         	 resetCount++;
      }
      public void mouseReleased (MouseEvent event) {}
      public void mouseEntered (MouseEvent event) {}
      public void mouseExited (MouseEvent event) {}
      public void mouseDragged(MouseEvent arg0) 
      {
    	  pointList.add(arg0.getPoint());
    	  total += pointList.get(pointList.size()-1).distance(pointList.get(pointList.size()-2));
    	  othLis++;
    	  if(othLis > 0 && othLis % 30 == 0)
    		  colorPicker++;
    	  repaint();
      }
      public void mouseMoved(MouseEvent arg0) {
    	  pointList.add(arg0.getPoint());
    	  total += pointList.get(pointList.size()-1).distance(pointList.get(pointList.size()-2));
    	  repaint();
      }
   }
}
