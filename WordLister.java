// WordLister.java

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads a list of words from a text file and displays them.
 * 
 */
public class WordLister 
{
	public static void main(String[] args) throws IOException 
	{
		String line;
		String word;
	
		//Open the text file for reading
		@SuppressWarnings("resource")
		Scanner input = new Scanner(new File("Dictionary.txt")); //scans the file
		ArrayList<String> arr = new ArrayList<String>(); //makes the arraylist
		
		while(input.hasNext()) //checks to make sure there is a next input
		{
			line = input.nextLine(); //reads the next line
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(line); //scans in the line
			
			while(sc.hasNext()) //checks to make sure there's another line
			{
				word = sc.next(); //sets the word as the String
				word = word.toLowerCase(); //makes it lower case
				int k = 0; //counter in case the word is the last in the alphabet
				if(!(arr.contains(word))) //does not add the word if it is already in the list
				{
					for (int i = 0; i < arr.size(); i++) //if it is not the first word, it does a for-loop
					{
					    if(word.compareTo(arr.get(i)) < 0) //compares the new word to the term in question in the array
					    {
					    	WordLister.addbeginning(arr, i); //sends it to the static function to add a spot where the new word will go
					    	arr.add(i,word); //adds the word to the empty spot
					    	i = arr.size(); //ends the for-loop
					    	k++; //skips the next if-statement because the word was already added
					    }
					}
					if(k==0) //if the word is not the first word and it wasn't added yet
						arr.add(word); //adds the word
				}	
			}	
		}
		System.out.println(arr.toString()); //prints out the entire array list.
	}
	/**
	 * Takes the original ArrayList and inserts a spot for the new word where the new word should go alphabetically.
	 * @param arr
	 * @param position
	 * @return narr The ArrayList with the new space for the new word.
	 */
	public static ArrayList<String> addbeginning(ArrayList<String> arr, int position)
	{
		ArrayList<String> narr = new ArrayList<String>(); //declares a new arraylist
		for (int i = 0; i < arr.size(); i++) //runs to the size of the original array
		{
			if(i == position) //if it has iterated to the right position
				narr.add("XXXX"); //add the blank space to the new array list
			narr.add(arr.get(i)); //adds the next term from the original to the new 
		}
		return narr; //returns the new arraylist
	}
}