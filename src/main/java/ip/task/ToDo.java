package ip.task;

import java.util.Scanner;

import ip.exception.MissingDescription;

/**
 * Encapsulation of a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructor to create a to-do object.
     *
     * @param taskMetadata Contains the description and deadline of the task.
     * @throws MissingDescription If there is no description in options.
     */
    public ToDo(Scanner taskMetadata) throws MissingDescription {
        if (!taskMetadata.hasNext()) {
            throw new MissingDescription();
        }
        String taskDescription = taskMetadata.nextLine().substring(1);
        super.setTaskDescription(taskDescription);
    }

    /**
     * Constructor to create deadline object from formatted string.
     *
     * @param taskMetadata Contains data used to build the todo object.
     */
    public ToDo(String[] taskMetadata) {
        super.setTaskDescription(taskMetadata[2]);
        if (taskMetadata[1].equals("true")) {
            super.markComplete();
        }
    }

    public String formatToSave() {
        return "t|" + isComplete + "|" + taskDescription + "||\n";
    }

    @Override
    public boolean containsString(String keyword) {
        return super.taskDescription.contains(keyword);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
