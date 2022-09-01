package duke.main;

import java.util.Scanner;

/**
 * Represents the UI that is used for the interaction with the user
 */
public class Ui {

    /**
     * greet method that Sends a greeting to the user
     *
     */
    public static String greet() {
        return "Hello! I'm BotChat123 \nWhat can I do for you?";
    }

    /**
     * bye method that is sent on termination of conversation with the user
     *
     */
    public static String bye() {
        return "Bye. Please chat with me again!";
    }

    /**
     * list method that lists out the tasks in task list
     *
     * @param taskList
     */
    public static String list(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.length(); i++) {
            System.out.println(i + 1 + ": " + taskList.getTask(i));
            output += i + 1 + ": " + taskList.getTask(i) + "\n";
        }
        return output;
    }

    /**
     * repeater method that repeats the string given to it.
     *
     * @param task
     */
    public void repeater(String task) {
        System.out.println(task);
    }
}
