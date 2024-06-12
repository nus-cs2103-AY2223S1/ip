import duke.DukeException;
import duke.command.Command;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final Scanner scan = new Scanner(System.in);
    private static final String FILE_PATH = "data/tasks.txt";
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Default constructor to fix javafx issue.
     */
    public Duke() {
    }

    /**
     * Constructor to initialise Duke using the save file.
     *
     * @param filePath Path where the save file is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadInTasks());
        } catch (IOException ioe) {
            System.exit(0);
        } catch (DukeException de) {
            tasks = new TaskList();
        }
    }

    /**
     * Main method to initialise Duke.
     *
     * @param args The user input.
     * @throws DukeException If invalid command encountered.
     */
    public static void main(String[] args) throws DukeException {
        new Duke(FILE_PATH).run();
    }

    /**
     * Initialise the command line interface and run Duke.
     */
    public void run() {
        ui.printIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inData;
                inData = scan.nextLine();
                Command command = Parser.parseCommand(inData);
                isExit = command.isExitCommand();
                command.execute(tasks, ui, storage);
            } catch (DukeException de) {
                ui.printDukeException(de);
            } catch (IOException ie) {
                ui.printIoException(ie);
            }
        }
    }

    /**
     * Function to generate a response to user input.
     *
     * @param input Text that user entered.
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command command = Parser.parseCommand(input);
            isExit = command.isExitCommand();
            if (!isExit) {
                return command.execute(tasks, ui, storage);
            } else {
                return ui.printExit();
            }
        } catch (DukeException de) {
            return ui.printDukeException(de);
        } catch (IOException ie) {
            return ui.printIoException(ie);
        }
    }
}
