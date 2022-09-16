package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents a task that has a description and an indicator of whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description Provides information about what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns one of two possible icons, depending on whether the task is done.
     *
     * @return "X" if task is done, and "O" if task is not done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : "O");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates that a task has been done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("     Task has been marked as done!: \n"
                + "       "
                + this.toString());
    }

    /**
     * Overloaded method for markAsDone(), meant for JavaFX.
     *
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void markAsDone(VBox dialogContainer, Image dukeImage) {
        this.isDone = true;
        String done = "Task has been marked as done!: \n"
                + "       "
                + this.toString();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(done, dukeImage));
    }

    /**
     * Indicates that a task is undone, meant for JavaFX.
     *
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void markAsUnDone(VBox dialogContainer, Image dukeImage) {
        this.isDone = false;
        String unDone = "Task has been marked as NOT done!: \n"
                + "       "
                + this.toString();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(unDone, dukeImage));
    }

    /**
     * Checks to see if the task description contains the given keyword.
     *
     * @param keyword The word that we want to check whether is in the task description.
     * @return True if the keyword is in the description, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Updates the task with new details contained in the string 'input'.
     *
     * @param input The string containing the new updated information.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void update(String input, VBox dialogContainer, Image dukeImage) {
    }

    /**
     * Displays a message sent by Duke to tell user that the task has been updated.
     *
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void sendTaskUpdatedMessage(VBox dialogContainer, Image dukeImage) {
        String updatedTask = "Task has been updated!: \n"
                + "       "
                + this.toString();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(updatedTask, dukeImage));
    }

    /**
     * Returns what the task is about, and also an icon to indicate if the task is done.
     *
     * @return What the task is about, and also an icon to indicate if the task is done.
     */
    public String toString() {
        return (this.getStatusIcon() + " | " + this.description);
    }
}
