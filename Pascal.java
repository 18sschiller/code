import java.util.Scanner;
/**
 * This class prints Pascal's triangle to the nth row. The printing method will stop working if numbers with
 * over four digits are generated, so if the user is using large numbers, they must change the boolean 'design'
 * to false, to use the standard print function.
 * 
 * @author 18sschiller
 */
public class Pascal 
{
    private static int totalRows; // remembers total number of rows for proper spacing
    public static boolean design = true; // controls the design of the print. switch to false for big numbers.
    
	public static void main(String[] args) 
	{
	   Scanner sc = new Scanner(System.in);
	   System.out.print("How many rows:\t");
	   totalRows = sc.nextInt();
       int[] nowRow = {1};
       makeTri(totalRows, nowRow);
       sc.close();
	}

	/**
	 * Generates the next line of Pascal's triangle, using the previous line.
	 * Runs recursively until the triangle is solved.
	 * @param n
	 * @param nowRow
	 */
	public static void makeTri(int n, int[] nowRow)
	{
		System.out.println(makePretty(nowRow)); //prints out the row using string method
		int[] lastRow = nowRow; // sets the current row as the previous row
		nowRow = new int[lastRow.length+1]; // resets current row to size plus one for the next row
		nowRow[0] = 1; // sets the first integer in the row as a one
		nowRow[lastRow.length] = 1; // sets the last integer in the row as a one
		if( n <= 0) // if it has reached the length of the run
			System.out.println("Done."); // ends the loop; BASE CASE
		else{
	        for (int j = 0; j < lastRow.length - 1; j++) //runs to length of last row
	        	nowRow[j + 1] = lastRow[j] + lastRow[j + 1]; //generates values for new row
	        makeTri(n-1, nowRow); //runs method again recursively until done
		}
	}  
	
	/**
	 * Makes the array of numbers for the row printable.
	 * Will print in a pretty fashion, unless 'design' is changed to false.
	 * @param nowRow
	 * @return prettyRow
	 */
	public static String makePretty(int[] nowRow)
	{
		String str = ""; // makes a new string
		if(design == false) // will run this if user changes the boolean
			for (int i = 0; i < nowRow.length; i++) // runs to size of array 
				str += nowRow[i] + "  "; // prints number and three spaces
		else{
			for( int i = 0; i <= ((totalRows-nowRow.length)); i++) // runs to total rows minus length of current row
				str += "  "; // puts spaces accordingly to make it look like a pyramid
			for (int q = 0; q < nowRow.length; q++) { //runs to the length of the array
				str += nowRow[q] + " "; // adds the standard amount of spaces
				if(nowRow[q] < 1000) // if it is less than 1000
					str += " "; // gives a space token
				if(nowRow[q] < 100) // if it is less than 100
					str += " "; // gives a space token 
				if(nowRow[q] < 10 && nowRow[q] != 1)
					str += " "; //gives another for being single digit
			}}
		return str; //returns the string
	}
}
