public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }

    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        boolean terminateFlag = false;
        this.ui.printWelcome();

        while(!terminateFlag) {
            try {
                Command currentCommand = parser.parse(ui.getCommand());
                currentCommand.execute(taskList, ui, storage);
                terminateFlag = currentCommand.isTerminator;
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }
}
