package duke;

/**
 * Contains all the print statements from Duke for user interaction.
 *
 */
public class Ui {
    TaskList taskList;

    /**
     * Constructor for Ui object.
     * */
    Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints an introduction of Duke to the user.
     *
     * @return String representation of a duke greeting.
     */
    public String getHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        return "Hello I'm\n" + logo + "What can I do for you?\n";
    }

    /**
     * Informs the user a task has been added to the list.
     *
     * @return String telling the user a task has been added to his list.
     */
    public String printAddedTask(String msg) {
        return "_______________________________________________________" +
                "\n" + "Nice, I have added this task to your list:\n " + msg + "\n" +
                "Great, now you have " + taskList.getSize() + " tasks in the list.\n" +
                "_______________________________________________________";
    }

    /**
     * Informs the user a task has been removed to the list.
     *
     * @return String telling the user a task has been removed to his list.
     */
    public String printRemovedTask(String msg) {
        int taskRemaining = taskList.getSize() - 1;
        return "_______________________________________________________" +
                "\n" + "OK, I have deleted this task from your list:\n " + msg + "\n" +
                "Great, now you have " + taskRemaining  + " tasks in the list.\n" +
                "_______________________________________________________";
    }

    /**
     * Informs the user a task has been marked as done in the list.
     *
     * @return String telling the user a task has been marked as done in his list.
     */
    public String printMarkDone(String msg) {
        return "_______________________________________________________" + "\n" +
                "Nice! I've marked this task as done:" +
                "\n" + msg + "\n" +
                "\n" + "_______________________________________________________";
    }

    /**
     * Informs the user a task has been marked as undone in the list.
     *
     * @return String telling the user a task has been marked as undone in his list.
     */
    public String printMarkUndone(String msg) {
        return "_______________________________________________________" + "\n" +
                "Wow! I've marked this task as not done yet:" +
                "\n" + msg + "\n" +
                "\n" + "_______________________________________________________";
    }


    /**
     * Says goodbye to the user.
     *
     * @return String that says goodbye to the user.
     */
    public String goodbye() {
        return "_______________________________________________________" +
                "\n" + "Bye. Hope to see you again soon!";
    }

    /**
     * Shows all the tasks in the user's list.
     *
     * @return String showing all the tasks in the user's list.
     */
    public String printAllTasks() {
        String userInputs = "";
        for (int i = 0; i < taskList.getSize(); i++)
        {
            int index = i + 1;
            Task tempTask = taskList.getTask(i);
            userInputs += "\n" + index + "."
                    + tempTask.toString() + "\n";
        }
        return userInputs;
    }
}
