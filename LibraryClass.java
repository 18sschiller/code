import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryClass 
{
	private ArrayList<BookClass> books;
	private String librarian;
	private String location;
	private String name;	
	
	public LibraryClass(ArrayList<BookClass> Hello, String Hey, String Hi, String Whaddup)
	{
		setBooks(Hello);
		setLibrarian(Hey);
		setLocation(Hi);
		setName(Whaddup);
	}
	
	/**
	 * This function retrieves the name of the librarian as a string.
	 */
	public String getLibrarian()
	{
		return librarian;
	}
	/**
	 * This function lets the driver set the librarian as a string.
	 * @param librarian
	 */
	public void setLibrarian(String librarian) 
	{
		this.librarian = librarian;
	}
	/**
	 * This function retrieves the name of the library as a string.
	 * @return
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * This function takes the name of a book and retrieves all of the information from it in the form of a BookClass object.
	 * @param title
	 * @return BookClass
	 */
	public BookClass findBookByTitle(String title)
	{
		BookClass rt = null;
		for (int i = 0; i < books.size(); i++) 
		{
			BookClass temp = books.get(i);
			String titletemp = temp.getTitle();
			if(titletemp.equals(title))
			{
				rt = temp;
			}
		}
		return rt;
	}
	/**
	 * This function puts all of the book titles into one string so it can be printed.
	 * @return
	 */
	public String printBooks()
	{
		String str = "";
		str += "All Book Titles:\n";
		for (int i = 0; i < books.size(); i++) 
		{
			BookClass temp;
			temp = books.get(i);
			str += temp.getTitle() + "\n";
		}
		return str;
	}
	/**
	 * This file prints out all of the authors in the library in a string.
	 * @return
	 */
	public String printAuthors()
	{
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < books.size(); i++) 
		{
			BookClass temp;
			temp = books.get(i);
			if(!(list.contains(temp.getAuthor())))
			{
				list.add(temp.getAuthor());
			}
		}
		String str = "All Authors:\n";
		for (int i = 0; i < list.size(); i++) 
		{
			str += list.get(i);
			str += "\n";
		}
		return str;
	}
	/**
	 * This string uses a string that has an author's name, and returns all of the books in the Library with that author in the form of a string.
	 * @param author
	 * @return string
	 */
	public String displayAllBooksFromAuthor(String author)
	{
		String str = "All Books From Author:\n";
		
		for (int i = 0; i < books.size(); i++) 
		{
			BookClass temp;
			temp = books.get(i);
			String tempa = temp.getAuthor();
			if( author.equals(tempa))
			{
				str += temp.getTitle() + "\n";
			}
		}		
		return str;
	}
	/**
	 * This file allows the Driver to set the name of the library as a string.
	 * @param name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * This allows the driver to retrieve the location of the library in the form of a string.
	 * @return
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * This allows the Driver to set the location of the library in the form of a string.
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * This allows the Driver to retrieve the books of the library in the form of a string.
	 * @return
	 */
	public ArrayList<BookClass> getBooks() 
	{
		return books;
	}
	/**
	 * 	/**
	 * This allows the Driver to set the books of the library in the form of a string.
	 * @param books
	 */
	public void setBooks(ArrayList<BookClass> books) {
		this.books = books;
	}
	/**
	 * This function allows the Driver to add a book to the library in the form of a BookClass.
	 * @param newbook
	 */
	public void addBook(String hey, String hi, long hello, int howdy, String hola, int whassup)
	{
		BookClass newbook = new BookClass(hey, hi, hello, howdy, hola, whassup);
		books.add(newbook);
	}
	/**
	 * This allows the user to delete a book from the library by typing in the title.
	 * @param title
	 */
	public void deleteBook(String title)
	{
		for (int i = 0; i < books.size(); i++) 
		{
			BookClass temp = books.get(i);
			String titletemp = temp.getTitle();
			if(titletemp.equals(title))
			{
				books.remove(i);
			}
		}
	}
	/**
	 * This function allows the Driver to print a sorted list of the book titles by ISBN number.
	 * @return
	 */
	public String sortBooksByISBN()
	{
		BookClass temp;
		BookClass stem;
		BookClass sten;
		long tk;
		long tj;
		
		for (int i = 0; i < books.size(); i++) 
		{
			stem = books.get(i);
			tk = stem.getISBN();
			for (int j = 0; j < books.size(); j++) 
			{
				sten = books.get(j);
				tj = sten.getISBN();
				if(tk > tj)
				{
					temp = stem;
					stem = sten;
					sten = temp;
				}
			}
		}
		String str = "Books by ISBN:\n";
		BookClass tstr;
		for (int i = 0; i < books.size(); i++) 
		{
			tstr = books.get(i);
			str += tstr.getTitle();
			str += "\n";
		}
		
		return str;
	}	
	/**
	 * This puts the library information in the form of a string.
	 */
	public String toString()
	{
		String str = "";
		str += "Name: " + name;
		str += "\nNumber of books: " + books.size();
		str += "\nLibrarian:  " + librarian;
		str += "\nLocation:  " + location;
		
		return str;
	}
	
	public static void fileWriter(String data) throws IOException 
	{
		BufferedWriter output = new BufferedWriter(new FileWriter("Books.txt"));
		output.write(data); //writes out the data
		output.close(); //closes the output
	}
}


