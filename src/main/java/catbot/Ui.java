package catbot;

import catbot.task.Task;

import java.util.List;

/**
 * A Ui class to handle interactions with the user.
 */
public class Ui {

    /** TaskList to handle all tasks related operations. */
    private TaskList taskList;

    /**
     * Constructor for a Ui object.
     *
     * @param taskList TaskList to handle all tasks related operations.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints the number of current tasks, as well as how many are completed.
     */
    public String printListCount() {
        return "You have " + this.taskList.size() + " tasks currently, " + Task.totalDone + " are completed";
    }

    /**
     * Returns a list of all the tasks.
     *
     * @return The string containing the list of all the tasks.
     */
    public String printList() {
        if (this.taskList.size() == 0) {
            return "List is empty!";
        }

        String lst = "List of tasks:\n";
        for (int i = 1; i <= this.taskList.size(); i++) {
            lst = lst + "  " + i + ": " + this.taskList.get(i - 1) + "\n";
        }
        lst = lst + printListCount();
        return lst;
    }

    /**
     * Returns all the tasks from the given input list.
     *
     * @param lst The list of tasks to be printed.
     * @return The string containing the list of all the tasks from the given input list.
     */
    public String printTasks(List lst) {
        if (this.taskList.size() == 0) {
            return "";
        }

        String tasks = "";
        for (int i = 1; i <= lst.size(); i++) {
            tasks = tasks + i + ": " + lst.get(i - 1) + "\n";
        }
        return tasks;
    }

    /**
     * Returns the hello introduction message for CatBot.
     *
     * @return The hello introduction message for CatBot.
     */
    public String startMessage() {
        return "WELCOME TO CATS ONLY!!\nHow may I help you today?";
    }

    /**
     * Returns the exit program message for CatBot.
     *
     * @return The exit program message for CatBot.
     */
    public String exitMessage() {
        return "Meow meow:( Your tasks have been saved!\nHope to see you again soon!\n\n"
                + "*This cat will go to sleep in a few seconds!*";
    }
}
