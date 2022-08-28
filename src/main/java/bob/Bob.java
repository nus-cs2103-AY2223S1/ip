package bob;

/**
 * Represents a chatbot, Bob, which records down and saves tasks inputted by user.
 */
public class Bob {

    Storage storage;
    Ui ui;
    TaskList taskList;
    Parser parser;

    /**
     * Initialises Bob with a Ui, Storage, TaskList and Parser.
     */
    public Bob() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = this.storage.read();
        this.parser = new Parser();
    }

    /**
     * Runs Bob, to start taking in inputs from user.
     */
    public void run() {
        ui.displayWelcomeMessage();
        while (parser.toExit == false) {
            parser.parse(ui.getReply(), taskList, storage, ui);
        }
    }

    public static void main(String[] args) {
        new Bob().run();
    }
}

