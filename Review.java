public class Review 
{
	public static void main( String[] args)
	{
		   int[] x = {1,3,5,7,8,11,14,18,19,22};
		   int g = 5;
		   System.out.println(g + " is at position:\t" + binarySearch(x,g));
		   
		   System.out.println((g+2) + " is at position:\t" + binaryRecursive(x, g+2));
	}	
	
	/**
	 * This method helps the binary recursive search run by making it easier in the main method to write it in.
	 * It will fill out two of the missing pieces of information, making it easier for the user.
	 * @param arr
	 * @param target
	 * @return
	 */
	public static int binaryRecursive(int[] arr, int target)
	{
		return binaryRecursiveRunner(arr, target, arr.length-1, 0);
	}
	
	/**
	 * This method runs a binary search recursively to find an integer value in an integer array.
	 * PRECONDITION: The array submitted must be sorted from least to greatest.
	 * POSTCONDITION: The position of the target number or a -1, if target was not found, will be returned.
	 * @param arr
	 * @param target
	 * @param high
	 * @param low
	 * @return position
	 */
	public static int binaryRecursiveRunner(int [] arr, int target, int high, int low)
	{
		if(low > high) return -1; // returns -1 if target is not found
		if(arr[(high + low)/2] == target) return (high + low)/2; // returns position of target if currently at target
		else if(arr[(high + low)/2] < target) return binaryRecursiveRunner(arr, target, high, ((high + low)/2)-1); // runs again with new minimum if target is greater than minimum
		else return binaryRecursiveRunner(arr, target, ((high + low)/2)+1, low); // runs again with new maximum if target is less than medium
	}

	/**
	 * Your code.
	 * @param array
	 * @param value
	 * @return
	 */
	public static int binarySearch(int[] array, int value)
	{
		int first, last, middle = 0, position;   // Position of search value
		boolean found;   // Flag

	    first = 0;
	    last = array.length - 1;
	    position = -1;
	    found = false;

	    while (!found && first <= last)
	    {
	    	middle = (first + last) / 2; // Calculate mid point
	        if (array[middle] == value) // If value is found at mid
	        {
	        	found = true;
	            position = middle;
	            System.out.println(middle);
	        }
	        else if (array[middle] > value) // If value is in
	            last = middle - 1;
	        else
	            first = middle + 1;         // If value is in
	    }
	    return position;
	 }
}
