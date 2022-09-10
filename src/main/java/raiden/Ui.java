package raiden;

import java.util.Scanner;

import raiden.task.TaskList;

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
    public boolean showActiveStatus() {
        return this.isActive;
    }

    /**
     * Prints the error message when RaidenException occurs.
     *
     * @param e The RaidenException for this error.
     * @return The String message of the RaidenException.
     */
    public String showError(RaidenException e) {
        return e.toString() + "\n";
    }

    /**
     * Ends the current Raiden session.
     *
     * @param storage The storage associated with Raiden.
     * @param taskList The TaskList associated with Raiden.
     * @throws RaidenException if RaidenException occurs when saving to file.
     */
    public void endSession(Storage storage, TaskList taskList) throws RaidenException {
        this.isActive = false;
        this.sc.close();
        storage.writeToSave(taskList);
    }
}
