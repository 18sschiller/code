/**
 * This class will take a string and reverse the letters of the string exactly 
 * and the words in a sentence using the property of recursion.
 * 
 * @author 18sschiller
 *
 *
 * 1. Recursion works by a method calling itself within itself. It continues to expand inside of itself
 * until it resolves, or reaches a base case, and then it resolves the rest of the versions of itself that it made.
 * 
 * 2. I think it was less efficient for the computer by using recursion in this program. There are other ways
 * to much more easily program this in a linear fashion. Along with that, it was less efficient for the me, the
 * programmer, because it is unfamiliar to me. However, if I became more familiar with it, it would be easier for
 * me because it is less lines of code and less complicated than the linear way.
 */

public class WordReverser 
{
	public static void main(String[] args) 
	{
		String str = "hi i am jon";
		System.out.println("Original:\n" + str);
		System.out.println("\nReversed Letters:\n" + reverse(str));
		System.out.println("\nReversed Sentence:\n" + reverseOrder(str));
	}
	
	/**
	 * This method takes a string and reverses all of the letters.
	 * 
	 * @param str
	 * @return string
	 */
	public static String reverse( String str)
	{
		if(str.length() <= 1) //if there is one left
			return str; //returns just the one and ends it. base case.
		else
			return str.charAt(str.length()-1) + reverse(str.substring(0,str.length()-1));
	}		// this returns the last character in the string and passes in the string without that character
	
	/**
	 * This method takes a sentence and reverses all of the words.
	 * @param str
	 * @return
	 */
	public static String reverseOrder( String str)
	{
		if(!(str.contains(" "))) //if there are still more spaces, there are more words
			return str; //returns the last word
		else{
			for (int i = str.length()-1; i > 0; i--) //runs from word length to the next space
				if(str.charAt(i) == ' ') //if the character in question is a space
					return str.substring((i+1),str.length()) + " " + reverseOrder(str.substring(0,i));
			return "ERROR"; //above returns the last word and passes in the string without the last word
		} //returns error to catch the "no return" problem
	}
}
