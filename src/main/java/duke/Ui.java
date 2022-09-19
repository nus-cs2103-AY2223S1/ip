package duke;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Ui handles interactions with the user.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Ui {
    /**
     * Returns the farewell message and terminates the program.
     *
     * @return The farewell message.
     */
    public String showBye() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1500);

        return "Bye. See you again soon.";
    }

    /**
     * Returns the message indicating the given Task has been marked as completed.
     *
     * @param task String representation of the Task.
     * @return The message indicating the given Task has been marked as completed.
     */
    public String showMark(String task) {
        return String.format("Noted. I've marked this task as done:%n%s%n", task);
    }

    /**
     * Returns the message indicating the given Task has been marked as uncompleted.
     *
     * @param task String representation of the Task.
     * @return The message indicating the given Task has been marked as uncompleted.
     */
    public String showUnmark(String task) {
        return String.format("Noted. I've marked this task as not done yet:%n%s%n", task);
    }

    /**
     * Returns the message indicating the given Task has been added.
     *
     * @param task String representation of the Task.
     * @param size Current size of the list.
     * @return The message indicating the given Task has been added.
     */
    public String showAdd(String task, int size) {
        return String.format("Noted. I've added this task:%n%s%nNow you have %d task%s in the list.%n",
                task, size, size > 1 ? "s" : "");
    }

    /**
     * Returns the message indicating the given Task has been deleted.
     *
     * @param task String representation of the Task.
     * @param size Current size of the list.
     * @return The message indicating the given Task has been deleted.
     */
    public String showDelete(String task, int size) {
        return String.format("Noted. I've removed this task:%n%s%nNow you have %d task%s in the list.%n",
                task, size, size != 1 ? "s" : "");
    }

    /**
     * Returns the message indicating the given Task has been set to high priority.
     *
     * @param task String representation of the Task.
     * @return The message indicating the given Task has been set to high priority.
     */
    public String showHighPriority(String task) {
        return String.format("Noted. I've set this task to high priority:%n%s%n", task);
    }
}
