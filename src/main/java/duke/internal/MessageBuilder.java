package duke.internal;

import duke.task.TaskList;

/**
 * Handles the building of messages of the user interface.
 * Method calls can be chained for a more fluent API.
 */
public class MessageBuilder {
    private final StringBuilder buffer;

    public MessageBuilder() {
        this.buffer = new StringBuilder();
    }

    /**
     * Builds the message from the buffer.
     *
     * @return the string message
     */
    public String build() {
        String result = buffer.toString();
        buffer.setLength(0);
        return result;
    }

    /**
     * Prints a normal message to the command line.
     *
     * @param message the message to print
     * @return this UI object for method chaining
     */
    public MessageBuilder addLine(String message) {
        buffer.append(message).append("\n");
        return this;
    }

    /**
     * Prints the size of a task list to the command line.
     *
     * @param tasks the task list
     * @return this UI object for method chaining
     */
    public MessageBuilder addTaskListSize(TaskList tasks) {
        String message = String.format("You now have %d %s!\n",
                tasks.size(),
                tasks.size() == 1 ? "task" : "tasks"
        );
        buffer.append(message);
        return this;
    }
}
