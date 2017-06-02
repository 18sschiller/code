import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class BarHeights extends Applet
{
	//-----------------------------------------------------------
	//	Paints bars of varying heights, tracking the tallest and
	//	shortest bars, which are redrawn in color at the end.
	//-----------------------------------------------------------
	public void paint( Graphics page)
	{
		final int NUMBARS = 10, WIDTH = 30, MAXHEIGHT = 300, GAP = 9;
		int tallx = 0, tallest = 0, shortx = 0, shortest = MAXHEIGHT;
		int x, height;
		
		Random generator = new Random();
		setBackground( Color.black);
		
		page.setColor( Color.blue);
		x = GAP;
		
		for( int count = 0; count < NUMBARS; count++	)
		{
			height = generator.nextInt(MAXHEIGHT) + 1;
			page.fillRect(x, MAXHEIGHT-height, WIDTH, height);
			
			//keep track of the tallest and shortest bars
			if( height > tallest)
			{
				tallx = x;
				tallest = height;
			}
			
			if( height < shortest)
			{
				shortx = x;
				shortest = height;
			}
			
			x = x + WIDTH + GAP;
		}
		
		//redraw the tallest bar in red
		page.setColor(Color.red);
		page.fillRect(tallx, MAXHEIGHT-tallest, WIDTH, tallest);
		
		//redraw the shortest bar in yellow
		page.setColor(Color.yellow);
		page.fillRect(shortx, MAXHEIGHT-shortest, WIDTH, shortest);
	}
}
