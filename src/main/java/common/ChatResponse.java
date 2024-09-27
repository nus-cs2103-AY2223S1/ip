package common;

import tasklist.TaskList;
import tasks.Task;

/**
 * Utility class that handles returning formatted Strings according
 * to the required command.
 */
public class ChatResponse {

    /**
     * Returns the welcome text.
     */
    public static String chatShowWelcome() {
        return String.format("Hello! I'm SotongBoi! What's up?");
    }

    /**
     * Returns a generic Exception.
     *
     * @param e Exception to returnChat.
     */
    public static String returnChatError(Exception e) {
        return String.format(e.getMessage());
    }

    /**
     * Returns goodbye message.
     */
    public static String returnChatGoodbye() {
        return String.format("Please take care...");
    }

    /**
     * Returns invalid command chat.
     */
    public static String returnChatInvalidCommand() {
        return String.format("I don't understand...");
    }

    /**
     * Returns formatted contents of a given TaskList object.
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
     * Returns the flavour text when marking a task.
     *
     * @param task Task to mark.
     */
    public static String returnChatMarkTask(Task task) {
        return String.format("I see you've been hard at work! I marked this as done: %s\n", task);
    }

    /**
     * Returns the flavour text when marking a task.
     *
     * @param task Task to unmark.
     */
    public static String returnChatUnmarkTask(Task task) {
        return String.format("Changed your mind? I unmarked this task as undone: %s\n", task);
    }

    /**
     * Returns the flavour text when adding a task.
     *
     * @param task     Task that is added.
     * @param taskList Current task list after task is added.
     */
    public static String returnChatAddTask(Task task, TaskList taskList) {
        return String.format("Nice, I added a task:\n  %s\nNow you have %s %s in your list.\n",
                task,
                taskList.size(),
                taskList.size() != 1 ? "tasks" : "task");
    }

    /**
     * Returns the flavour text when deleting a task.
     *
     * @param task     Task that is added.
     * @param taskList Current task list after task is added.
     */
    public static String returnChatDeleteTask(Task task, TaskList taskList) {
        return String.format("Cool. I've removed this task:\n %s\nNow you have %s %s in the list.\n",
                task,
                taskList.size(),
                taskList.size() != 1 ? "tasks" : "task");
    }

    /**
     * Returns flavour text for creating new directory.
     *
     * @param dirName Name of directory.
     */
    public static String returnChatCreateNewDirectory(String dirName) {
        return String.format("Created new directory '%s' to store data.\n", dirName);
    }

    /**
     * Returns flavour text for creating new storage file.
     *
     * @param storageName Name of storage file.
     */
    public static String returnChatCreateNewStorage(String storageName) {
        return String.format("Created new file '%s' to store data.\n", storageName);
    }

    /**
     * Returns flavour text for saving task list to storage file.
     */
    public static String returnChatSaving() {
        return String.format("Saving...\n");
    }

    /**
     * Returns the task list where tasks matches a given target.
     *
     * @param taskList The task list.
     */
    public static String returnChatFindResults(TaskList taskList) {
        String results = String.format("I found these tasks in your list: \n");
        return String.join(results, returnChatTaskList(taskList));
    }

    /**
     * Returns formatted string containing count
     * of a specified task.
     *
     * @param task  Task of the count.
     * @param count Count of the task.
     * @return Formatted string containing the count of the task.
     */
    public static String returnStatisticTaskCount(String task, Integer count) {
        return String.format("The count of %s is %s.\n", task, count);
    }
}
