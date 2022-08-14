import java.util.Arrays;
import java.util.stream.Stream;

public class MessagePrinter {
    private int indentationLevel;
    private int horizontalLineLength;
    private char horizontalLineSymbol;

    public void setIndentationLevel(int indentationLevel) {
        this.indentationLevel = indentationLevel;
    }

    public void setHorizontalLineLength(int horizontalLineLength) {
        this.horizontalLineLength = horizontalLineLength;
    }

    public void setHorizontalLineSymbol(char horizontalLineSymbol) {
        this.horizontalLineSymbol = horizontalLineSymbol;
    }

    public MessagePrinter() {
        int defaultIndentationLevel = 3;
        int defaultHorizontalLineLength = 50;
        char defaultHorizontalLineSymbol = '-';
        setIndentationLevel(defaultIndentationLevel);
        setHorizontalLineLength(defaultHorizontalLineLength);
        setHorizontalLineSymbol(defaultHorizontalLineSymbol);
    }

    public MessagePrinter(int indentationLevel, int horizontalLineLength, char horizontalLineSymbol) {
        setIndentationLevel(indentationLevel);
        setHorizontalLineLength(horizontalLineLength);
        setHorizontalLineSymbol(horizontalLineSymbol);
    }

    private String getIndentation() {
        return Stream.generate(() -> " ").limit(this.indentationLevel).reduce("", (x, y) -> x + y);
    }

    private void printLine() {
        String line = Stream.generate(() -> Character.toString(this.horizontalLineSymbol))
                .limit(this.horizontalLineLength)
                .reduce("", (x, y) -> x + y);
        print(line);
    }

    private void print(String msg) {
        System.out.println(getIndentation() + msg);
    }

    public void printMessage(String msg) {
        printLine();
        Arrays.stream(msg.split("\\n")).forEach(x -> print(" " + x));
        printLine();
    }
}
