/**
 * This class file is code to traverse a maze using recursive code methods.
 * Change the main method maze declaration from true to false to switch mazes.
 * 
 * @author 18sschiller
 *
 */
public class TraverseMaze 
{
	public static void main(String[] args) 
	{
		char[][] maze = makeMaze(false); //setting this as TRUE is maze #1, FALSE is maze #2
		traverseTheMaze(maze, 0, 2, -1); //starts the maze
	}
	
	/**
	 * Prints out the maze all pretty-like.
	 * @param maze
	 */
	public static void printMaze(char[][] maze)
	{
		System.out.println("\n\n"); //makes space at the beginning
		for(int x = 0; x < 12; x++){ //runs to size of x-axis
			System.out.print(" "); //spaces between maze parts
			for(int y = 0; y < 12; y++) //runs to size of y-axis
				System.out.print(maze[y][x] + "  "); //prints out the character
			System.out.println(""); //new line after each row
		}
	}
	
	/**
	 * Makes the original maze using hard code.
	 * 
	 * @param l
	 * @return maze
	 */
	public static char[][] makeMaze(boolean l)
	{
		char[][] maze = new char[12][12]; //makes the 2d array
		String maze1; //declares maze string
		if(l == true)																																						
			maze1 = "############" + "#...#......#" + "..#.#.####.#" + "###......#.#" + "#....###.#.#" + "######.#.#.#" + "#..#.#.#.#.#" + "##.#.#.#.#.#" + "#........#.#" + "#####.#####." + "#......#...#" + "############";
		else //will run either the maze 1 or maze 2 based on the boolean passed
			maze1 = "############" + "#...#......#" + "..#.#.####.#" + "###.#....#.#" + "#.....##.#.." + "######.#.#.#" + "#..#.#.#.#.#" + "##.#.#.#.#.#" + "#........#.#" + "#####.####.#" + "#.......#..#" + "############";
		for(int x = 0; x < 12; x++) //runs to size of x-axis
			for(int y = 0; y < 12; y++) //runs to size of y-axis
				maze[y][x] = maze1.charAt(y+(x*12)); //sets character right
		return maze; //returns the completed maze
	}
	
	/**
	 * Runs through the maze to find the exit.
	 * PRECONDITION:  Must pass in -1 as the original direction, or "dir".
	 * POSTCONDITION: 'O' will have completed the maze.
	 * 
	 * @param maze
	 * @param posx
	 * @param posy
	 * @param dir
	 */
	public static void traverseTheMaze(char[][] maze, int posx, int posy, int dir)
	{
		try {Thread.sleep(400);} //delays so user can see process
		catch (InterruptedException e) {e.printStackTrace();}
		
		maze[posx][posy] = 'O'; //sets the current position as O
		printMaze(maze);		// immediately prints it, 
		maze[posx][posy] = '.'; // and then resets it for the next move
	
		if(posx==11 || (dir != -1 && posx ==0)) //if it has reached the final column,
			System.out.println("\n\nMaze Complete."); //or it has reached the first again
		else if(maze[posx][posy-1] == '.' && dir != 2) //if it can move upward and its not going down
			traverseTheMaze(maze, posx, posy-1, 0);
		else if(maze[posx+1][posy] == '.' && dir != 3) //if it can move forward and its not going backward
			traverseTheMaze(maze, posx+1, posy, 1);
		else if(maze[posx][posy+1] == '.' && dir != 0) //if it can move downward and its not going up
			traverseTheMaze(maze, posx, posy+1, 2);
		else if(maze[posx-1][posy] == '.' && dir != 1) //if it can move backward and its not going forward
			traverseTheMaze(maze, posx-1, posy, 3);
		else
			traverseTheMaze(maze, posx, posy, 5); //otherwise, it will go back the way it came
	}
}