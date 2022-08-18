package duke;

import duke.command.Command;
import duke.parser.Parser;

import java.io.IOException;

public class Duke {
    private static String GREETING = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static String EXIT_MSG = "Bye. Hope to see you again soon!";
    private static String NO_TASK_NAME = "No task name defined, please try again";
    private static String NO_INDEX_SPECIFIED = "No index specified, try again";
    private final Ui ui;
    private final Storage storage;

    public Duke() throws Storage.InvalidStorageFilePathException, IOException {
        this.ui = new Ui(System.in, System.out);
        this.storage = new Storage();
    }

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

    public static void main(String[] args) throws Storage.InvalidStorageFilePathException, IOException {
        Duke duke = new Duke();
        duke.run();
    }
}