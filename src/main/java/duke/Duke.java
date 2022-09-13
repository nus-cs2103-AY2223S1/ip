package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Duke bot.
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage("data/nuke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load storage");
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Deletes the task at the specified index and returns the response string.
     *
     * @param index Index of task to be deleted.
     * @return The response string.
     */
    public String deleteTask(int index) {
        Task t = taskList.deleteTask(index);
        try {
            storage.rewriteFile(taskList.getTasks());
        } catch (IOException e) {
            return "Unable to write to file.";
        }
        return "Noted. I've removed this task:\n" + "  " + t
                + "\nNow you have " + taskList.getCount() + " tasks in the list.";
    }

    /**
     * Finds the tasks containing the given keyword and returns the response string.
     *
     * @param keyword The given keyword.
     * @return The response string.
     */
    public String findTask(String keyword) {
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(keyword);
        if (matchingTasks.isEmpty()) {
            return "No results found for keyword '" + keyword + "'";
        } else {
            String tasks = "";
            for (int i = 0; i < matchingTasks.size(); ++i) {
                tasks += (i + 1) + ". " + matchingTasks.get(i).toString() + "\n";
            }
            return "Here are the matching tasks in your list:\n" + tasks;
        }
    }

    public String listTasks() {
        return "Here are the tasks in your list:\n" + taskList.listTasks();
    }

    /**
     * Adds the given task to the task list and returns the response string.
     *
     * @param t The task to add.
     * @return The response string.
     */
    public String addTask(Task t) {
        taskList.addTask(t);
        try {
            storage.appendTaskToFile(t);
        } catch (IOException e) {
            return "Unable to write to file.";
        }
        return "Got it. I've added this task:\n" + "  " + t + "\nNow you have "
                + taskList.getCount() + " tasks in the list.";
    }

    /**
     * Marks or unmarks the task at the specified index, depending on the boolean supplied.
     * @param index The index of the task to mark/unmark.
     * @param b Boolean that indicates whether or not to mark the task.
     * @return The response string.
     */
    public String markTask(int index, boolean b) {
        String ret;
        Task t = taskList.getTasks().get(index);
        taskList.markTask(t, b);
        if (b) {
            ret = "Nice! I've marked this task as done: \n"
                    + "  " + t;
        } else {
            ret = "OK, I've marked this task as not done: \n"
                    + "  " + t;
        }
        taskList.updateDate(t);

        try {
            storage.rewriteFile(taskList.getTasks());
        } catch (IOException e) {
            return "Unable to write to file.";
        }
        return ret;
    }

    protected String getResponse(String input) {
        if (parser.parse(input)) {
            return parser.runCommand(this);
        } else {
            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else {
                return "Please enter a valid command:\n\n" + "mark\n"
                        + "unmark\n" + "list\n" + "todo\n" + "deadline\n" + "event";
            }
        }
    }
}
