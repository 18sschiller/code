import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The rectangle class is for making rectangles. It stores the x and y coordinates of the beginning of the rectangle and it also
 * stores the width and height of the rectangle. It has a lovely toString method and can also write out to file and read in from
 * file. It also has useful functions that have to do with rectangles like finding the tallest or shortest one in a list.
 * @author 18sschiller
 *
 */
public class Rectangle 
{
	public int xstart, ystart;
	public int width, height;
	/**
	 * Sets the rectangle's x and y starting coordinates, and their width and height.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rectangle(int x, int y, int width, int height)
	{
		setXstart(x);
		setYstart(y);
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Creates the rectangle's printable string.
	 */
	public String toString()
	{
		String str = "";
		str += "\nX:\t" + xstart;
		str += "\tY:\t" + ystart;
		str += "\tW:\t" + width;
		str += "\tH: \t" + height;
		return str;
	}
	
	/**
	 * Finds the tallest rectangle in an ArrayList of rectangles.
	 * @param arr 
	 * @return tallest rectangle
	 */
	public static Rectangle findOne(ArrayList<Rectangle> arr)
	{
		int biggest = 0;
		Rectangle theOne = null;
		for (int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getHeight() > biggest){
				biggest = arr.get(i).getHeight();
				theOne = arr.get(i);
			}
		}
		return theOne;
	}
	/**
	 * Finds the shortest rectangle in an ArrayList of rectangles.
	 * @param arr 
	 * @return shortest rectangle
	 */
	public static Rectangle findOther(ArrayList<Rectangle> arr)
	{
		int smallest = 1000;
		Rectangle theOne = null;
		for (int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getHeight() < smallest){
				smallest = arr.get(i).getHeight();
				theOne = arr.get(i);
			}
		}
		return theOne;
	}
	
	/**
	 * Sets a rectangle to have the same height as another rectangle.
	 * @param i
	 */
	public void setAll(Rectangle i){
		this.ystart = i.ystart;
		this.height = i.height;
	}
	
	public int getXstart() {
		return xstart;
	}
	public void setXstart(int xstart) {
		this.xstart = xstart;
	}
	public int getYstart() {
		return ystart;
	}
	public void setYstart(int ystart) {
		this.ystart = ystart;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Makes the rectangle into a printable string for a file.
	 * @param arr
	 */
	public static void fileToString(ArrayList<Rectangle> arr)
	{
		String str = "";
		for (int i = 0; i < arr.size(); i++) 
		{
			str += arr.get(i).getXstart() + ",";
			str += arr.get(i).getYstart() + ",";
			str += arr.get(i).getWidth() + ",";
			str += arr.get(i).getHeight() + "\n";
		}
		try {fileWriter(str, "rect");} catch (IOException e) {e.printStackTrace();}
	}
	/**
	 * Writes an ArrayList of rectangles out to file.
	 * @param data
	 * @param fileName
	 * @throws IOException
	 */
	public static void fileWriter(String data, String fileName) throws IOException 
   {
		BufferedWriter output = new BufferedWriter(new FileWriter(fileName + ".txt"));
		output.write(data); //writes out the data
		output.close(); //closes the output
   }
	/**
	 * Reads an ArrayList of rectangles in from file.
	 * @return r
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Rectangle> readFile() throws FileNotFoundException 
	{
		Scanner input = new Scanner(new File("rect.txt")); //makes a new file scanner
		ArrayList<String> data = new ArrayList<String>(); //temporary string
		while (input.hasNextLine()) //checker to make sure it doesn't screw up
			data.add(input.nextLine()); //adds to the data
		input.close(); //closes the scanner stream
		String[] s = new String[4];
		ArrayList<Rectangle> r = new ArrayList<Rectangle>();
		for (int i = 0; i < data.size(); i++) {
			s = data.get(i).split(",");
			r.add(new Rectangle(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3])));
		}
		return r; //returns all the data
	}
}
