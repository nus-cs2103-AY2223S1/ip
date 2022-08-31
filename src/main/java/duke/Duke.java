package duke;

import duke.commands.Command;
import duke.exceptions.DukeUnknownCommandException;

import java.io.IOException;

/**
 * Chatbot that read user input to modify a tasklist
 */
public class Duke {

    private Storage storage;

    /**
     * Construct a <code>Duke</code> instance that acts on file.
     *
     * @param filePath path of file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            storage.load();
            Ui.printSuccessfulLoad();
        } catch (IOException e) {
            Ui.printFailedLoad();
        }
    }

    /**
     * Run <code>Duke</code> to start taking input
     */
    public void run() {
        Ui.printWelcome();

        while (true) {
            try {
                Ui.printAskForCommand();
                String line = Ui.getCommand();
                Command c = Parser.parse(line);
                c.execute(this.storage);
            } catch (DukeUnknownCommandException e) {
                Ui.printError(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {

        Duke duke = new Duke("data/tasks.txt");

        duke.run();

    }


}

