package parser;
import printer.Printer;
import storage.Storage;


public class Parser {
    private boolean isListening = true;
    private Storage storage;

    public Parser(Storage storage) {
        this.storage = storage;
    }

    public void parseText(String text) {
        if (text.equals("bye")) {
            Printer.print("Bye. See you later master!");
            this.isListening = false;
        } else if (text.equals("list")){
            Printer.print(this.storage.toString());
        } else {
            this.storage.addTask(text);
            Printer.print(String.format("New task, %s, is successfully added", text));
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
