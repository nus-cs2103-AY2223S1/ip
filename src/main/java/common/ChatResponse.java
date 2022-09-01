package common;

import tasklist.TaskList;
import tasks.Task;

/**
 * Utility class that handles returning formatted Strings according
 * to the required command.
 */
public class ChatResponse {

    /**
     * Prints the welcome text.
     */
    public static String chatShowWelcome() {
        return String.format("Hello! I'm Duke!\nWhat can I do for you?");
    }

    /**
     * Prints a generic Exception.
     *
     * @param e Exception to returnChat.
     */
    public static String returnChatError(Exception e) {
        return String.format(e.toString());
    }

    /**
     * Prints goodbye message.
     */
    public static String returnChatGoodbye() {
        return String.format("Bye. Hope to see you again soon!");
    }

    /**
     * Returns invalid command chat.
     */
    public static String returnChatInvalidCommand() {
        return String.format("I don't understand your command. Please enter a valid command!");
    }

    /**
     * Formats and returnChats the contents of a given TaskList object.
     *
     * @param taskList Task list to returnChat.
     */
    public static String returnChatTaskList(TaskList taskList) {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += String.format("%s. %s\n", i + 1, taskList.get(i));
        }
        return list;
    }

    /**
     * Prints the flavour text when marking a task.
     *
     * @param task Task to mark.
     */
    public static String returnChatMarkTask(Task task) {
        return String.format("Nice! I've marked this task as done: %s\n", task);
    }

    /**
     * Prints the flavour text when marking a task.
     *
     * @param task Task to unmark.
     */
    public static String returnChatUnmarkTask(Task task) {
        return String.format("OK, I've marked this task as not done yet: %s\n", task);
    }

    /**
     * Prints the flavour text when adding a task.
     *
     * @param task     Task that is added.
     * @param taskList Current task list after task is added.
     */
    public static String returnChatAddTask(Task task, TaskList taskList) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    /**
     * Prints the flavour text when deleting a task.
     *
     * @param task     Task that is added.
     * @param taskList Current task list after task is added.
     */
    public static String returnChatDeleteTask(Task task, TaskList taskList) {
        return String.format("Noted. I've removed this task:\n %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    /**
     * Prints flavour text for creating new directory.
     *
     * @param dirName Name of directory.
     */
    public static String returnChatCreateNewDirectory(String dirName) {
        return String.format("Creating new directory '%s' to store data...\n", dirName);
    }

    /**
     * Prints flavour text for creating new storage file.
     *
     * @param storageName Name of storage file.
     */
    public static String returnChatCreateNewStorage(String storageName) {
        return String.format("Creating new file '%s' to store data...\n", storageName);
    }

    /**
     * Prints flavour text for saving task list to storage file.
     */
    public static String returnChatSaving() {
        return String.format("Saving...\n");
    }

    /**
     * Prints the task list where tasks matches a given target.
     */
    public static String returnChatFindResults(TaskList taskList) {
        String results = String.format("Here are the matching tasks in your list: \n");
        return String.join(results, returnChatTaskList(taskList));
    }
}
