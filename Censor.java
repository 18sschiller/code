import java.util.StringTokenizer;
/**
 * This class is made to take in a string and censor it. Censoring it means it checks if it has the specified words, 
 * and if it does, then it rejects the whole string. 
 *  * @author 18sschiller
 *
 */
public class Censor 
{
	/**
	 * This function will take in a string, and if it has no taboo words, it will print it out again, saying it is okay.
	 * If it contains taboo words, it will return the sentence, indicating that it is rejected.
	 * @param str
	 */
	public void censorThis(String str)
	{
		int x = 0; //counter for banned words
		StringTokenizer stok = new StringTokenizer(str," !/?.,:cjkpqwxz1234567890-=:;'<>"); //creates tokenizer; contains all letters not used in banned words
		int kl = stok.countTokens(); //sets kl equal to the number of tokens there are
		for (int i = 0; i < kl; i++)  //runs to how many tokens there are
		{
			String j = stok.nextToken(); //sets string equal to the next one
			if(j.equalsIgnoreCase("Hermes") || j.equalsIgnoreCase("bridge")|| j.equalsIgnoreCase("muddy")|| j.equalsIgnoreCase("river")|| j.equalsIgnoreCase("assault")|| j.equalsIgnoreCase("offensive"))
				x++; //if it equals one of the words that are banned, it will add to the counter
		}
		if(x == 0) //if there are none in the counter, the sentence is fine
			System.out.println(str + ">>> OK");
		else //if there are any in the counter, words are banned and the sentence is rejected
			System.out.println(str + ">>> REJECTED");	
	}
}
