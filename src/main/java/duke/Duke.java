package duke;

public class Duke {
    private TaskList taskList;
    private Parser parser;

    public Duke() {
        this.taskList = new TaskList(Storage.load());
        this.parser = new Parser(this.taskList);
    }

    public void run() throws DukeException {
        Ui.showWelcome();

        while (parser.isScanning()) {
            parser.parse();
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
