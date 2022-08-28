package duke;

import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class for Duke.
 *
 * It consists of methods that deals with interaction with users.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Ui {
    private static final String BOT_NAME = "Duke";

    /**
     * Prints Greetings Message.
     */
    public void greetings() {
        System.out.println("\n-----------------------------------------");
        System.out.printf("Hello! I'm the Magical ChatBot, %s!%n", BOT_NAME);
        System.out.println("What can I help you with today?");
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints Farewell Message.
     */
    public void farewell() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints error message.
     *
     * @param e An exception.
     */
    public void printErr(Exception e) {
        System.out.println("\n-----------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints message.
     *
     * @param message The message to be printed.
     */
    public void printErr(String message) {
        System.out.println("\n-----------------------------------------");
        System.out.println(message);
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints message on task completion.
     *
     * @param task Task that is completed.
     */
    public void markMessage(Task task) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task);
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints message on task completion undo-ed.
     *
     * @param task Completed Task that is undo-ed.
     */
    public void unmarkMessage(Task task) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println("    " + task);
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints tasks in TaskList.
     *
     * @param tasks The list of tasks.
     */
    public void listTasks(TaskList tasks) {
        int counter = 0;
        System.out.println("\n-----------------------------------------");
        System.out.println("Here are the tasks in your list");
        for (Task task : tasks.getTaskList()) {
            System.out.println(++counter + "." + task);
        }
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints matched tasks in List.
     *
     * @param tasks The list of matched tasks.
     */
    public void listMatchedTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\n-----------------------------------------");
            System.out.println("Boo... No matching tasks found!");
            System.out.println("-----------------------------------------\n");
        }
        int counter = 0;
        System.out.println("\n-----------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            System.out.println(++counter + "." + task);
        }
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints message on task addition.
     *
     * @param size Size of TaskList after addition.
     * @param task Task added.
     */
    public void addMessage(int size, Task task) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints message on task deletion.
     *
     * @param size Size of TaskList after deletion.
     * @param task Task deleted.
     */
    public void deleteMessage(int size, Task task) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints help message.
     *
     * Shows the list of available commands.
     */
    public void helpMessage() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Here are the list of available commands");
        System.out.println("List: List all tasks");
        System.out.println("Command: list\n");

        System.out.println("Mark: Set selected task as completed");
        System.out.println("Command: mark (index of task)\n");

        System.out.println("Unmark: Set selected task as not completed");
        System.out.println("Command: unmark (index of task)\n");

        System.out.println("Delete: Delete selected task");
        System.out.println("Command: delete (index of task)\n");

        System.out.println("ToDo: adds a Todo Task");
        System.out.println("Command: Todo (description)\n");

        System.out.println("Deadline: adds a Deadline Task");
        System.out.println("Command: Deadline (description) /by (YYYY-MM-DD 24hr)\n");

        System.out.println("Event: adds a Event Task");
        System.out.println("Command: Event (description) /at (YYYY-MM-DD 24hr)");
        System.out.println("-----------------------------------------\n");
    }
}
