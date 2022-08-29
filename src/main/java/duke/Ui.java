package duke;

import java.util.Scanner;

import duke.task.TaskList;

/**
 * Represents the Ui that deals with interactions with the user.
 */
public class Ui {
    private final Scanner sc;
    private boolean isActive;

    /**
     * Creates Ui that handles the interactions with the user.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.isActive = true;
    }

    /**
     * Checks if the scanner is active.
     *
     * @return true if the scanner has next, false otherwise.
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Prints the message that is given.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns the String of the greeting message.
     *
     * @return The String message of the greeting.
     */
    public String showGreeting() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetMessage = "Hello, I'm Duke!\n" + logo + "\nDuke: What can I do for you?\n";
        return greetMessage;
    }

    /**
     * Prints the error message when DukeException occurs.
     *
     * @param e The DukeException for this error.
     * @return The String message of the DukeException.
     */
    public String showError(DukeException e) {
        return e.toString() + "\n";
    }

    /**
     * Ends the current Duke session.
     *
     * @param storage The storage associated with Duke.
     * @param taskList The TaskList associated with Duke.
     * @throws DukeException if DukeException occurs when saving to file.
     */
    public void endSession(Storage storage, TaskList taskList) throws DukeException {
        this.isActive = false;
        this.sc.close();
        storage.writeToSave(taskList);
    }
}
