import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver
{
	public static void main( String[] args) throws IOException
	{			
    	ArrayList<String> str1 = new ArrayList<String>();
    	ArrayList<String> str2 = new ArrayList<String>();
    	try {
			str1 = readFile( new File("file.txt"));
			str2 = readFile( new File("filetwo.txt"));
		} catch (FileNotFoundException e1) {e1.printStackTrace();}
    	int x = str1.size() / 7;
    	ArrayList<Recipe> ark = new ArrayList<Recipe>();
    	for (int i = 0; i < x; i++) 
    		ark.add(makeThem(str1, (7 * i)));
    	String str = CookBook.printDate();
		try {
			CookBook c = new CookBook(ark, str2.get(0), str);
			c.display();
		} catch (IOException e) {e.printStackTrace();}
	}
	public static ArrayList<String> readFile(File file) throws FileNotFoundException 
	{
		Scanner input = new Scanner(file); //makes a new file scanner
		ArrayList<String> data = new ArrayList<String>(); //temporary string
		while (input.hasNextLine()) //checker to make sure it doesn't screw up
		{
			data.add(input.nextLine()); //adds to the data
		}
		input.close(); //closes the scanner stream
		return data; //returns all the data
	}
	private static Recipe makeThem( ArrayList<String> arl, int x)
	{
		String[] ingr = seperator(arl.get(x+3));
		int prep = Integer.parseInt(arl.get(x+2));
		String[] steps = seperator(arl.get(x+4));
		ArrayList<Integer> cat = new ArrayList<Integer>();
		cat = seperatorr(arl.get(x+6));
		Recipe temp = new Recipe(arl.get(x), ingr, arl.get(x+1), arl.get(x+5), steps, prep, cat);
		return temp;
	}
	private static String[] seperator( String str)
	{
		String[] str2 = str.split("   ");
		return str2;
	}
	private static ArrayList<Integer> seperatorr( String str)
	{
		ArrayList<Integer> x = new ArrayList<Integer>();
		String[] str2 = str.split("   ");
		for (int i = 0; i < str2.length; i++) 
		{
			x.add(Integer.parseInt(str2[i]));
		}
		return x;
	}
}
