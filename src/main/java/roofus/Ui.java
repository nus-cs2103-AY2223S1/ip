package roofus;

import roofus.task.Task;

/**
 * Ui handles interactions with the user.
 */
public class Ui {

    /**
     * Formats Roofus' greetings.
     *
     * @return Roofus' greetings.
     */
    public String greet() {
        return "Hello I'm ROOOOFUS!!!\n"
                + "What can I do for you?";
    }

    /**
     * Formats the program's termination message.
     *
     * @return The termination message
     */
    public String signOff() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Formats any error messages.
     *
     * @param message The error message to be formatted and printed.
     * @return The formatted error message.
     */
    public String printErrMessage(String message) {
        if (message.isEmpty()) {
            return "!!!!!";
        }
        return message.toUpperCase();
    }

    /**
     * Formats the delete task message.
     *
     * @param task The specified task to be removed.
     * @param taskLength The length of TaskList after removal.
     * @return The delete task message.
     */
    public String delete(String task, int taskLength) {
        return String.format("Noted. I've removed this task:\n%s\n"
                + "Now you have %d tasks in the list.", task.toString(), taskLength);
    }

    /**
     * Formats all tasks in TaskList.
     *
     * @param taskList A Tasklist that contains all tasks.
     * @return The current list of tasks.
     */
    public String list(TaskList taskList) {
        String finalString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.length(); i++) {
            int index = i + 1;
            finalString += String.format("%d. %s\n", index,
                    taskList.getTask(i).toString());
        }
        return finalString;
    }

    /**
     * Formats all tasks that contain key in TaskList.
     *
     * @param taskList A Tasklist that contains all tasks.
     * @return The list of tasks that matches the key.
     */
    public String filterList(TaskList taskList, String key) {
        assert key.length() > 0 : "key should not be empty";
        String finalString = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(key)) {
                finalString += String.format("%d. %s\n", i + 1, taskString);
            }
        }
        return finalString;
    }

    /**
     * Formats mark task message.
     *
     * @param task The task to be marked as done.
     * @return The mark task message.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString();
    }

    /**
     * Formats unmark task message.
     *
     * @param task The task to be marked as not done.
     * @return The unmark task message.
     */
    public String unmark(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task.toString();
    }

    /**
     * Formats add task message.
     *
     * @param task The task to be added to TaskList.
     * @param taskLength The length of TaskList after the addition.
     * @return The add tasks message.
     */
    public String addTask(Task task, int taskLength) {
        String reply = String.format("Got it. I've added this task:\n%s\n"
                        + "Now you have %d tasks in the list.",
                            task.toString(), taskLength);
        return reply;
    }

    /**
     * Formats clear TaskList message.
     *
     * @return String The clear TaskList message.
     */
    public String clearStorage() {
        return "Storage has been cleared :)";
    }
}
