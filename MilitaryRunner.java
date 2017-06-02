import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MilitaryRunner 
{
	public static void main(String[] args)
	{
		doNothing();
		Scanner sc = new Scanner( System.in);
		String str = "";
		Censor css = new Censor();
		while(!(str.equals("stop")))
		{
			str = sc.nextLine();
			css.censorThis(str);	
		}
		sc.close();
	}
	public static void doNothing()
	{
		boolean a, b, c, d, e;
		
		Object s = new Object();
		
		Integer o = (Integer)5;
		
		Character h = 'c';
		int i = h.toString().length();
		ArrayList<String> arstr = new ArrayList<String>();
		arstr.add("dog");
		arstr.add("cat");
		arstr.add("cow");
		arstr.add("horse");
		arstr.add("pig");
		
		System.out.println(arstr.toString());
		
		arstr.add(2, "bird");
		
		System.out.println(arstr.toString());
	}
}
