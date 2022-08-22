import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private boolean isActive;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.isActive = true;
    }

    /**
     * Retrieves the input command given by the user.
     *
     * @return The String representation of the command.
     */
    public String getUserCommand() {
        return this.sc.nextLine();
    }

    /**
     * Checks if the scanner is active.
     *
     * @return true if the scanner has next, false otherwise.
     */
    public boolean isActive() {
        return this.isActive && this.sc.hasNext();
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
     * Prints the greeting message.
     */
    public void showGreeting() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm Duke!\n" + logo);
        System.out.println("Duke: What can I do for you?");
    }

    /**
     * Prints the error message when DukeException occurs.
     *
     * @param e The DukeException for this error.
     */
    public void showError(DukeException e) {
        System.out.println(e);
    }

    /**
     * Ends the current Duke session.
     *
     * @param storage The storage associated with Duke.
     * @param tasksList The TasksList associated with Duke.
     * @throws DukeException if DukeException occurs when saving to file.
     */
    public void endSession(Storage storage, TasksList tasksList) throws DukeException {
        this.isActive = false;
        System.out.println("Duke: Bye! Hope to see you again soon!");
        sc.close();
        storage.writeToSave(tasksList);
    }
}
