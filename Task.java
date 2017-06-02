//********************************************************************
//  Task.java       Author: Lewis/Loftus/Cocking
//
//  Represents a task.
//********************************************************************

public class Task implements Comparable
{
   private String name;
   private int priority;

   //-----------------------------------------------------------------
   //  Sets up the new task with a name and priority.
   //  Lower numbers indicate higher priority.
   //-----------------------------------------------------------------
   public Task (String taskName, int prio)
   {    
      name = taskName;
      priority = prio;
   }

   //-----------------------------------------------------------------
   //  Returns this task's priority.
   //-----------------------------------------------------------------
   public int getPriority ()
   {
      return priority;
   }

   //-----------------------------------------------------------------
   //  Returns this task as a string.
   //-----------------------------------------------------------------
   public String toString ()
   {
      return name;
   }

   //-----------------------------------------------------------------
   //  Compares tasks according to their priority.
   //-----------------------------------------------------------------
   public int compareTo (Object t)
   {
      if (priority > ((Task)t).getPriority())
         return -1;
      else if (priority == ((Task)t).getPriority())
         return 0;
      else
         return 1;
   }
}
