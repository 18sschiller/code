import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to use and run the Vigenere cipher. This encryption method uses a dual-alphabet system.
 * The new code is found by creating a two-dimensional array of letters, and using the original input as
 * the x-axis value and the key as the y-axis value. This program runs on auto-key, so it uses the regular
 * key and adds to the end of it the original output until they are the same length. Some cool functions of
 * this program are the safe data function and the space giver. The space giver remembers where the spaces
 * were in the original input and put them back when the decrypter is used. 
 * @author 18sschiller
 *
 */
public class Vigenere 
{
	public char[][] arr;
	public String codedd;
	public int checker = 0;
	public static ArrayList<String> spaceHolder;
	
	/**
	 * Object Instantiation.
	 * This just instantiates the object and establishes the array as instance data.
	 */
	public Vigenere()
	{
		arr = arrayMaker();
	}
	/**
	 * Two Dimensional Array Maker.
	 * This method creates a 26 X 26 character array of letters that is used for the encryption.
	 * @return thing
	 */
	public static char[][] arrayMaker()
	{
		char[][] thing = new char[26][26]; //declares new character array of size 26 X 26
		char ch = 'a'; //starts the characters to add
		for (int y = 0; y < 26; y++) //runs to the size of a row, also the alphabet
		{
			ch = (char) ('a' + y); //sets the character equal to a plus whatever row it is each new row
			for (int x = 0; x < 26; x++) //runs to the alphabet again for columns
			{
				thing[x][y] = ch; //sets the spot equal to the character iterated
				ch++; //adds one to the character so it goes to the next one
				if(ch > 'z') //if it reaches the end of the alphabet
					ch = (char) (ch - 26); //it goes back to letter a
			}
		}
		return thing; //returns the array filled with all of the letters
	}
	
	/**
	 * Menu user interface.
	 * This welcomes the user and asks what they would like to do.
	 * @return t
	 */
	public String userTalkerIntro()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); //opens scanner; can't close because used indeterminate amount of times.
		System.out.println("\nWould you like to encrypt or decrypt?\n1: Encrypt\n2: Decrypt\n3: Exit");
		String t = sc.nextLine(); //scans next line
		return t; //returns answer to driver
	}
	/**
	 * Encryption user interface. 
	 * This takes the input and the key and encrypts and stores it.
	 * @return playback
	 */
	public String userTalkerEncrypt()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); //opens new scanner
		System.out.println("\nPlease enter the word or phrase you would like encrypted:");
		String inp = sc.nextLine(); //gets the user input
		System.out.println("\nNow, please enter your secret key for encryption:");
		String kk = sc.nextLine(); //gets the user key
		String enc = this.encoder(inp, kk); //*********ENCODES the whole thing
		System.out.println("\nYour input is now encoded:\n========================");
		System.out.println(enc); //prints out the encoded text
		System.out.println("=======================\n\nWould you like to return to the main menu or exit?\n1: Main Menu\n2: Exit");
		String playback = sc.nextLine(); //what the user wants to do next
		if(playback.equals("2")) //sets it to the command that will exit the program
			playback = "3";
		return playback; //returns the answer
	}
	/**
	 * Decryption user interface.
	 * Accepts a key word, and then decrypts the code and returns the original input if the key was correct.
	 * It will not decrypt if nothing was encrypted yet. 
	 * @return playback
	 */
	public String userTalkerDecrypt()
	{
		if(checker == 0){ //checks to see if there is something encoded yet
			System.out.println("\nI'm sorry, you have nothing encrypted yet!\nPlease try encrypting something before using this.");
			return "0";} //it will not run if there is nothing to decode
		else{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); //makes a new scanner for the key
		System.out.println("\nPlease enter your chosen keyword for decryption:");
		String inp = sc.nextLine(); //gets the key from the user
		String enc = this.decoder(inp); //runs the decoder with the key
		System.out.println("\nYour code is now decoded:\n=======================");
		System.out.println(enc); //prints out the original decoded input (hopefully)
		System.out.println("=======================\n\nWould you like to return to the main menu or exit?\n1: Main Menu\n2: Exit");
		String playback = sc.nextLine(); //what the use wants to do next
		if(playback.equals("2"))
			playback = "3"; //sets the 2 to the exit function
		return playback; //returns choice
		}
	}
	
	/**
	 * Encrypts input with key.
	 * This method will take the key and the input and first run it through the safe data function,
	 * which will remove all non-letters and make everything lowercase. Then, it will create the auto-key
	 * and run it against the character array to make the encrypted text. It will then add to checker
	 * so that the decoder method can run and sets the coded text as instance data for easy access.
	 * @param input
	 * @param key
	 * @return coded
	 */
	public String encoder( String input, String key)
	{
		input = safeData(input, false); //runs safe data for the input
		key = safeData(key, true); //runs safe data for the key
		String modified = key; //sets the auto key as the key
		for (int i = 0; i < input.length() - key.length(); i++) { //creates the auto key
			modified += input.charAt(i); //adds to the end of the key until the key and the input are the same length
		}
		String coded = ""; //declares the coded string
		for (int i = 0; i < input.length(); i++) //runs the input length
			coded += arr[input.charAt(i)-97][modified.charAt(i)-97]; //retrieves the character on the array,
		codedd = coded; //using the original input as the x-axis and the auto-key as the y-axis
		coded = coded.toUpperCase(); //sets it to uppercase to make it pretty
		checker++; //allows the decoder method to run
		return coded; //returns the code
	}
	/**
	 * Decrypts input with key.
	 * This will take the key, and safe data that jawn. Then is will run it against
	 * the character array to get back the original code. Lastly, it will give back the spaces.
	 * @param key
	 * @return decoded
	 */
	public String decoder( String key) 
	{
		key = safeData(key, true); //runs the key through safe data
		String decoded = ""; //declares the string for decoded
		for (int i = 0; i < key.length(); i++) { //runs to the length of the key, which keeps expanding
			for (int j = 0; j < 26; j++) { //runs to the alphabet
				if((codedd.charAt(i)-97) == ((arr[j][key.charAt(i)-97])-97)) //if the code at the character is equal to the character in the array at the key position and the current iteration
				{
					decoded += arr[j][0]; //adds the letter to the decoded portion
					key += arr[j][0]; //adds the decoded portion to the end of the auto-key
					if(decoded.length()==codedd.length()) //if the decoded string is complete
					{
						i = key.length(); //ends the loop
						j = 27;} //ends the other loop
				}}}
		decoded = spaceGiver(decoded); //gives back the spaces to the original input
		return decoded; //returns the decoded portion
	}
	
	/**
	 * Makes the input runnable.
	 * This method will take the user input and remove everything that is not a letter.
	 * It will also set everything to lower case and if it is the input and not the key,
	 * it will remember where the spaces are so it can give them back when decoding.
	 * @param input
	 * @param isKey
	 * @return
	 */
	public String safeData( String input, boolean isKey)
	{
		input = input.toLowerCase(); //sets everything to lowercase
		char[] p = input.toCharArray(); //puts the input into a character array
		String n = ""; //sets a new string to hold the safe input
		for (int i = 0; i < input.length(); i++)  //runs to input length
		{
			if(p[i] >= 97 && p[i] <= 122) //if it is a lower case letter...
				n += p[i]; //adds to the protected input
			if(p[i] == 32) //if it is a space...
				if(isKey == false) //and it is not the key...
					n += p[i]; //adds the space to the protected input
		}
		if(isKey == false) //if it is not the key...
			n = spaces(n); //runs it through the spaces method
		return n; //returns the protected code
	}
	/**
	 * Remembers the spaces in the input.
	 * This one is really cool. It creates an array list of strings to remember where the spaces
	 * are in the input. It will remove the space and record what is in the string up until that
	 * point, so that when decoding, it can see that it is time to add the space back.
	 * @param input
	 * @return j
	 */
	public String spaces( String input)
	{
		ArrayList<String> holdingpin = new ArrayList<String>(); //creates a new array list
		String temp = ""; //creates a temporary string
		char[] p = input.toCharArray(); //sets the input to a character array for easy access
		for (int i = 0; i < input.length(); i++)  //runs to input length
		{
			temp = ""; //resets the temporary string
			if(input.charAt(i) == ' ') //if the character in question is a space
			{
				for (int j = 0; j < i; j++) //runs to the size of the string up to that point
					temp += p[j]; // creates a string that contains everything before that point in the string
				holdingpin.add(temp); //adds it to the array list
			}}
		spaceHolder = holdingpin; //sets the array list as instance data
		String j = ""; //another temporary string
		for (int i = 0; i < input.length(); i++) //runs to input length
			if(!(p[i] == ' ')) //if it is not a space
				j += p[i]; //basically it removes all the spaces
		return j; //returns the input string without spaces
	}
	/**
	 * Gives back spaces to decoded text.
	 * This just puts back all of the spaces that were taken out of
	 * the input before it was encrypted.
	 * @param output
	 * @return y
	 */
	public String spaceGiver( String output)
	{
		String y = ""; //sets new output string
		for (int i = 0; i < output.length(); i++) //runs to the size of the string
		{
			y += output.charAt(i); //adds the character to the new string
			for (int j = 0; j < spaceHolder.size(); j++) //runs to how many spaces there are saved
				if(y.equals(spaceHolder.get(j))) //if the existing string equals one of the spaces saved
				{
					y += " "; //adds a space to the string
					spaceHolder.remove(j); //removes the space from the array
				}}
		return y; //returns the output with the spaces
	}
}