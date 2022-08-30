package duke;

public class Ui {

    /**
     * Prints out text with decorative lines.
     * Used for all output by Duke.
     *
     * @param str Text to be printed.
     */
    public void printOut(String str) {
        String line = "____________________________________________________________\n";
        System.out.println(line + str + "\n" + line);
    }

    /**
     * Prints out welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Duke.\n"
                + "What can I do for you?";
    }

    /**
     * Prints out goodbye message.
     */
    public String showGoodbye() {
        return "See you later. Bye!";
    }

    /**
     * Prints out error message for invalid task index.
     */
    public String showInvalidTaskIndexError() {
        return "This task number is invalid!";
    }

    public String showTaskAddedMessage(Task task, TaskList tasks) {
        return "Okay, I've added this task:\n" + task.toString()
                + "\nYou now have " + tasks.size() + " tasks.";
    }
}
