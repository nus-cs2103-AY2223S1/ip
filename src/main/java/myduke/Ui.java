package myduke;

import task.Task;

/**
 * This class deals with interaction with the user.
 */
public class Ui {
    private static final String LINE = "------------------------------------------";
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor for ui.
     *
     * @param taskLists TaskList used to store the tasks.
     * @param storage   Storage used to save to file.
     */
    public Ui(TaskList taskLists, Storage storage) {
        this.taskList = taskLists;
        this.storage = storage;
    }

    private static String wrapper(String content) {
        return LINE + "\n" + content + "\n" + LINE;
    }

    /**
     * This prints the welcome message.
     */
    public String welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return (wrapper(logo + "Hello! I'm Duke\nWhat can I do for you?"));
    }

    public String goodbye() {
        String message = "Bye. Hope to see you again soon!";
        return wrapper(message);
    }

    public String addTasktoString(Task task, TaskList tasklist) {
        String message = "Got it. I've added this task:\n" + task + tasklist.numOfTaskToString();
        return wrapper(message);
    }

    public String deleteTasktoString(Task task, TaskList tasklist) {
        String message = "Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskList.getNumOfTask() + " tasks in the list.";
        return wrapper(message);
    }

    public String listtoString(TaskList tasklist) {
        String message = tasklist.toString();
        return wrapper(message);
    }


    public String markCommandtoString(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task.toString();
        return wrapper(message);
    }

    public String unMarkCommandtoString(Task task) {
        String message = "OK, I've marked this task as not done yet:\n" + task.toString();
        return wrapper(message);
    }

    public String findCommandtoString(TaskList tasklist) {
        String message = tasklist.toString();
        return wrapper(message);
    }
}
