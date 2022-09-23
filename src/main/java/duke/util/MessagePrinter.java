package duke.util;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Represents the UI of the Duke.
 */
public class MessagePrinter {
    private int indentationLevel;
    private int horizontalLineLength;
    private char horizontalLineSymbol;

    /**
     * Constructs Message Printer.
     */
    public MessagePrinter() {
        int defaultHorizontalLineLength = 39;
        char defaultHorizontalLineSymbol = '*';
        int defaultIndentationLevel = 0;
        this.indentationLevel = defaultIndentationLevel;
        this.horizontalLineLength = defaultHorizontalLineLength;
        this.horizontalLineSymbol = defaultHorizontalLineSymbol;
    }

    /**
     * Returns the length of horizontal lines around the message.
     * @return The length of horizontal lines around the message.
     */
    public int getHorizontalLineLength() {
        return horizontalLineLength;
    }

    /**
     * Returns the symbol of the horizontal line.
     * @return The symbol of the horizontal line.
     */
    public char getHorizontalLineSymbol() {
        return horizontalLineSymbol;
    }

    /**
     * Returns the indentation level.
     * @return The indentation level.
     */
    public int getIndentationLevel() {
        return indentationLevel;
    }

    private String getIndentation() {
        return Stream.generate(() -> " ")
                .limit(this.indentationLevel)
                .reduce("", (x, y) -> x + y);
    }

    private String getBorderLine() {
        return getBorderLine(this.horizontalLineSymbol, horizontalLineLength);
    }

    private String getBorderLine(char symbol, int length) {
        String line = Stream.generate(() -> Character.toString(symbol))
                .limit(length)
                .reduce("", (x, y) -> x + y);
        return line;
    }

    /**
     * The method to print message.
     * @param input The user input.
     * @param output The duke's response.
     */
    public void printInTerminal(String input, String output) {
        char logBorderLineSymbol = '-';
        int logBorderLineLength = 50;
        String timestamp = new java.util.Date().toString();
        System.out.println("At " + timestamp + ":" + System.lineSeparator()
                + "User input: [" + input + "]" + System.lineSeparator()
                + "Response:" + System.lineSeparator()
                + output);
        System.out.println(getBorderLine(logBorderLineSymbol, logBorderLineLength));
    }

    /**
     * The method to accept given message and print with style.
     * @param msg The given message.
     * @return The message with style.
     */
    public String getPrintMessage(String msg) {
        StringBuilder result = new StringBuilder();
        result.append(getBorderLine()).append(System.lineSeparator());
        result.append(Arrays.stream(msg.split(System.lineSeparator()))
                        .reduce("", (x, y) -> x
                                + (x.equals("") ? "" : System.lineSeparator())
                                + getIndentation() + y))
                .append(System.lineSeparator());
        result.append(getBorderLine());
        return result.toString();
    }
}
