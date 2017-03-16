import java.awt.Point;
/**
 * This class is a child class of the Point class. It has everything the Point class has,
 * but it also has an extra piece of data, which is a double to store the importance of
 * the point.
 * @author 18sschiller
 *
 */
public class PointPlus extends Point
{
	private double importance; //stores importance of the point in relation to other points
	
	/**
	 * Constructor that takes a Point and casts it as a PointPlus.
	 * @param p
	 */
	public PointPlus(Point p)
	{
		super(p);
		setImportance(0);
	}
	/**
	 * Constructor that casts a Point with an importance as a PointPlus.
	 * @param p
	 * @param imp
	 */
	public PointPlus(Point p, double imp)
	{
		super(p);
		setImportance(imp);
	}
	/**
	 * Constructor that makes a PointPlus with coordinates and also an importance value.
	 * @param x
	 * @param y
	 * @param imp
	 */
	public PointPlus(int x, int y, double imp)
	{
		super(x, y);
		setImportance(imp);
	}
	/**
	 * Constructor that makes a Point and casts it as a PointPlus.
	 * @param x
	 * @param y
	 */
	public PointPlus(int x, int y)
	{
		super(x,y);
		setImportance(0);
	}
	
	/**
	 * Returns a String that prints the PointPlus out in the proper format.
	 * Proper Format Example:
	 * X:   81      Y:   17      Val:   21.12445431
	 * @return str
	 */
	public String toString()
	{
		String str = "";
		str += "X:  " + this.getX() + "      Y:   " + this.getY() + "      Val:  " + this.getImportance();
		return str;
	}
	
	/**
	 * Gets the value for importance.
	 * @return
	 */
	public double getImportance() {
		return importance;
	}

	/**
	 * Sets the value for importance.
	 * @param importance
	 */
	public void setImportance(double importance) {
		this.importance = importance;
	}
}
