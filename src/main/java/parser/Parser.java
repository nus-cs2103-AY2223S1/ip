package parser;
import printer.Printer;


public class Parser {
    private boolean isListening = true;

    public void parseText(String text) {
        if (text.equals("bye")) {
            Printer.print("Bye. See you later master!");
            this.isListening = false;
        } else {
            Printer.print(text);
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
