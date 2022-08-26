package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Encapsulation of interactions with the user.
 *
 * @author   Sun Ruoxin
 * @version  %I%, %G%
 */
public class Ui {
    /**
     * The logo of the chatbot.
     */
    protected String logo = "     _   _    ______     _____ ____\n"
            + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
            + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
            + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
            + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(logo);
        say("Hello. I'm Jarvis", true, false);
        say("What can I do for you?", false, true);
    }

    /**
     * Reads in the input of the user.
     *
     * @return the input of the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command;
        command = scanner.nextLine();
        return command;
    }

    /**
     * Gives feedback to the user when a task is added.
     *
     * @param tasks  the list of the tasks
     * @param task   the task to be added
     */
    public void addMessage(TaskList tasks, Task task) {
        say("Got it. I've added this task:", true, false);
        say("  " + task, false, false);
        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
    }

    /**
     * Gives feedback to the user when a task is deleted.
     *
     * @param tasks   the list of tasks
     * @param index   the index of the task to be deleted
     * @param isDone  the status of whether the task is removed from the list
     */
    public void deleteMessage(TaskList tasks, int index, boolean isDone) {
        if (!isDone) {
            say("Noted. I've removed this task:", true, false);
            say(tasks.get(index).toString(), false, false);
        } else {
            say("Now you have " + tasks.size() + " tasks in the list.", false, true);
        }
    }

    /**
     * Gives feedback to the user when asked to print out the tasks.
     *
     * @param tasks  the list of tasks
     */
    public void listMessage(TaskList tasks) {
        say("Here are the tasks in your list:", true, false);
        for (int i = 0; i < tasks.size(); i++) {
            boolean isFirstLine = false;
            boolean isLastLine = i == tasks.size() - 1;
            say(i + 1 + ". " + tasks.get(i).toString(), isFirstLine, isLastLine);
        }
    }

    /**
     * Gives feedback to the user when a task is marked as done.
     *
     * @param tasks  the list of tasks
     * @param index  the index of the task to be marked
     */
    public void markMessage(TaskList tasks, int index) {
        say("Nice! I've marked this task as done:", true, false);
        say(tasks.get(index).toString(), false, true);
    }

    /**
     * Gives feedback to the user when a task is unmarked as done.
     *
     * @param tasks  the list of tasks
     * @param index  the index of the task to be unmarked
     */
    public void unmarkMessage(TaskList tasks, int index) {
        say("OK, I've marked this task as not done yet:", true, false);
        say(tasks.get(index).toString(), false, true);
    }

    /**
     * Gives feedback to user when searching for a keyword in the list.
     *
     * @param result the tasks in the list containing the keyword
     */
    public void findMessage(ArrayList<Task> result) {
        say("Here are the matching tasks in your list:", true, false);
        for (int i = 0; i < result.size(); i++) {
            if (i != result.size() - 1) {
                say(i + 1 + ". " + result.get(i).toString(), false, false);
            } else {
                say(i + 1 + ". " + result.get(i).toString(), false, true);
            }
        }
    }

    /**
     * Farewells the user.
     */
    public void exitMessage() {
        say("Bye. Hope to see you again soon.", true, true);
    }

    /**
     * Gives feedback to the user when an error is encountered while loading the file.
     */
    public void showLoadingError() {
        say("Encountered error while loading.", true, true);
    }

    /**
     * Gives feedback to the user when an error is encountered.
     *
     * @param errorMessage  the message of the error
     */
    public void showError(String errorMessage) {
        say("Encountered error: " + errorMessage, true, true);
    }

    /**
     * Prints out the message in a fixed format.
     *
     * @param message      the message to be printed out
     * @param isFirstLine  whether the message is the first line
     * @param isLastLine   whether the message is the last line
     */
    public void say(String message, boolean isFirstLine, boolean isLastLine) {
        String line = "____________________________________________________________";
        if (isFirstLine) {
            System.out.println(line);
        }
        System.out.println(" " + message);
        if (isLastLine) {
            System.out.println(line);
        }
    }
}
