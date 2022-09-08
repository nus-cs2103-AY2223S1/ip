package duke;

/**
 * The class with deals with user interaction.
 */
public class Ui {
    /**
     * Returns a welcome message for when the user launch the application.
     *
     * @return A welcome message.
     */
    public String showWelcome() {
        return "Hi, I'm Jamie.\nWhat do you want to do?"
                + "\nFor dates and time, please enter in the format:"
                + "\ndd/MM/yyyy HHmm eg. 29/10/2022 0000";
    }

    /**
     * Returns the taskList in string form.
     *
     * @param tasks The taskList that is being printed.
     * @return The tasks in the taskList in string form.
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
     * Returns a message for when a task is added.
     *
     * @param task The task added.
     * @return A string of the message.
     */
    public String printAddTaskMessage(Task task) {
        return "Got it! I've added this task:\n  " + task;
    }

    /**
     * Returns a message for when a task is marked.
     *
     * @param marking The marking being done to a task.
     * @param task The task being marked.
     * @return A string of the message.
     */
    public String printMarkTaskMessage(String marking, Task task) {
        if (marking.equals("mark")) {
            return "Ok! I've marked it as done.\n  "
                    + task.toString();
        } else {
            return "Ok! I've marked it as undone.\n  "
                    + task.toString();
        }
    }

    /**
     * Returns a message for when all tasks are marked.
     *
     * @param marking The marking being done to all the tasks.
     * @return A string of the message.
     */
    public String printMarkAllTaskMessage(String marking) {
        if (marking.equals("mark")) {
            return "Ok! I've marked all tasks as done.";
        } else {
            return "Ok! I've marked all tasks as undone.";
        }
    }

    /**
     * Returns a message for when a task is deleted.
     *
     * @param task The task deleted.
     * @return A string of the message.
     */
    public String printDeleteTaskMessage(Task task) {
        return "Ok, I've removed this task:\n  "
                + task.toString();
    }

    /**
     * Returns the message when all tasks are deleted.
     *
     * @param tasks The taskList where the tasks are being removed from.
     * @return The removed tasks in the taskList in string form.
     */
    public String printRemoveAllTasksMessage(TaskList tasks) {
        StringBuilder result = new StringBuilder("These are the tasks I have deleted:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.getTask(i - 1);
            result.append(i + "." + t + "\n");
        }
        return result.toString();
    }

    /**
     * Returns a message for the number of tasks in the taskList.
     *
     * @param tasks The taskList that is being counted.
     * @return A string of the message containing the number of tasks.
     */
    public String printNumberOfTasksMessage(TaskList tasks) {
        return "\nYou now have " + tasks.size() + " tasks in the list :)";
    }

    /**
     * Returns an error message when there is missing number after a command.
     *
     * @param command The command called.
     * @return A string of the error message.
     */
    public String printMissingNumberAfterCommandError(String command) {
        return "Please enter numbers after " + command;
    }

    /**
     * Returns an error message when there is a invalid command.
     *
     * @return A string of the error message.
     */
    public String printInvalidCommandError() {
        return "Please enter a valid command";
    }

    /**
     * Returns an error message when there is something missing in a task command.
     *
     * @param task The task command called.
     * @return A string of the error message.
     */
    public String printSomethingMissingError(String task) {
        return "Something is missing in the description of this " + task + " task! >:(";
    }

    /**
     * Returns an error message when there is no such task.
     *
     * @return A string of the error message.
     */
    public String printNoSuchTaskError() {
        return "There's no such task!";
    }
}
