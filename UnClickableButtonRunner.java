import java.util.ArrayList;

public class UnClickableButtonRunner 
{
	public static ArrayList<Integer> used;

	public static void main(String[] args)
	{
		used = new ArrayList<Integer>();
        @SuppressWarnings("unused")
		UnClickableButton u = new UnClickableButton();
	}
}

