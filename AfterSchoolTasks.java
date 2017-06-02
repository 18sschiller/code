//*******************************************************************
//  AfterSchoolTasks.java       Author: Lewis/Loftus/Cocking
//
//  Demonstrates the use of queues.
//*******************************************************************

import java.util.PriorityQueue;

public class AfterSchoolTasks
{
   //----------------------------------------------------------------
   //  Creates and manipulates a PriorityQueue.
   //----------------------------------------------------------------
   public static void main (String[] args)
   {    
      PriorityQueue<Task> tasks = new PriorityQueue<Task>();
      
      tasks.add (new Task("Clean room", 3));
      tasks.add (new Task("Do homework", 1));
      tasks.add (new Task("Mow lawn", 4));
      tasks.add (new Task("Write letter to Grandma", 2));

      System.out.println (tasks.remove() + ": check");
      System.out.println (tasks.remove() + ": check");
      System.out.println ("Next task: " + tasks.peek()); 
   }
}
