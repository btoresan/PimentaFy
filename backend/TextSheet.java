package backend;
public class TextSheet {
    private String text;
    private int index;

    public TextSheet(String inputText, int index) {
        setText(inputText);
        setIndex(index);
    }

    public void setText(String inputText) {text = inputText;}
    public String getText() {return text;}
    public void setIndex(int index) {this.index = index;}
    public int getIndex() {return index;}


}
