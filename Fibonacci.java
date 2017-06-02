import java.util.Scanner;
/**
 * The outcome of improving the efficiency was actually astounding.
 * It was so cool that I showed my friends when I finished. I started by
 * testing a relatively low number, 8, to get the result of 21. The original
 * method ran 41 times and the new one ran 13 times. I was already impressed,
 * but as I tested larger numbers, I was more impressed. Testing the 40th term,
 * the new method ran 77 times while the new method ran an outrageous 200 million
 * times. This was shocking at first, but it makes perfect sense, really,
 * because if you were to imagine it like a tree, the original method needs to
 * examine every single leaf on every single twig on all the branches on the
 * whole tree. In the new program, it will remember the first branch and never
 * even need to look at the other ones.
 * 
 * @author 18sschiller
 */
public class Fibonacci 
{
	public static long[] fibNums; //stores all the calculated values
	public static int countOld; //counts how many times the original method runs
	public static int countNew; //counts runs of the new method
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in); //scans user imput
		long n;   //long type variable used and can only go to 20, 21 produces an error
		
		System.out.println("Which Fibonacci Term: ");
		n = sc.nextLong(); //reads in number
		fibNums = new long[(int)n]; //declares array to size of highest number
		for (int i = 0; i < n; i++) { //makes all of them a non-accident number
			fibNums[i] = -999;
		}
		countOld = 0; //sets the counters
		countNew = 0; // to zero
		
		System.out.println("\nOriginal Recursive\nAnswer: " + RecursiveFibonacci(n)); //prints results
		System.out.println("Number of Runs:\t" + countOld); //prints counter
		System.out.println("========================================");
		System.out.println("Modified Recursive\nAnswer: " + NewFibonacci(n)); //prints same result
		System.out.println("Number of Runs:\t" + countNew); //hopefully lower counter
	}
	
	/**
	 * Uses recursion to calculate the value of the nth Fibonacci term.
	 * Runs more efficiently by remembering all previous calculations.
	 * @param n
	 * @return fibNum
	 */
	public static long NewFibonacci( long n)
	{
		countNew++; //counts every run through
		if( n <= 2) //two is the lowest it will go
			return 1; // BASE CASE - ends program
		else if( fibNums[((int)n)-1] != -999) //checks if value was changed
			return fibNums[((int)n)-1]; //will recall previously calculated values
		else{ //will store the value so it will not calculate it again
			fibNums[((int)n)-1] = NewFibonacci(n-1) + NewFibonacci(n-2);
			return fibNums[((int)n)-1]; //returns the new number to the next run
		}
	}

	/**
	 * Calculates the value of the nth Fibonacci term using recursion.
	 * @param n
	 * @return
	 */
	public static long RecursiveFibonacci(long n)
	{
		countOld++; //counts each run of method
		if( n <= 2) //if it reaches the lowest case
			return 1; //BASE CASE - ends the loop
		else //if it can still go down more, will calculate next term
			return RecursiveFibonacci(n -1) + RecursiveFibonacci( n - 2);
	}
}