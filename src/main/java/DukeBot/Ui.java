package DukeBot;

/**
 * Encapsulates the User Interface.
 */
public class Ui {

    public static String showPriorityChange(Task newTask) {
        return "I have changed the priority of this task:\n" +
        newTask;
    }

    /**
     * Returns the new task as a string.
     *
     * @param newTask to be printed on the output.
     * @return New task in string format.
     */
    public static String showNewTask(Task newTask) {
        String toReturn = "Got it. I've added this task:\n" +
                newTask +
                String.format("Now you have %d tasks in the list.", Task.getTaskCount());
        return toReturn;
    }

    /**
     * Returns the welcome message.
     *
     * @return A string containing the welcome message.
     */
    public static String showWelcome() {
        return "Hi this is Duke. What can I do for you?";
    }

    /**
     * Returns the error as a string.
     *
     * @param e to be printed on the output.
     * @return The error in string format.
     */
    public static String showError(Exception e) {
        return e.toString();
    }

    /**
     * Returns a string that lists out the tasks.
     *
     * @param tasks
     * @return String with all the tasks listed out
     */
    public static String showList(TaskList tasks, boolean isFind) {
        StringBuilder toReturn = new StringBuilder();
        if (isFind) {
            toReturn.append("Here are the matching tasks in your list:\n");
        } else {
            toReturn.append("    Here are the tasks in your list:\n");
        }
        for (int i = 0; i < tasks.size(); i++) {
            toReturn.append(String.format("      %d. %s\n", i + 1, tasks.get(i)));
        }
        return toReturn.toString();
    }

    /**
     * Returns the goodbye message.
     *
     * @return goodbye message.
     */
    public static String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns message saying task deleted.
     *
     * @param deletedTask Task that is deleted.
     * @return A message saying task is deleted.
     */
    public static String showDelete(Task deletedTask) {
        String toReturn = "Noted. I've removed this task:\n" +
                String.format("      %s\n", deletedTask) +
                String.format("    Now you have %d tasks in the list.", Task.getTaskCount());
        return toReturn;
    }

}
