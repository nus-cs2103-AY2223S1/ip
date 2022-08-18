package duke;

import duke.command.Command;
import duke.parser.Parser;

import java.io.IOException;

public class Duke {
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