package duke;

import duke.command.DeadlineCommand;
import duke.command.MarkCommand;

import java.util.Arrays;
import java.util.stream.Stream;

public class MessagePrinter {
    private int indentationLevel;
    private int horizontalLineLength;
    private char horizontalLineSymbol;

    private void setIndentationLevel(int indentationLevel) {
        this.indentationLevel = indentationLevel;
    }

    private void setHorizontalLineLength(int horizontalLineLength) {
        this.horizontalLineLength = horizontalLineLength;
    }

    private void setHorizontalLineSymbol(char horizontalLineSymbol) {
        this.horizontalLineSymbol = horizontalLineSymbol;
    }

    public int getHorizontalLineLength() {
        return horizontalLineLength;
    }

    public char getHorizontalLineSymbol() {
        return horizontalLineSymbol;
    }

    public int getIndentationLevel() {
        return indentationLevel;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MessagePrinter) {
            MessagePrinter mp = (MessagePrinter) obj;
            return this.horizontalLineLength == mp.horizontalLineLength
                    && this.horizontalLineSymbol == mp.getHorizontalLineSymbol()
                    && this.indentationLevel == mp.getIndentationLevel();
        }
        return false;
    }
}
