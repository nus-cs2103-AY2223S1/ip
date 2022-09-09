package ip;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (DukeException de) {
            ui.printLoadingError(de.toString());
            this.taskList = new TaskList();
        }
        this.parser = new Parser(this.ui, this.taskList);
    }

    private void run() throws DukeException {
        try {
            this.ui.printWelcome();
            boolean canContinue = true;
            while (this.parser.hasNext() && canContinue) {
                canContinue = this.parser.handleNext();
            }
            this.storage.storeToFile(this.taskList);
            this.ui.printGoodbye();
        } catch (DukeException de) {
            this.ui.prettyPrint(de.toString());
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("Duke.txt").run();
    }
}
