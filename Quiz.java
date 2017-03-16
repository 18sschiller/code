import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Quiz 
{
	public ArrayList<Question> quizQ;
	public String fileName;  

   private JFrame frame, contactt, helpp, answersheet;
   private JPanel panel, contactpanel, helppanel, answersheetpanel;
   private JLabel inputLabel, asl;
   private JTextField fahrenheit;
   private JButton tjg, helpButton;
   private int countingThing;
   
   private String[] answ;
   private boolean[] resultss;

   private int timeThrough;
   private int thing = 0;	
   
	public Quiz(String f)
	{	
		timeThrough = -1;
		makeFrames();
		quizQ = new ArrayList<Question>();
		fileName = f;
		ArrayList<String> str1 = new ArrayList<String>();
    	Question temp;
    	String tempQuest, tempAns, tempCompl;
    	
    	try {
    		str1 = readFile( new File(fileName + ".txt"));
		}catch (FileNotFoundException e1) {e1.printStackTrace();}

    	for (int i = 0; i < (str1.size()/3); i++) 
    	{
			tempQuest = str1.get((i*3));
			tempAns = str1.get((i*3)+1);
			String[] k = tempAns.split(",");
			tempCompl = str1.get((i*3)+2);
			temp = new Question(tempQuest, k);
			temp.setComplexity(Integer.parseInt(tempCompl));
			quizQ.add(temp);
		}
    	
	}
	private void makeFrames()
	{
	 	answersheet = new JFrame("Answer Sheet");
    	answersheet.pack();
    	answersheet.setSize(768, 400);
    	answersheet.setLocation(299, 154);
    	answersheet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	answersheet.setVisible(false);
	    answersheetpanel = new JPanel();
	   answersheetpanel.setLocation(50, 50);
	   answersheetpanel.setSize(50, 50);
	   answersheetpanel.setBackground(Color.lightGray);
	   asl = new JLabel(" ");
	   asl.setVisible(true);
	   answersheetpanel.add(asl);
       JScrollPane scrollPane = new JScrollPane(answersheetpanel);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setBounds(0, 0, 750, 360);
       scrollPane.setVisible(true);
	   answersheet.getContentPane().add(scrollPane);
	   answersheetpanel.setVisible(true);		
	   answersheet.addWindowListener(new WindowAdapter()
       {
           @Override
           public void windowClosing(WindowEvent e)
           {
        	   helpp.setVisible(false);
           }
       });
	   helpp = new JFrame("Help");
       helpp.pack();
       helpp.setSize(220, 150);
       helpp.setLocation(1043, 270);
       helpp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       helpp.setVisible(false);
	   helppanel = new JPanel();
	   helppanel.setLocation(50, 50);
	   helppanel.setSize(50, 50);
	   helppanel.setBackground(Color.lightGray);
	   JLabel tl = new JLabel("<html><center>Question<br>Difficulty</center></html>");
	   tl.setVisible(true);
	   JButton b = new JButton("+");
	   b.setVisible(true);
	   b.addActionListener( new ActionListener()
	   {
	       public void actionPerformed(ActionEvent e)
	       {
	           quizQ.get(timeThrough).setComplexity(quizQ.get(timeThrough).getComplexity()+1);
			   frame.setTitle(fileName.substring(4, fileName.length()) + " Quiz -- Question " + (timeThrough+1) + "/" + quizQ.size() + "  -- (Level:" + quizQ.get(timeThrough).getComplexity() + ")"); 
	       }
	   });
	   JButton c = new JButton("-");
	   c.setVisible(true);
	   c.addActionListener( new ActionListener()
	   {
	       public void actionPerformed(ActionEvent e)
	       {
	           quizQ.get(timeThrough).setComplexity(quizQ.get(timeThrough).getComplexity()-1);
			   frame.setTitle(fileName.substring(4, fileName.length()) + " Quiz -- Question " + (timeThrough+1) + "/" + quizQ.size() + "  -- (Level:" + quizQ.get(timeThrough).getComplexity() + ")"); 
	       }
	   });
	   JButton d = new JButton("Add Question To Quiz");
	   d.setVisible(true);
	   JButton e = new JButton("Get Answer");
	   e.setVisible(true);
	   e.addActionListener( new ActionListener()
	   {
	       public void actionPerformed(ActionEvent e)
	       {
	    	   Object[] options = {"OK"};
				int n = JOptionPane.showOptionDialog(frame,quizQ.get(timeThrough).getAnswer()[0], "Answer", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	       }
	   });
	   helppanel.add(tl);
	   helppanel.add(b);
	   helppanel.add(c);
	   helppanel.add(d);
	   d.addActionListener( new ActionListener()
	   {
		   public void actionPerformed(ActionEvent e)
		   {
			   JTextField firstName = new JTextField();
			   JTextField lastName = new JTextField();
			   JTextField password = new JTextField();
			   final JComponent[] inputs = new JComponent[] {
			           new JLabel("Question"),
			           firstName,
			           new JLabel("Answer"),
			           lastName,
			           new JLabel("Difficulty"),
			           password
			   };
			   int result = JOptionPane.showConfirmDialog(null, inputs, "New Question", JOptionPane.PLAIN_MESSAGE);
			   if (result == JOptionPane.OK_OPTION) {
			       String kkt = lastName.getText();
			       String[] po = kkt.split(",");
			       Question t = new Question(firstName.getText(),po);
			       t.setComplexity(Integer.parseInt(password.getText()));
			       quizQ.add(t);
			       String[] lop = new String[quizQ.size()];
			       for (int i = 0; i < answ.length; i++)
			    	   lop[i] = answ[i];
			       answ = lop;
			   }
		   }
	   });
	   helppanel.add(e);
	   helpp.getContentPane().add(helppanel);
	   helppanel.setVisible(true);
	   
	   frame = new JFrame ("Quiz");
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      frame.setLocation(383, 269);
	      frame.setResizable(false);
	      frame.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	        		String str = "";
	        		String[] jt;
	        		for (int i = 0; i < quizQ.size(); i++) 
	        		{
	        			str += quizQ.get(i).getQuestion();
	        			str += "\n";
	        			jt = quizQ.get(i).getAnswer();
	        			for (int j = 0; j < jt.length; j++) 
	        				str += jt[j] + ",";
	        			str += "\n";
	        			str += quizQ.get(i).getComplexity();
	        			if(i != quizQ.size()-1)
	        				str += "\n";
	        		}
	                try {
						fileWriter(str, fileName);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                e.getWindow().dispose();
	            }
	        });

	      inputLabel = new JLabel ("Start?");
	      inputLabel.setPreferredSize(new Dimension(10,50));
	      inputLabel.setHorizontalAlignment(JLabel.CENTER);

	      fahrenheit = new JTextField (5);
	      fahrenheit.setVisible(false);
	      fahrenheit.addActionListener(new TempListener());

	      tjg = new JButton("OK");
	      tjg.setPreferredSize(new Dimension(60, 40));
	      tjg.addActionListener(new TempListener());
	      
	      helpButton = new JButton("Help");
	      helpButton.setVisible(false);
	      helpButton.addActionListener(new HelpListener());
	      
	      panel = new JPanel();
	      panel.setLayout(new BorderLayout());
	      panel.setPreferredSize (new Dimension(600, 120));
	      panel.setBackground (Color.lightGray);
	      
	      panel.add (inputLabel, BorderLayout.NORTH);
	      panel.add(helpButton, BorderLayout.EAST);
	      panel.add(fahrenheit, BorderLayout.CENTER);
	      panel.add(tjg, BorderLayout.PAGE_END);
	      
	      frame.getContentPane().add(panel);
	      
		 	contactt = new JFrame(" ");
 		   contactt.setUndecorated(true);
 	    	contactt.pack();
 	    	contactt.setSize(120, 58);
 	    	contactt.setLocation(543, 455);
 	    	contactt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 	    	contactt.setVisible(false);
 		    contactpanel = new JPanel();
 		   contactpanel.setLocation(50, 50);
 		   contactpanel.setSize(50, 50);
 		   contactpanel.setBackground(Color.lightGray);
 		   JLabel ml = new JLabel("<html><center>13 + 15 = 31<br>10 x 10 = 100<br>6 x 3 = 24</center></html>");
 		   ml.setVisible(true);
 		   contactpanel.add(ml);
 		   contactt.getContentPane().add(contactpanel);
 		   contactpanel.setVisible(true);
	}
	public boolean[] giveQuiz()
	{
		Scanner scan = new Scanner(System.in);	  
		boolean[] xv = new boolean[quizQ.size()];
		String llk;
		
		for (int i = 0; i < quizQ.size(); i++) 
		{
			llk = scan.nextLine();
			if(quizQ.get(i).answerCorrect(llk))
				xv[i] = true;
		}		
		return xv;
	}
	public boolean[] giveQuiz(String[] answers)
	{
		boolean[] xv = new boolean[quizQ.size()];
		for (int i = 0; i < quizQ.size(); i++) 
			if(quizQ.get(i).answerCorrect(answers[i]))
				xv[i] = true;
		return xv;
	}
	public void giveQuiz(boolean x)
	{
		display();
	}
	
	public void addQuestion(String qu, String[] answer, int complexity)
	{
		Question q = new Question(qu, answer);
		q.setComplexity(complexity);
		quizQ.add(q);
	}
	
	public static String toString(ArrayList<Question> quizQ)
	{
		String str = "";
		String[] jt;
		for (int i = 0; i < quizQ.size(); i++) 
		{
			str += quizQ.get(i).getQuestion();
			str += "\n";
			jt = quizQ.get(i).getAnswer();
			for (int j = 0; j < jt.length; j++) 
				str += jt[j] + ",";
			str += "\n";
			str += quizQ.get(i).getComplexity();
			if(i != quizQ.size()-1)
				str += "\n";
		}
		return str;
	}
   public static void fileWriter(String data, String fileName) throws IOException 
   {
		BufferedWriter output = new BufferedWriter(new FileWriter(fileName + ".txt"));
		output.write(data); //writes out the data
		output.close(); //closes the output
   }
	public static ArrayList<String> readFile(File file) throws FileNotFoundException 
	{
		Scanner input = new Scanner(file); //makes a new file scanner
		ArrayList<String> data = new ArrayList<String>(); //temporary string
		while (input.hasNextLine()) //checker to make sure it doesn't screw up
			data.add(input.nextLine()); //adds to the data
		input.close(); //closes the scanner stream
		return data; //returns all the data
	}
	
   public void display()
   {
	  answ = new String[quizQ.size()];
	  countingThing = 0;
     
      frame.pack();
      frame.setVisible(true);
   }
   private class HelpListener implements ActionListener
   {
      public void actionPerformed (ActionEvent event)
      {
    	  helpp.setVisible(true);
      }
   }
   private class TempListener implements ActionListener
   {
      public void actionPerformed (ActionEvent event)
      {
         timeThrough++;
         if(countingThing > 0){
        	 contactt.setVisible(false);
        	 contactt.repaint();
        	 contactpanel.setVisible(false);}
         if(timeThrough == 0)
         {
        	 fahrenheit.setVisible(true);
        	 helpButton.setVisible(true);
    	     tjg.setText("Submit");
         }
         if(timeThrough == quizQ.size())
         {
    		 answ[timeThrough-1] = fahrenheit.getText();
        	 frame.dispose();
        	 resultss = giveQuiz(answ);
    		String str = "";
    		String[] jt;
    		for (int i = 0; i < quizQ.size(); i++) 
    		{
    			str += quizQ.get(i).getQuestion();
    			str += "\n";
    			jt = quizQ.get(i).getAnswer();
    			for (int j = 0; j < jt.length; j++) 
    				str += jt[j] + ",";
    			str += "\n";
    			str += quizQ.get(i).getComplexity();
    			if(i != quizQ.size()-1)
    				str += "\n";
    		}
            try {
				fileWriter(str, fileName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            asl.setText(findResulters());
         }
         else
         {
        	 if(timeThrough>=1){
        		 answ[timeThrough-1] = fahrenheit.getText();
         	}	
	         fahrenheit.setText("");
			 inputLabel.setText("<html><center>" + quizQ.get(timeThrough).getQuestion());
			 frame.setTitle(fileName.substring(4, fileName.length()) + " Quiz -- Question " + (timeThrough+1) + "/" + quizQ.size() + "  -- (Level:" + quizQ.get(timeThrough).getComplexity() + ")");
			 if(quizQ.get(timeThrough).getQuestion().substring(0, 2).equals("##"))
			 {
				 countingThing++;
				 inputLabel.setText("<html><center>" + quizQ.get(timeThrough).getQuestion().substring(2, quizQ.get(timeThrough).getQuestion().length()));
				 contactt.setVisible(true);
			 }
         }
      }
      private String findResulters()
      {
    	  int point = 0;
    	  answersheet.setVisible(true);
    	  String str = "<html><center>Let's see how you did...";
    	  for (int i = 0; i < quizQ.size(); i++) 
    	  {
			str += "<br><br>Question #" + (i+1) + ":    " + quizQ.get(i).getQuestion();
			if(resultss[i] == true){
				str += "<br><font color='green'>Correct! Your answer was:     " + answ[i] + "</font>";
				point++;
			}
			else{
				str += "<br><font color='red'>Wrong! " + quizQ.get(i).getAnswer()[0] + "</font>";
			}
    	  }
    	  double score = ((double)(point) / (double)(quizQ.size())) * 100;
    	  String k = score + "";
    	  if(score < 1)
    		  k = "0.000";
    	  if(k.length() > 6)
    		  k = k.substring(0, 5);
    	  str += "<br><br><br>Overall, you scored a " + point + "/" + quizQ.size() + ", or a " + k + "% on this test.<br>";
    	  str += "</center></html>";
    	  
		   JTextField firstName = new JTextField();
		   final JComponent[] inputs = new JComponent[] {
		           new JLabel("Your Full Name:"),
		           firstName
		   };
		   JOptionPane.showConfirmDialog(null, inputs, "For the record books...", JOptionPane.PLAIN_MESSAGE); 	  
    	  ArrayList<String> jjstr = new ArrayList<String>();
	      	try {
	    		jjstr = readFile( new File("log.txt"));
			}catch (FileNotFoundException e1) {e1.printStackTrace();}
	  	  String allData = "";
	  	  for (int i = 0; i < jjstr.size(); i++) 
	  	  {
			allData += jjstr.get(i) + "\n";
	  	  }
	  	  allData += "Entry #" + (jjstr.size()+1) + ":\t";
	  	  allData += printDate();
	  	  allData += "\t\t" + firstName.getText() + "\t\t" + fileName.substring(4, fileName.length()) + "\t\tScore:   " + point + "/" + quizQ.size() + "\tPercent:   " + k + "%";
    	  try {
			fileWriter(allData, "log");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	  return str;
      }
	    private String printDate()
	  	{
	  		Date date = new Date();		
	          SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy '--' hh:mm");
	      	return ft.format(date);
	  	}
   }
   public static String makeQuizFromScratch()
   {
	   JFrame jf = new JFrame("New Quiz");
	   String name = JOptionPane.showInputDialog(jf, "What is the subject of this quiz?");
	   String nombre = "quiz" + name;
	   ArrayList<Question> jhold = new ArrayList<Question>();
	   int h = 0;
	   String l = "";
	   JTextField firstName, lastName, password;
	   firstName = new JTextField();
	   lastName = new JTextField();
	   password = new JTextField();
	   final JComponent[] inputs = new JComponent[] {
	           new JLabel("Question"),
	           firstName,
	           new JLabel("Answer"),
	           lastName,
	           new JLabel("Difficulty"),
	           password
	   };
	   while(h < 10)
	   {
		   h++;
		   l = "Question #" + (h);
		   int result = JOptionPane.showConfirmDialog(null, inputs, l, JOptionPane.PLAIN_MESSAGE);
		   if (result == JOptionPane.OK_OPTION) {
		       String kkt = lastName.getText();
		       String[] po = kkt.split(",");
		       Question t = new Question(firstName.getText(),po);
		       t.setComplexity(Integer.parseInt(password.getText()));
		       jhold.add(t);
		   }
	   }
	   String kks = toString(jhold);
	   try {
		fileWriter(kks,nombre);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   JOptionPane.showConfirmDialog(null,"Restart this program to take your quiz or add more questions.", "Thank you.", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE, null);
	   return name;
   }
}
