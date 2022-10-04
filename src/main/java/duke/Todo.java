package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents a task that has no due date or time.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     * @param description A string that provides information about the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Updates the todo task with new details contained in the string 'input'.
     *
     * @param input The string containing the new updated information.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    @Override
    public void update(String input, VBox dialogContainer, Image dukeImage) {
        assert input != null : "input should not be null";

        super.description = input;
        this.sendTaskUpdatedMessage(dialogContainer, dukeImage);
    }

    /**
     * Returns the todo task as a string.
     * @return The todo task as a string.
     */
    @Override
    public String toString() {
        return ("T | " + super.toString());
    }
}
