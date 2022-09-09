package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.loanbook.Loanbook;
import duke.loanbook.command.LoanbookCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a robot that takes in tasks given by user through CLI, and other requests such as
 * listing all tasks, deleting tasks, adding tasks and marking tasks.
 *
 * @author Elgin
 */
public class Duke {
    /** All Tasks */
    private static TaskList tasks;

    /** Loan Book */
    private static Loanbook loanbook = new Loanbook();

    /** Storage for tasks. */
    private static Storage storage;

    /** Ui for Duke. */
    private static Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path to storage file from root folder (e.g. 'src/data/duke.txt').
     */
    public Duke(String filePath) {
        Duke.storage = new Storage(filePath);
        Duke.ui = new Ui();

        try {
            Duke.tasks = new TaskList(Duke.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(Duke.ui.getLoadingErrorMsg());
            Duke.tasks = new TaskList();
        }
    }

    /**
     * Executes the command given by the user and act accordingly.
     *
     * @param userInput The input that user provides.
     * @return The message Duke wants to say to the user.
     */
    public String handleUserInput(String userInput) {
        String dukeMessage = "";

        try {
            if (Parser.isTaskCommand(userInput)) {
                Command c = Parser.parse(userInput);
                dukeMessage = c.execute(Duke.tasks, Duke.ui, Duke.storage);
            } else {
                LoanbookCommand c = Parser.parseLoanbookCommand(userInput);
                dukeMessage = c.execute(Duke.loanbook, Duke.ui, Duke.storage);
            }
        } catch (DukeException e) {
            dukeMessage = Duke.ui.formatDukeErrorMsg(e.getMessage());
        } catch (NumberFormatException e) {
            dukeMessage = Duke.ui.getNumberCastErrorMsg();
        } catch (DateTimeParseException e) {
            dukeMessage = Duke.ui.getDateErrorMsg();
        }

        assert !dukeMessage.isEmpty() : "Message is not supposed to be empty";

        return dukeMessage;
    }
}
