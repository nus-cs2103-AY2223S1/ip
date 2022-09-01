package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * Ui class represents the Ui interaction with the user.
 */
public class Ui {

    /**
     * Returns the greeting by Duke.
     *
     * @return The greeting by Duke.
     */
    public String Greet() {
        MultiLineFormatter multiLineFormatter = new MultiLineFormatter();
        multiLineFormatter.add("Hello, this is Siri! It is a pleasure to meet you!\n");
        multiLineFormatter.add("How may I assist you?");
        return multiLineFormatter.getFullMessage();
    }

    /**
     * Bids farewell to the user after they key in bye.
     *
     * @return The farewell to the user.
     */
    public String GoodBye() {
        return "So Long, Farewell!";
    }

    /**
     * Returns either the message of the task, the task itself and the
     * number of tasks in the list.
     *
     * @param message The message of the task.
     * @param task The task.
     * @param size The size of the task list.
     * @return The message, task and number of tasks in list.
     */
    public String displayCommandMessage(String message, Task task, Integer size) {
        MultiLineFormatter multiLineFormatter = new MultiLineFormatter();
        if (message != null) {
            multiLineFormatter.add(message);
            multiLineFormatter.add("\n");
        }
        if (task != null) {
            multiLineFormatter.add("\t\t" + task);
            multiLineFormatter.add("\n");
        }
        if (size != null) {
            String numOfTasks = String.format("You currently have %d tasks in the list", size);
            multiLineFormatter.add(numOfTasks);
        }
        return multiLineFormatter.getFullMessage();
    }

    /**
     * Return the message for the exception.
     * @param message The message for the exception.
     * @return The message for the exception.
     */
    public String showExceptionMessage(String message) {
        return "Hey Listen! There is an error!\n" + message;
    }
}
