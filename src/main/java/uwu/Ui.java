package uwu;

import java.util.Scanner;
import uwu.task.Task;
import uwu.task.TaskList;

/**
 * Text UI of the application.
 */
public class Ui {
    /** The Scanner to read the user's input. */
    Scanner scanner = new Scanner(System.in);

    /**
     * Reads the user's input.
     *
     * @return The user's input.
     */
    public String readCommand() {
        String userCommand = scanner.nextLine().toLowerCase().trim();
        return userCommand;
    }

    /**
     * Prints a horizontal line to separate chat messages.
     */
    public void showLine() {
        String horizontalLine ="----------------------------------------------------------------------";
        System.out.println(horizontalLine);
    }

    /**
     * Greets users.
     */
    public void greetUsers() {
        String greetings = "\thellu!" +
                           "\n\ti am oo woo <:" +
                           "\n\thow can i be of service today?";
        System.out.println(greetings);
    }

    /**
     * Leaves chat.
     */
    public void leaveChat() {
        String farewellWords = "\tgood work today!" +
                                "\n\thope to see you again soon~";
        System.out.println(farewellWords);
    }

    /**
     * Tells user that their task has been added to the task list.
     *
     * @param task The task to be added.
     * @param tasksLength The length of the task list.
     */
    public void addTask(Task task, int tasksLength) {
        String addToDoString = "\too new task! ^^" +
                "\n\t\tadded:\t" + task.toString() +
                "\n\tyou have " + String.valueOf(tasksLength) + " task(s) <:";
        System.out.println(addToDoString);
    }

    /**
     * Displays the tasks in the task list.
     *
     * @param tasks The stored TaskList.
     */
    public void listTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("\tlooks like there are no tasks on your list uwu" +
                                "\n\tfeed me a task to get started~ <:");
        } else {
            System.out.println("\there are your tasks~ you've got this!" + tasks.taskListToString());
        }
    }

    /**
     * Tells user that their task has been marked.
     *
     * @param task The marked task.
     */
    public void markTask(Task task) {
        String markedAsDone = "\tyey! good job~ keep it up <3";
        System.out.println(markedAsDone + "\n\t\t" + task.toString());
    }

    /**
     * Tells user that their task has been unmarked.
     *
     * @param task The unmarked task.
     */
    public void unmarkTask(Task task) {
        String unmarked = "\tkeep going~";
        System.out.println(unmarked + "\n\t\t" + task.toString());
    }

    /**
     * Tells user that their task has been deleted.
     *
     * @param task The deleted task.
     * @param tasksLength The length of the task list.
     */
    public void deleteTask(Task task, int tasksLength) {
        System.out.println("\tremoving this task from your list...\n\t\t" +
                task.toString() +
                "\n\ttask removed~! now you have " + String.valueOf(tasksLength) + " task(s) <:");
    }

    /**
     * Displays the error encountered during execution.
     * @param e The exception message.
     */
    public void showError(String e) {
        System.out.println(e);
    }
}
