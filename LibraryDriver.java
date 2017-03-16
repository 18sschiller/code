import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryDriver 
{
	public static void main(String[] args)
	{
		//initializing a few books for the library so it starts out with a few books
		//each book has: title (string), author (string), isbn number (long), page number (int), subject (string), and a year (int).
		BookClass two = new BookClass("The Fault in Our Stars", "John Green", 199382757, 918, "Sad", 2003);
		BookClass three = new BookClass("The Catcher in the Rye", "J.D. Salinger", 314592653, 253, "Sad", 1996);
		BookClass four = new BookClass("Horton Hears a Who", "Dr. Seuss", 215298121, 19, "Silly", 1999);
		BookClass five = new BookClass("Crime and Punishment", "Fyodor Dostoevsky", 610375374, 548, "Scary", 1866);
		
		//an arraylist of books is made to hold the books together to pass into the library
		ArrayList<BookClass> hey = new ArrayList<BookClass>();
		hey.add(two); hey.add(three); hey.add(four); hey.add(five);
		
		//the library is made with the arraylist of books, a librarian, a location, and a name.
		LibraryClass one = new LibraryClass(hey, "Mrs.Test", "936 Sturgis Lane, Ambler, PA 19002", "The Big Library");
		
		//gives user the options for what to do in the library.
		//this will show up again if you type "411" in the bar for help
		System.out.println("Hello and welcome to my library. \nPlease select what you would like to do. \n(A) Print Library Info\n(B) Change Library Info\n(C) Add a Book\n(D) Delete a Book\n(E) Find Book Info\n(F) Sort Books by ISBN\n(G) Print All Books\n(H) Print All Library Authors\n(I) Search Books By Author\n(J) Change Book Info");
		
		/*
		int x = -1;
		while(x == -1)
		{
			x = JOptionPane.showConfirmDialog(null, "Choose One", "Choose One", JOptionPane.YES_NO_OPTION);
			
			if(x == 0)
				System.out.println("yes");
			else
				System.out.println("no");
			System.out.println("x: " + x);
		}
		*/
		//this opens all of the scanners because they were having A LOT of annoying problems
		String decide = "0";
		Scanner scanone = new Scanner( System.in); //for the decision cuz i didnt wanna mess with that
		Scanner scan4l = new Scanner(System.in); //for the long ints to keep them seperate
		Scanner scan4m = new Scanner(System.in); //for the regular integers
		Scanner scana = new Scanner( System.in); //for all of the strings besides the decision
		
		//keeps the program running until you type something over four characters
		//initially it was gonna be "stop", but no options are over four characters anyways so
		while(decide.length() != 4)
		{
			//resets "decide" and takes request
			decide = "hi";
			decide = scanone.nextLine();
		    
		    if(decide.equalsIgnoreCase("A")) //print library info
		    {
		    	//uses the regular library toString.
		    	System.out.println(one.toString());
		    }
		    else if(decide.equalsIgnoreCase("B")) //change library info
		    {
		    	System.out.println("What would you like to change:");
		    	System.out.println("(1) Library Name\n(2) Librarian\n(3) Location");
		    	String quq = scana.nextLine();
		    	if(quq.equals("1"))
		    	{
		    		//uses regular setName option for the library
		    		System.out.println("The current name of the library is " + one.getName() + ".\n");
		    		System.out.println("Please enter the new name:");
		    		String njnjn = scana.nextLine();
		    		one.setName(njnjn);
		    		System.out.println("Library Name Change Complete.");
		    	}
		    	else if(quq.equals("2"))
		    	{
		    		//same thing
		    		System.out.println("The current name of the librarian is " + one.getLibrarian() + ".\n");
		    		System.out.println("Please enter the new librarian's name:");
		    		String qjnjn = scana.nextLine();
		    		one.setLibrarian(qjnjn);
		    		System.out.println("Librarian Name Change Complete.");
		    	}
		    	else if(quq.equals("3"))
		    	{
		    		//i like making stuff more complicated im sorry.
		    		System.out.println("The current location of the library is " + one.getLocation() + ".\n");
		    		System.out.println("Please enter the new street number:");
		    		String wjnjn = scana.nextLine();
		    		System.out.println("Please enter the new street name:");
		    		String ejnjn = scana.nextLine();
		    		System.out.println("Please enter the new town or city:");
		    		String rjnjn = scana.nextLine();
		    		System.out.println("Please enter the new state abbreviation:");
		    		String tjnjn = scana.nextLine();
		    		System.out.println("Please enter the new zip code:");
		    		String yjnjn = scana.nextLine();
		    		//formats the address correctly
		    		String str8 = wjnjn + " " + ejnjn + ", " + rjnjn + ", " + tjnjn + " " + yjnjn;
		    		one.setLocation(str8);
		    		System.out.println("Library Location Change Complete.");
		    	}
		    	else
		    	{
		    		//safe data bitch
		    		System.out.println("Not an option.");
		    	}
		    }
		    else if(decide.equalsIgnoreCase("411"))
		    {
		    	//help screen
		    	System.out.println("Hello and welcome to my library. \nPlease select what you would like to do. \n(A) Print Library Info\n(B) Change Library Info\n(C) Add a Book\n(D) Delete a Book\n(E) Find Book Info\n(F) Sort Books by ISBN\n(G) Print All Books\n(H) Print All Library Authors\n(I) Search Books By Author\n(J) Change Book Info");
		    }
		    else if(decide.equalsIgnoreCase("C")) //add a book
		    {
		    	//this took a really long time because i forgot i made a function to add books
		    	System.out.println("Please enter the following.\nBook Title:");
				String tt = scana.nextLine();
			    System.out.println("\nAuthor:");
				String aa = scana.nextLine();
			    System.out.println("\nSubject:");
				String ss = scana.nextLine();			    
				System.out.println("\nISBN:");
				long ii = scan4l.nextLong();
			    System.out.println("\nPage Number:");
			    int pp = scan4m.nextInt();
			    System.out.println("\nYear:");
				int yy = scan4m.nextInt();
			    one.addBook(tt, aa, ii, pp, ss, yy);
			    System.out.println("Book Added.");
		    }	   
		    else if(decide.equalsIgnoreCase("D")) //delete a book
		    {
		    	//way easier to do
		    	System.out.println("Please enter the name of the book you would like to delete:\n");
				String nombre = scana.nextLine();
			    one.deleteBook(nombre);
			    System.out.println("Book Deleted.");
		    }
		    else if(decide.equalsIgnoreCase("E")) //find book info
		    {
		    	//also really easy
		    	System.out.println("Please enter the name of the book you are searching:\n");
				String nuy = scana.nextLine();
			    BookClass xk = one.findBookByTitle(nuy);
			    System.out.println(xk.toString());
		    }
		    else if(decide.equalsIgnoreCase("F")) //sort by isbn
		    {
		    	//really easy too
		    	String hq = one.sortBooksByISBN();
		    	System.out.println(hq);
		    }
		    else if(decide.equalsIgnoreCase("G")) //print all books
		    {
		    	//sets a temporary array
		    	ArrayList<BookClass> hi = one.getBooks();
		    	//prints them all out
		    	System.out.println("All Books:\n");
		    	for (int i = 0; i < hi.size(); i++) 
		    	{
					System.out.println(hi.get(i) + "\n");
				}
		    }
		    else if(decide.equalsIgnoreCase("H")) //print all authors
		    {
		    	//really easy still
		    	String aut = one.printAuthors();
		    	System.out.println(aut);
		    }
		    else if(decide.equalsIgnoreCase("I")) //search book by author
		    {
		    	//easy
		    	System.out.println("Please enter the author:\n");
				String al = scana.nextLine();			    
			    String jier = one.displayAllBooksFromAuthor(al);
			    System.out.println(jier);
		    }
		    else if(decide.equalsIgnoreCase("J")) //change book info
		    {
		    	//this one was a little bit harder
		    	System.out.println("Please enter the title of the book you want to change:");
		    	String tito = scana.nextLine();
		    	BookClass nwew = one.findBookByTitle(tito);
		    	System.out.println("Please choose what you would like to change:\n(1) Title\n(2) Author\n(3) ISBN\n(4) Subject\n(5) Page Number\n(6) Year");
		    	String jaz = scana.nextLine();
		    	if(jaz.equals("1"))
		    	{
		    		//i had to get the new info and make a new book with the same old info besides the new piece
		    		//and then i deleted the old book because i didnt know how to access and change the individual book information
		    		System.out.println("The current title is " + nwew.getTitle() + ".");
		    		System.out.println("Please enter in your new title:");
		    		String oak = scana.nextLine();
		    		one.addBook(oak, nwew.getAuthor(), nwew.getISBN(), nwew.getPages(), nwew.getSubject(), nwew.getYear());
		    		one.deleteBook(tito);
		    		System.out.println("Title Change Complete.");
		    	}
		    	else if(jaz.equals("2"))
		    	{
		    		System.out.println("The current author is " + nwew.getAuthor() + ".");
		    		System.out.println("Please enter in the new author's name:");
		    		String maple = scana.nextLine();
		    		one.addBook(nwew.getTitle(), maple, nwew.getISBN(), nwew.getPages(), nwew.getSubject(), nwew.getYear());
		    		one.deleteBook(tito);
		    		System.out.println("Author Change Complete.");
		    	}
		    	else if(jaz.equals("3"))
		    	{
		    		System.out.println("The current ISBN Number is " + nwew.getISBN() + ".");
		    		System.out.println("Please enter in your new ISBN Number:");
		    		long cherry = scan4l.nextLong();
		    		one.addBook(nwew.getTitle(), nwew.getAuthor(), cherry, nwew.getPages(), nwew.getSubject(), nwew.getYear());
		    		one.deleteBook(tito);
		    		System.out.println("ISBN Number Change Complete.");
		    	}
		    	else if(jaz.equals("4"))
		    	{
		    		System.out.println("The current page number is " + nwew.getPages() + ".");
		    		System.out.println("Please enter in the new page total:");
		    		int willow = scan4m.nextInt();
		    		one.addBook(nwew.getTitle(), nwew.getAuthor(), nwew.getISBN(), willow, nwew.getSubject(), nwew.getYear());
		    		one.deleteBook(tito);
		    		System.out.println("Title Change Complete.");
		    	}
		    	else if(jaz.equals("5"))
		    	{
		    		System.out.println("The current subject is " + nwew.getSubject() + ".");
		    		System.out.println("Please enter in your new subject:");
		    		String ash = scana.nextLine();
		    		one.addBook(nwew.getTitle(), nwew.getAuthor(), nwew.getISBN(), nwew.getPages(), ash, nwew.getYear());
		    		one.deleteBook(tito);
		    		System.out.println("Subject Change Complete.");
		    	}
		    	else if(jaz.equals("6"))
		    	{
		    		System.out.println("The current year is " + nwew.getYear() + ".");
		    		System.out.println("Please enter in your new year:");
		    		int pine = scan4m.nextInt();
		    		one.addBook(nwew.getTitle(), nwew.getAuthor(), nwew.getISBN(), nwew.getPages(), nwew.getSubject(), pine);
		    		one.deleteBook(tito);
		    		System.out.println("Year Change Complete.");
		    	}
		    	
		    }
		    else
		    {
		    	if(decide.length() == 4)
		    	{
		    		System.out.println("Thank you and have a nice day!");
		    	}
		    	else
		    	{
		    		System.out.println("Not an option.");
		    	}
		    }
		}
		//closing all scanners
		scana.close();
		scan4l.close();
	    scan4m.close();
		scanone.close();
		
		ArrayList<BookClass> a = one.getBooks();
		String adder = "All Library Books:\n--------------------\n\n";
		String jjjj = "";
		for (int i = 0; i < a.size(); i++) 
		{
			BookClass temp = a.get(i);
			jjjj = temp.toString();
			adder += jjjj;
			adder += "\n";
		}
		try 
		{
			LibraryClass.fileWriter(adder);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
}
	
