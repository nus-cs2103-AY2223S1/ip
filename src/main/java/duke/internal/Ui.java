package duke.internal;

import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the displaying of the user interface.
 * Most method calls can be chained for a more fluent API.
 */
public class Ui {
    private final List<String> responses;

    public Ui() {
        this.responses = new ArrayList<>();
    }

    /**
     * Clears the buffer of stored responses, returning its contents as a string.
     * @return the string response
     */
    public String flush() {
        String flushed = responses.stream()
                .map(s -> s + "\n")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        responses.clear();
        return flushed;
    }

    /**
     * Prints the welcome message.
     */
    public Ui showWelcome() {
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n";
        responses.add(logo);
        responses.add("Hello! I'm Duke!");
        responses.add("What can I do for you?");
        return this;
    }

    /**
     * Prints an error message to the command line.
     *
     * @param message the error message to print
     * @return this UI object for method chaining
     */
    public Ui showError(String message) {
        responses.add(message);
        return this;
    }

    /**
     * Prints a normal message to the command line.
     *
     * @param message the message to print
     * @return this UI object for method chaining
     */
    public Ui showMessage(String message) {
        responses.add(message);
        return this;
    }

    /**
     * Prints the size of a task list to the command line.
     *
     * @param tasks the task list
     * @return this UI object for method chaining
     */
    public Ui showTaskListSize(TaskList tasks) {
        String message = String.format("You now have %d %s.\n",
                tasks.size(),
                tasks.size() == 1 ? "task" : "tasks"
        );
        responses.add(message);
        return this;
    }
}