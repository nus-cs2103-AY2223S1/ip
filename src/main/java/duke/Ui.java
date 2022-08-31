package duke;

import java.util.ArrayList;

/**
 * Ui class that deals with user interactions.
 */
public class Ui {

    /**
     * Prints message surrounded by lines.
     *
     * @param message String to be printed.
     */

    public String dukeReply(String message) {
        String lineBreak = "-------------------------------------------------";
        String reply = String.format("%s\n%s\n%s", lineBreak, message, lineBreak);
        return reply;
    }

    /**
     * Prints goodbye message.
     */
    public String exit() {
        return dukeReply("Bye. Hope to see you again soon!");
    }


    /**
     * Prints current list of tasks.
     *
     * @param taskList The list of tasks.
     * @return String message containing list of tasks.
     */
    public String printList(TaskList taskList) {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task curTask = taskList.get(i);
            message.append("\n").append(i + 1).append(". ").append(curTask);
        }
        return dukeReply(message.toString());
    }

    public String printFind(ArrayList<Task> filteredList) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < filteredList.size(); i++) {
            Task curTask = filteredList.get(i);
            message.append("\n").append(i + 1).append(". ").append(curTask);
        }
        return dukeReply(message.toString());
    }

    /**
     * Prints added task, and updated number of tasks.
     *
     * @param task The task to be added.
     * @param taskList The TaskList after addition.
     */
    public String echoTask(Task task, TaskList taskList) {
        int size = taskList.size();
        return dukeReply("Got it. I've added this task: \n  " + task + "\nNow you have " + size + " task(s) in the list.");
    }

    /**
     * Prints delete message, and updated number of tasks.
     *
     * @param task The task to be deleted.
     * @param taskList The TaskList after deletion.
     */
    public String echoDelete(Task task, TaskList taskList) {
        return dukeReply("Noted. I've removed this task:\n  " + task +"\nNow you have " + taskList.size() + " task(s) in the list." );
    }
}
