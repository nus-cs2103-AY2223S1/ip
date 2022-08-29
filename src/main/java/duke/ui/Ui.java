package duke.ui;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;

/**
 * User interface for Duke application.
 */
public class Ui {
    /**
     * Prints the given message.
     *
     * @param message the message to be displayed.
     */
    public void printMessage(String message) {
        String dash = "----------------------------------------";
        System.out.println(dash + "\n" + message + "\n" + dash);
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from\n" + logo + "\nWait, I'm not Duke\n"
                + "I'm Yem, your personal assistant\nWhat can I do for you master?";
        printMessage(welcomeMessage);
    }

    /**
     * Prints farewell message.
     */
    public void printFarewellMessage() {
        printMessage("Bye. See you later master!");
    }

    /**
     * Prints error message of a DukeException.
     *
     * @param error the DukeException which message is going to be shown.
     */
    public void printError(DukeException error) {
        printMessage(error.getMessage());
    }
}
