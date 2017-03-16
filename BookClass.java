public class BookClass 
{
	private String title;
	private String author;
	private long ISBN;
	private int pages;
	private String subject;
	private int year;
	
	/**
	 * This is a constructor that creates a book. It uses what is put in and creates a new object.
	 * This consists of a title, an author, an ISBN number, page number, subject, and year.
	 * @param hey
	 * @param hi
	 * @param hello
	 * @param howdy
	 * @param hola
	 * @param whassup
	 */
	public BookClass(String hey, String hi, long hello, int howdy, String hola, int whassup)
	{
		setTitle(hey);
		setAuthor(hi);
		setISBN(hello);
		setPages(howdy);
		setSubject(hola);
		setYear(whassup);
	}

	/**
	 * These two functions allow the driver to get and set the title of the book.
	 * @return
	 */
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * These two functions allow the driver to get and set the author of the book.
	 * @return
	 */
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * These two functions allow the driver to get and set the ISBN number of the book.
	 * @return
	 */
	
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	/**
	 * These two functions allow the driver to get and set the number of pages of the book.
	 * @return
	 */
	
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * These two functions allow the driver to get and set the subject of the book.
	 * @return
	 */
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * These two functions allow the driver to get and set the year of the book.
	 * @return
	 */
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * This function combines all of the book information into a single string.
	 */
	public String toString()
	{
		String str = "";
		str += title + "\n";
		str += "By: " + author + "\n";
		str += "ISBN: " + ISBN + "\n";
		str += "Pages: " + pages + "\n";
		str += "Subject: " + subject + "\n";
		str += "Year: " + year + "\n";
		
		return str;
	}
}
