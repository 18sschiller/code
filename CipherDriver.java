public class CipherDriver 
{
	public static void main(String[] args) 
	{
		Vigenere n = new Vigenere();
		System.out.println("Hello and welcome to the Vignere encryptor.");
		String s = "";
		while(!(s.equals("3")))
		{
			s = n.userTalkerIntro();
			if(s.equals("1"))
				s = n.userTalkerEncrypt();
			else if(s.equals("2"))
				s = n.userTalkerDecrypt();
			else if(s.equals("3"))
				System.out.println();
			else
				System.out.println("Not an option.");
		}
		System.out.println("Thank you and goodbye.");
	}
}
