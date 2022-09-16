package sally.ui;

import sally.main.MainWindow;
import sally.task.Task;
import sally.task.TaskList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Ui class where all ui is handled.
 *
 * @author liviamil
 */

public class Ui {
    protected String BORDER ="-------------------------------------------------------------------------------------";
    private Scanner sc;
    private MainWindow mainWindow;
    private static final String[] COMMANDS = {
        "todo",
        "deadline",
        "event",
        "list",
        "mark",
        "unmark",
        "find",
        "delete",
        "bye",
        "help"
    };
    private static final String TOTAL_COMMANDS = Arrays.stream(COMMANDS).collect(Collectors.joining("\n"));

    /**
     * Constructor for Ui Class
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints parsed inputs with borders
     *
     * @param s messages to be displayed with borders
     */
    public void printWithBorder(String s) {
        System.out.println(BORDER);
        System.out.println(s);
        System.out.println(BORDER);
    }

    /**
     * Displays Sally's responses to the main window
     *
     * @param s message to be displayed
     */
    public void displaySally(String s) {
        mainWindow.addSallyDialog(s);
    }

    /**
     * Shows greetings at the beginning
     */
    public void showGreeting() {
        displaySally("Hello! I'm Sally\nWhat can I do for you?");
    }

    /**
     * Gets the user command
     *
     * @return user command as string
     */
    public String getUserCommand() {
        return sc.nextLine();
    }

    /**
     * Gets the list in String format of tasks
     *
     * @param tasks to retrieve the list from
     * @return String format of list
     */
    public String getList(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            int number = i + 1;
            output = output + number + ". " + tasks.getTask(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Shows the list to user
     *
     * @param tasks to retrieve the list from
     */
    public void showList(TaskList tasks) {
        if (tasks.getNumOfTasks() == 0) {
            displaySally("You don't have any list right now");
        } else {
            displaySally("Here's your current list:\n" + getList(tasks));
        }
    }

    /**
     * Shows the deleted task to user
     *
     * @param removed task to show to user
     */
    public void showDeleted(String removed) {
        displaySally("This task has been removed from your to-do list:\n" + removed);
    }

    /**
     * Shows the unmarked task to user
     *
     * @param unmarkedTask task to show to user
     */
    public void showUnmarked(String unmarkedTask) {
        displaySally("Got it, I've unmarked this task for you!\n" + unmarkedTask);
    }

    /**
     * Shows the previously unmarked task to user
     *
     * @param notMarked task to show to user
     */
    public void showPreviouslyUnmarked(String notMarked) {
        displaySally("You have not marked: \n  " + notMarked);
    }

    /**
     * Shows the marked task to user
     *
     * @param markedTask task to show to user
     */
    public void showMarked(String markedTask) {
        displaySally("Got it, I've marked this task for you!\n" + markedTask);
    }

    /**
     * Shows the previously marked task to user
     *
     * @param marked task to show to user
     */
    public void showPreviouslyMarked(String marked) {
        displaySally("You have previously done: \n" + marked);
    }

    /**
     * Shows the added task to user
     *
     * @param tasks to show to user
     */
    public void showAddedTask(TaskList tasks) {
        int taskNum = tasks.getNumOfTasks();
        String message = (taskNum == 1)
            ? "Now you have 1 task in your list."
            : "Now you have " + taskNum + " tasks in your list.";
        displaySally("Got it. I've added this task:\n" + tasks.getTask(taskNum - 1).toString() + "\nto your list! " + message);
    }

    /**
     * Shows goodbye message to user
     */
    public void showGoodbye() {
        displaySally("Bye, until next time!");
    }

    /**
     * Shows error message to user
     *
     * @param error message to be shown
     */
    public void showError(String error) {
        displaySally(error);
    }

    public void showFoundTasks(ArrayList<Task> foundTasks) {
        String output = "";
        for (int i = 0; i < foundTasks.size(); i++) {
            output = output + (i + 1) + ". " + foundTasks.get(i).toString() + "\n";
        }
        displaySally("Here are the matching tasks in your list: \n" + output);
    }

    /**
     * Sets the main window
     *
     * @param mw main window to be set
     */
    public void setMainWindow(MainWindow mw) {
        this.mainWindow = mw;
    }

    public void showHelpList() {
        displaySally("Here are the list of commands Sally understands!\n" + TOTAL_COMMANDS + "\nFor more information on each command, type 'help <command>'");
    }

    public String getTodoHelp() {
        return "written in: todo DESCRIPTION, where DESCRIPTION is the todo description.\n\nExample: todo read book";
    }

    public String getDeadlineHelp() {
        return "written in: deadline DESCRIPTION /by TIME, where DESCRIPTION is the deadline description, and TIME is the given deadline.\n\n"
                + "TIME using dates must be written in the form of dd-mm-yyyy, otherwise can be written without any format.\n\n"
                + "Example: deadline do CS2103T quiz /by 16-09-2022 or deadline do CS2103T quiz /by tonight";
    }

    public String getEventHelp() {
        return "written in: event DESCRIPTION /at VENUE, where DESCRIPTION is the event description, and VENUE is the event venue.\n\n" +
                "Example: event tP meeting /at COM3";
    }

    public String getListHelp() {
        return "displays all the task list. \n\nExample: list";
    }

    public String getMarkHelp() {
        return "marks a task based on the given number.\n\n" +
                "written in: mark INDEX where INDEX is the task number to be marked as done.\n\n" +
                "Example: mark 2";
    }

    public String getUnmarkHelp() {
        return "unmarks a task based on the given number.\n\n" +
                "written in: unmark INDEX where INDEX is the task number to be unmarked.\n\n" +
                "Example: unmark 3";
    }

    public String getDeleteHelp() {
        return "deletes a task based on the given number.\n\n" +
                "written in: delete INDEX where INDEX is the task number to be deleted.\n\n" +
                "Example: delete 2";
    }

    public String getFindHelp() {
        return "finds a task containing the given keyword.\n\n" +
                "written in: find KEYWORD where KEYWORD is the keyword used to filter tasks.\n\n" +
                "Example: find meeting";
    }

    public String getByeHelp() {
        return "bid farewell to Sally to save your list\n\n" +
                "Example: bye";
    }

    public void showHelpFor(String command) {
        switch (command) {
            case "todo":
                displaySally(getTodoHelp());
                break;
            case "deadline":
                displaySally(getDeadlineHelp());
                break;
            case "event":
                displaySally(getEventHelp());
                break;
            case "list":
                displaySally(getListHelp());
                break;
            case "mark":
                displaySally(getMarkHelp());
                break;
            case "unmark":
                displaySally(getUnmarkHelp());
                break;
            case "delete":
                displaySally(getDeleteHelp());
                break;
            case "find":
                displaySally(getFindHelp());
                break;
            case "bye":
                displaySally(getByeHelp());
                break;
        }
    }
}
