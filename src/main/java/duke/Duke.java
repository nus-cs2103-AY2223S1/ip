package duke;

import duke.command.Command;
import duke.parser.Parser;

public class Duke {
    public static final String DEFAULT_FILE_NAME = "todolist.txt";
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs an instance of Duke, creates a Ui and Storage instance.
     * @return Duke object instance.
     * @see Ui
     * @see Storage
     */
    public Duke() {
        this.ui = new Ui(System.in, System.out);
        this.storage = Storage.of(DEFAULT_FILE_NAME);
    }

    /**
     * Starts Duke
     */
    public void run() {
        ui.startMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.getUserCommand();
                Command c = Parser.parse(command);
                c.execute(this.storage.loadSavedData(), this.ui, this.storage);
                isExit = c.isExit(c);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Start point for the Duke class
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
