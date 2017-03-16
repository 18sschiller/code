import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class QuizTime
{
   public static void main (String[] args)
   {
	   	Quiz a;
		JFrame f = new JFrame();
		Object[] options = getQuizzes();
		int x = JOptionPane.showOptionDialog(f,"Which quiz " + "would you like to take?", "Quiz", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[2]);
		if(x == (options.length-1))
		{
		   Object[] joptions = {"OK", "CANCEL"};
		   int n = JOptionPane.showOptionDialog(f,"You have selected to make a quiz.\nThis means you must write out at least ten questions.\nWrite the question and then any answers you have, seperated by commas.", "Answer", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, joptions, joptions[0]);
		   if(n == 0)
		   {
			   String name = Quiz.makeQuizFromScratch();
			   try {
				ArrayList<String> sub = Quiz.readFile(new File("quizNames.txt"));
				sub.add(name);
				String str = "";
				for (int i = 0; i < sub.size(); i++)
					str += sub.get(i) + "\n";
				Quiz.fileWriter(str, "quizNames");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   System.exit(0);
		   }
		   else if(n == 1){
			   System.out.println("cancel");
			   System.exit(0);}
		   else{
			   System.out.println("exit");
			   System.exit(0);
		   }
		   a = new Quiz("quizDisney");
		}
		else if( x == -1)
		{
			System.exit(0);
			a = new Quiz("q");
		}
		else
		{
			String kt = "quiz" + options[x];
			a = new Quiz(kt);
		}
	   a.giveQuiz(true);
   }
   public static Object[] getQuizzes()
   {
		ArrayList<String> jr = null;
		try {
			jr = Quiz.readFile(new File("quizNames.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[] options = new Object[jr.size()+1];
		for (int i = 0; i < jr.size(); i++) 
			options[i] = jr.get(i);
		options[jr.size()] = "New Quiz";
		return options;
   }
}
