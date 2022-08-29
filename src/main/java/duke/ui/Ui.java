package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;



/**
 * Ui class responsible for handling Ui and error messages
 */
public class Ui {

    private Scanner sc;
    private PrintStream out;

    /**
     * Constructor of Ui
     */
    public Ui() {
        sc = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Shows Greetings Ui
     */
    public String showGreetings() {
        String greetings = "_________________________________________________\nHello! I'm Duke"
                + "\nWhat can I do for you?\n_________________________________________________";
        return greetings;
    }

    /**
     * Shows Bye Ui
     * @return string that contains bye message
     */
    public static String showBye() {
        return ("_________________________________________________\nBye. Hope to see you again soon!\n"
                + "_________________________________________________\n");
    }

    /**
     * Shows message when directory is created
     */
    public static void showDirectoryCreation() {
        System.out.println("Creating new /data/ directory folder.");
    }

    /**
     * Shows message when file is created
     */
    public static void showFileCreation() {
        System.out.println("Creating new duke.txt file under /data/ directory folder.");
    }

    /**
     * Shows successful load
     */
    public static void showLoadSuccessful() {
        System.out.println("Loaded successfully!");
    }

    /**
     * Shows successful save
     */
    public static void showSaveSuccessful() {
        System.out.println("Saved successfully!");
    }

    /**
     * Shows the tasks in taskList
     * @param taskList the tasks to be shown
     * @return string that contains tasks in list
     */
    public static String showTasksInList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.taskListSize(); i++) {
            stringBuilder.append(i + 1 + ". " + taskList.getTask(i).toString() + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Reads the command entered by user
     * @return the command entered by user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the error message
     * @param message the error message
     * @return string that contains error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Shows when task is added
     * @param taskList the taskList that the task is added to
     * @return string that contains task added
     */
    public String showTaskAdded(TaskList taskList) {
        return ("Got it. I've added this task:")
                + "\n" + (taskList.latestTask() + "\nNow you have " + taskList.taskListSize()
                + " tasks in the list.");
    }

    /**
     * Shows when task is deleted
     * @param taskList the taskList that the task is deleted from
     * @param deletedTask the task which is deleted
     * @return string that shows task deleted
     */
    public String showTaskDeleted(TaskList taskList, Task deletedTask) {
        return ("Noted. I've removed this task:\n"
                + deletedTask) +
                ("\nNow you have " + (taskList.taskListSize() - 1) + " tasks in the list.");
    }

    /**
     * Shows when task is marked as done
     * @param taskList the taskList that the task is marked from
     * @param integer the index of the task which is marked
     * @return string that shows task marked as done
     */
    public String showMarkAsDone(TaskList taskList, int integer) {
        return ("Nice! I've marked this task as done:\n"
                + taskList.getTask(integer - 1).toString());
    }

    /**
     * Shows when task is marked as not done
     * @param taskList the taskList that the task is marked from
     * @param integer the index of the task which is marked as not done
     * @return string that shows task marked as not done
     */
    public String showMarkAsNotDone(TaskList taskList, int integer) {
        return ("OK, I've marked this task as not done yet:\n"
                + taskList.getTask(integer - 1).toString());
    }

    /**
     * Displays Ui when task is found
     * @param taskList the tasklist to be displayed
     * @return string that contains tasks found
     */
    public String showTaskFound(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.taskListSize(); i++) {
            stringBuilder.append(i + 1 + ". " + taskList.getTask(i).toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
