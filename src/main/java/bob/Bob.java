package bob;

public class Bob {

    Storage storage;
    Ui ui;
    TaskList taskList;
    Parser parser;

    public Bob() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = this.storage.read();
        this.parser = new Parser();
    }
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

