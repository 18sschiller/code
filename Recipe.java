import java.util.ArrayList;

import javax.swing.DefaultListModel;

/**
 * String name, String[] ingredients, String author, String password, String[] steps, int preptime, ArrayList<Integer> categories.
 * @param hey
 * @param hi
 * @param hello
 * @param howdy
 * @param hola
 * @param whassup
 * @param shalom
 */

public class Recipe 
{
	private String name;
	private String[] ingredients;
	private String author;
	private String password;
	private String[] steps;
	private int preptime;
	private ArrayList<Integer> categories;
	public ArrayList<String> mmm;
	public DefaultListModel<String> nnn;
	/**
	 * String name, String[] ingredients, String author, String password, String[] steps, int preptime, ArrayList<Integer> categories.
	 * @param hey
	 * @param hi
	 * @param hello
	 * @param howdy
	 * @param hola
	 * @param whassup
	 * @param shalom
	 */
	public Recipe(String hey, String[] hi, String hello, String howdy, String[] hola, int whassup, ArrayList<Integer> shalom)
	{
		setName(hey);
		setIngredients(hi);
		setAuthor(hello);
		setPassword(howdy);
		setSteps(hola);
		setPreptime(whassup);
		setCategories(shalom);
	}
	public String tooString()
	{
		String x = "       ";
		int i = 0;
		x += name; x += "\n"; x += " By:    "; x += author; x += "\n";
		x += " PrepTime:    "; x += preptime; x += " minutes"; x += "\n\n";
		x += " Ingredients:\n ";
		
		for (i = 0; i < ingredients.length-1; i++) 
		{
			if( i != 0)
				if( i % 2 == 0)
					x += "\n ";
			x += ingredients[i]; x += ",  ";
		}
		x += ingredients[i]; x += "\n\n";
		x += " Steps:\n ";
		for ( i = 0; i < steps.length; i++)
		{
			x += steps[i]; 
			x += "\n ";
		}
		x += " ---------------------------\n\n";
		return x;
	}
	public String toooString()
	{
		String str = "";
		str += name; str += "\n"; str += author; str += "\n";
		str += preptime; str += "\n";
		for (int j = 0; j < ingredients.length; j++) 
		{
			str += ingredients[j];
			str += "   ";
		}
		str += "\n";
		for (int j = 0; j < steps.length; j++) 
		{
			str += steps[j];
			str += "   ";
		}
		str += "\n"; str += password; str += "\n";
		for (int j = 0; j < categories.size(); j++) 
		{
			str += categories.get(j);
			str += "   ";
		}
		str += "\n";
		return str;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getIngredients() {
		return ingredients;
	}
	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getSteps() {
		return steps;
	}
	public void setSteps(String[] steps) {
		this.steps = steps;
	}
	public int getPreptime() {
		return preptime;
	}
	public void setPreptime(int preptime) {
		this.preptime = preptime;
	}
	public ArrayList<Integer> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Integer> categories) {
		this.categories = categories;
	}
}
