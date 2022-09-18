package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.loanbook.Loanbook;
import duke.loanbook.command.LoanbookCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.command.Command;
import duke.ui.Ui;

/**
 * Represents a robot that takes in tasks given by user through CLI, and other requests such as
 * listing all tasks, deleting tasks, adding tasks and marking tasks.
 *
 * @author Elgin
 */
public class Duke {
    private static TaskList tasks;

    private static Loanbook loanbook;

    private static Storage storage;

    private static Ui ui;

    /**
     * Constructor for Duke.
     *
     */
    public Duke() {
        Duke.storage = new Storage();
        Duke.ui = new Ui();

        // Load tasks from storage.
        try {
            Duke.tasks = new TaskList(Duke.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(Duke.ui.getTaskLoadingErrorMsg());
            Duke.tasks = new TaskList();
        }

        // Load loanbook from storage.
        try {
            Duke.loanbook = new Loanbook(Duke.storage.loadLoanbook());
        } catch (FileNotFoundException e) {
            System.out.println(Duke.ui.getLoanbookLoadingErrorMsg());
            Duke.loanbook = new Loanbook();
        }
    }

    /**
     * Executes the command given by the user and act accordingly.
     *
     * @param userInput The input that user provides.
     * @return The message Duke wants to say to the user.
     */
    public String handleUserInput(String userInput) {
        String dukeMessage;

        try {
            if (Parser.isTaskCommand(userInput)) {
                Command c = Parser.parse(userInput);
                dukeMessage = c.execute(Duke.tasks, Duke.ui, Duke.storage);
            } else {
                LoanbookCommand c = Parser.parseLoanbookCommand(userInput);
                dukeMessage = c.execute(Duke.loanbook, Duke.ui, Duke.storage);
            }

            // Save the updated states
            Duke.storage.save(Duke.tasks);
            Duke.storage.saveLoans(Duke.loanbook);
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
