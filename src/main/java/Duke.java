import java.io.FileNotFoundException;

public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.taskList = Storage.readFromStorage();
        } catch (FileNotFoundException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Main application loop for Duke.
     */
    public void run() {
        boolean isExit = false;

        ui.sayGreetings();
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Command c = parser.parseCommand(fullCommand);
                c.execute(this.taskList, this.ui, null);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.prettyPrint(e.getMessage());
            }
        }
        ui.sayGoodBye();
        ui.close();
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.run();
    }
}
