import java.util.Arrays;
import java.util.stream.Stream;

public class MessagePrinter {
    private int indentationLevel = 3;

    public void setIndentationLevel(int indentationLevel) {
        this.indentationLevel = indentationLevel;
    }

    public MessagePrinter() {
//        do nothing
        int defaultIndentationLevel = 3;
        setIndentationLevel(defaultIndentationLevel);
    }

    public MessagePrinter(int indentationLevel) {
        setIndentationLevel(indentationLevel);
    }

    private String getIndentation() {
        return Stream.generate(() -> " ").limit(this.indentationLevel).reduce("", (x, y) -> x + y);
    }

    private void printLine() {
        String line = "____________________________________________________________";
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
