import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class QuoteSorter 
{
	public static void main( String[] args) throws FileNotFoundException
	{
		String[] k = fileReader();
		shuffleArray(k);
		for (int i = 0; i < k.length; i++) 
		{
			System.out.println((i+1) + ":  " + k[i] + "\n");
		}	
	}
	
	public static void shuffleArray(String[] ar)
	{
	    Random rnd = ThreadLocalRandom.current();
	    String a = "";
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	}
	
	
	public static String[] fileReader() throws FileNotFoundException
	{
		String str = "";
		String[] lo;
		Scanner input = new Scanner(new File("quotes.txt")); //scans the file
		while(input.hasNext()) //checks to make sure there is a next input
		{
			str += input.nextLine();
		}
		input.close();
		lo = str.split("~");
		return lo;
	}
}

