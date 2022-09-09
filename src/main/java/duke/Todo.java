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

    @Override
    public void update(String input, VBox dialogContainer, Image dukeImage) {
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

    public static void main(String[] args) {
        //Task todo = new Todo("read books");
        //todo.update("return books");
        //System.out.println(todo.toString());
    }
}
