package duke;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.task.Task;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Ui deals with interactions with the user
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Ui {
    private Stage stage;

    /**
     * A constructor for Ui.
     *
     * @param stage The current Stage of the Application.
     */
    public Ui(Stage stage) {
        this.stage = stage;
    }

    /**
     * Returns the farewell message.
     *
     * @return The farewell message.
     */
    public String showBye() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> stage.close());
        delay.play();
        return String.format("Bye. Hope to see you again soon!");
    }

    /**
     * Returns the TaskList display.
     *
     * @param tasks TaskList to be displayed.
     * @return The TaskList display.
     */
    public String showList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Returns the mark-message.
     *
     * @param task The Task to be marked.
     * @return The mark-message.
     */
    public String showMark(Task task) {
        return String.format("%s%s%n", "Nice! I've marked this task as done:\n", task);
    }

    /**
     * Returns the un-mark-message.
     *
     * @param task The Task to be un-marked.
     * @return The un-mark-message.
     */
    public String showUnmark(Task task) {
        return String.format("%s%s%n", "OK, I've marked this task as not done yet:\n", task);
    }

    /**
     * Returns the add-message.
     *
     * @param task The Task to be added.
     * @param size Size of the TaskList after Task has been added.
     * @return The add-message.
     */
    public String showAdd(Task task, int size) {
        return String.format("%s%s%s%s%s", "Got it. I've added this task:\n  ", task, "\nNow you have ",
                size, " task(s) in the list.\n");
    }

    /**
     * Returns the delete-message.
     *
     * @param task The Task to be deleted.
     * @param size Size of the TaskList after Task has been deleted.
     * @return The delete-message.
     */
    public String showDelete(Task task, int size) {
        return String.format("%s%s%s%s%s", "Noted. I've removed this task:\n  ", task, "\nNow you have ",
                size, " task(s) in the list.\n");
    }

    /**
     * Returns the list of Task(s) containing the specified keyword.
     *
     * @param foundTasks The list of Task(s) to be displayed.
     * @return The list of Task(s) containing the specified keyword.
     */
    public String showFind(ArrayList<Task> foundTasks) {
        int size = foundTasks.size();
        return Stream.iterate(0, i -> i + 1)
                .limit(size)
                .map(j -> (j == size - 1)
                        ? String.format("\t%d.%s", j + 1, foundTasks.get(j))
                        : String.format("\t%d.%s\n", j + 1, foundTasks.get(j)))
                .reduce("Here are the matching task(s) in your list:\n", (x, y) -> x + y);
    }

    /**
     * Returns the update-message.
     *
     * @param task The Task to be updated.
     * @return The update-message.
     */
    public String showUpdate(Task task) {
        return String.format("%s%s%n", "OK, I've updated this task's date:\n", task);
    }
}
