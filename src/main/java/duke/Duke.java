package duke;

import java.io.IOException;

import duke.command.Command;
import duke.parser.Parser;

public class Duke {
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs an instance of Duke, creates a Ui and Storage instance.
     * @return Duke object instance.
     * @throws Storage.InvalidStorageFilePathException
     * @throws IOException
     * @see Ui
     * @see Storage
     */
    public Duke() throws Storage.InvalidStorageFilePathException, IOException {
        this.ui = new Ui(System.in, System.out);
        this.storage = new Storage();
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
     * @throws Storage.InvalidStorageFilePathException
     * @throws IOException
     */
    public static void main(String[] args) throws Storage.InvalidStorageFilePathException, IOException {
        Duke duke = new Duke();
        duke.run();
    }
}
