public class Entry 
{
	private String word;
	private String definition;
	private int beginDef;
	private int wordCoStartY;
	private int wordCoStartX;
	private int partOfSpeech;
	public Entry( String wor, String def, int wordCoStartYy, int wordCoStartXx)
	{
		setWord(wor);
		setDefinition(def);
		setWordCoStartY(wordCoStartYy);
		setWordCoStartX(wordCoStartXx);
	}
	public Entry(String wor, String def)
	{
		setWord(wor);
		setDefinition(def);
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String toString()
	{
		return "\nWord:\t" + this.word + "\nDefinition:\t" + this.definition + "\nStarting Coordinates:\t" + this.wordCoStartY + "\n";
	}
	public int getBeginDef() {
		return beginDef;
	}
	public void setBeginDef(int beginDef) {
		this.beginDef = beginDef;
	}
	public int getWordCoStartY() {
		return wordCoStartY;
	}
	public void setWordCoStartY(int wordCoStartY) {
		this.wordCoStartY = wordCoStartY;
	}
	public int getWordCoStartX() {
		return wordCoStartX;
	}
	public void setWordCoStartX(int wordCoStartX) {
		this.wordCoStartX = wordCoStartX;
	}
	public int getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(int partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
}
