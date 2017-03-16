import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
@SuppressWarnings("serial")

public class CityDriver extends Applet
{
	private final int APPLET_WIDTH = 1300;
	private final int APPLET_HEIGHT = 400;
	
	public void paint(Graphics page)
	{
		
		setBackground(Color.blue);
	    setSize (APPLET_WIDTH, APPLET_HEIGHT);
	    
	    Building x = new Building(60, 60, 60);
	    
	    Random r = new Random();
	    int g;
	    for (int i = 0; i < 6; i++){
	    	g = r.nextInt(2);
			x.draw(page, i, g);
			x.draw(page, g);
			x.draw(page);}
	    
	    //x.draw(page, true);
	}
}