package duke;

public class Ui {

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String getWelcome() {
        return "Hello! I'm Duke.\n"
                + "What can I do for you?\n"
                + "You can enter the command 'help' for a list of things I can do!";
    }

    /**
     * Returns invalid task index error message.
     *
     * @return Invalid task index error message.
     */
    public String getInvalidTaskIndexError() {
        return "This task number is invalid!";
    }

    /**
     * Returns task added message.
     *
     * @param task Task that was added.
     * @param tasks TaskList that the Task was added to.
     * @return Task added message.
     */
    public String getTaskAddedMessage(Task task, TaskList tasks) {
        return "Okay, I've added this task:\n" + task.toString()
                + "\nYou now have " + tasks.size() + " tasks.";
    }

    /**
     * Returns a list of all available commands and their formats.
     *
     * @return A list of commands.
     */
    public String getHelp() {
        return "Here is the list of commands:\n"
                + "1. todo [description] - adds a todo to your task list\n"
                + "2. deadline [description] /by [date] - adds a deadline to your task list\n"
                + "3. event [description] /at [date] - adds an event to your task list\n"
                + "4. list - retrieves a list of your added items\n"
                + "5. mark [task number] - marks the task as done\n"
                + "6. unmark [task number] - unmarks the task as done\n"
                + "7. delete [task number] - deletes the task\n"
                + "8. find [keyword] - finds all tasks containing the keyword\n";
    }
}
