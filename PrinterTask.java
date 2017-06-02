/**
 * This class makes a PrinterTask object that has all the information
 * of each printer task.
 * @author 18sschiller
 *
 */
public class PrinterTask implements Comparable<PrinterTask>
{
	private String name;
	private int priority;
	private int pageNum;
	
	/**
	 * Constructor
	 * @param taskName
	 * @param prio
	 * @param pageNm
	 */
	public PrinterTask(String taskName, int prio, int pageNm)
	{
	    name = taskName;
	    priority = prio;
		pageNum = pageNm;
	}
	
	/**
	 * toString method
	 */
	public String toString()
	{
		return " " + name + "\t\t" + pageNum;
	}
	
	/**
	 * Getters and setters.
	 * @return
	 */
    public int getPriority (){return this.priority;}
    public void setPriority ( int x){this.priority = x;}
	public int getPages(){return this.pageNum;}
	public void setPages( int x){this.pageNum = x;}

	/**
	 * Comparable method to compare printer tasks based on priority.
	 */
    public int compareTo (PrinterTask t)
    {
       if (priority > t.getPriority())
          return -1;
       else if (priority == t.getPriority())
          return 0;
       else
          return 1;
    }
}
