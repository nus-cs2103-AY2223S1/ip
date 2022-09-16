package mykoba;

import task.Task;

/**
 * This class deals with messages to be shown to the user.
 */
public class Ui {
    private static final String LINE = "------------------------------------------";
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a UI object.
     *
     * @param taskLists TaskList used to store the tasks.
     * @param storage   Storage used to save to file.
     */
    public Ui(TaskList taskLists, Storage storage) {
        this.taskList = taskLists;
        this.storage = storage;
    }

    private static String wrapper(String content) {
        return content;
    }

    /**
     * Prints the welcome message.
     *
     * @return a string saying hi.
     */
    public String welcome() {
        return (wrapper("Hello, I'm Koba\nWelcome back!"));
    }

    /**
     * Prints the goodbye message.
     *
     * @return a string saying goodbye.
     */
    public String goodbye() {
        String message = "Bye. Hope to see you again soon!";
        return wrapper(message);
    }

    /**
     * Returns a confirmation message when task is added.
     *
     * @param task     task that is added.
     * @param tasklist tasklist the task is added into.
     * @return a String detailing the task being added and number of task in tasklist now.
     */
    public String addTasktoString(Task task, TaskList tasklist) {
        String message = "Got it. I've added this task:\n" + task + tasklist.numOfTaskToString();
        return wrapper(message);
    }

    /**
     * Returns a confirmation message when task is deleted.
     *
     * @param task     the deleted task.
     * @param tasklist the tasklist being deleted from.
     * @return a String detailing the task being deleted and number of tasks remaining.
     */
    public String deleteTasktoString(Task task, TaskList tasklist) {
        String message = "Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasklist.getNumOfTask() + " tasks in the list.";
        return wrapper(message);
    }

    /**
     * Returns a message showing the tasks stored in tasklist.
     *
     * @param tasklist the tasklist storing all the tasks.
     * @return a String representing the tasks in table format.
     */
    public String listtoString(TaskList tasklist) {
        String message = tasklist.toString();
        return wrapper(message);
    }

    /**
     * Returns a confirmation message when task has been marked as completed.
     *
     * @param task the task being marked as completed.
     * @return a String showing the task being marked.
     */
    public String markCommandtoString(Task task) {
        String message = "Good job! I've marked this task as done:\n" + task.toString();
        return wrapper(message);
    }

    /**
     * Returns a confirmation message when task has been marked as incomplete.
     *
     * @param task the task being marked as incomplete.
     * @return a String show the task being unmarked.
     */
    public String unMarkCommandtoString(Task task) {
        String message = "Awww, I've marked this task as not done yet :( :\n" + task.toString();
        return wrapper(message);
    }

    /**
     * Returns a list of tasks that contains the given keyword from find command.
     *
     * @param tasklist the tasklist storing the tasks.
     * @return a String showing all the tasks in table format.
     */
    public String findCommandtoString(TaskList tasklist) {
        String message = tasklist.toString();
        return wrapper(message);
    }

    /**
     * Returns a help message.
     *
     * @return a String containing the format of all valid commands.
     */
    public String getHelpPage() {
        String message = "List of commands available to user:\n"
                + "help : brings out the help page\n"
                + "list : displays all tasks stored in app\n"
                + "bye  : exits the app\n"
                + "todo DESCRIPTION : adds a todo to the app where DESCRIPTION is what the todo is\n"
                + "                   make sure to leave a space between todo and the description\n"
                + "deadline DESCRIPTION /by 2022-08-30T18:00 :\n"
                + "adds a deadline to the app, remember to separate the description and date with /by\n"
                + "date should be entered in the ISO format, YYYY-MM-DDTHH:mm\n"
                + "event DESCRIPTION /at DATE : \n"
                + "adds a event to the app, description and date to be separated with '/at'\n"
                + "DATE can be in any format\n"
                + "mark INDEX : marks a task in the list as completed, INDEX should be a number\n"
                + "             and refers to the index of the task\n"
                + "unmark INDEX : marks a task in the list as not complete, INDEX should be a number\n"
                + "               and refers to the index of the task\n"
                + "delete INDEX : delete a task from the list, INDEX is the index of the task, it should be a number\n"
                + "find KEYWORD : finds all tasks with the given KEYWORD, keyword is case sensitive";
        return wrapper(message);
    }
}
