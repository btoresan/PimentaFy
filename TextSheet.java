public class TextSheet {
    public String text;
    public int index;

    public TextSheet(String inputText, int index) {
        setText(inputText);
        this.index = index;
    }

    public void setText(String inputText) {text = inputText;}

}
