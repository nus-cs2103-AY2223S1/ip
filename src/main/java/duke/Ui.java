package duke;

/**
 * The class with deals with user interaction.
 */
public class Ui {
    /**
     * Prints a welcome message when the program starts.
     */
    public String welcome() {
        return "Hi, I'm Jamie.\nWhat do you want to do?"
                + "\nFor dates and time, please enter in the format:"
                + "\ndd/MM/yyyy HHmm eg. 29/10/2022 0000";
    }

    /**
     * The method to list out the tasks that are present in taskList.
     */
    public String printTaskList(TaskList tasks) {
        StringBuilder result = new StringBuilder("Here are your tasks\n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.getTask(i - 1);
            result.append(i + "." + t + "\n");
        }
        return result.toString();
    }

    /**
     * Prints out the message when a task is added.
     * @param task The task that is added.
     */
    public String addTasksUi(Task task) {
        return "Got it! I've added this task:\n  "
                + task;
    }

    /**
     * Prints out the message for when a task is being marked.
     * @param marking The marking that is being done.
     * @param task The task that is being marked.
     */
    public String markingUi(String marking, Task task) {
        if (marking.equals("mark")) {
            return "Ok! I've marked it as done.\n  "
                    + task.toString();
        } else {
            return "Ok! I've marked it as undone.\n  "
                    + task.toString();
        }
    }

    /**
     * Prints out the message for when a task is deleted.
     * @param task The task that is deleted.
     */
    public String deleteUi(Task task) {
        return "Ok, I've removed this task:\n  "
                + task.toString();
    }

    /**
     * Prints out the message that displays the number of tasks in the list of tasks.
     * @param tasks The list of tasks.
     */
    public String numberOfTasksUi(TaskList tasks) {
        return "\nYou now have " + tasks.size() + " tasks in the list :)";
    }

    /**
     * Prints out an error message when there are missing numbers.
     * @param command The command that was called.
     */
    public String missingNumberAfterCommand(String command) {
        return "Please enter numbers after " + command;
    }

    /**
     * Prints out an error message when an invalid command is called.
     */
    public String invalidCommand() {
        return "Please enter a valid command";
    }

    /**
     * Prints out an error message when something is missing in the description of a task.
     * @param task The task that has a missing description.
     */
    public String someThingMissingError(String task) {
        return "Something is missing in the description of this + " + task + "! >:(";
    }

    /**
     * The error message for when there is no such task.
     */
    public String noSuchTaskError() {
        return "There's no such task!";
    }
}
