package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Event implements methods for displaying messages in Duke.
 *
 * @author Isaac Li Haoyang
 * @version v0.2
 */
public class UI {

    public String welcomeMessage() {
        return "     Hello! I'm Duke\n     What can I do for you?";
    }

    public String goodbyeMessage() {
        return "     Bye. Hope to see you again soon!";
    }

    public boolean checkValid(String input) {
        String[] str = input.split(" ");
        return (str.length > 1);
    }

    public String printListMessage(TaskList taskList) {
        return "    " + " You have " + taskList.getSize() + " tasks in the list.";
    }

    public String findMessage() {
        return "    Here are the matching tasks in your list: ";
    }

    public String addTaskMessage(Task task, int listSize) {
        return "    " + " Got it. I've added this task: \n" +
        "       " + task + "\n" +
        "    " + " Now you have " + listSize + " tasks in the list.";
    }

    public String deleteTaskMessage(Task task, int listSize) {
        return "    " + " Got it. I've removed this task: \n" +
        "       " + task + "\n" +
        "    " + " Now you have " + listSize + " tasks in the list.";
    }

    public String deleteAllMessage() {
        return "    " + " Got it. I've removed all tasks\n" +
        "    " + " Now you have 0 tasks in the list.";
    }

    public String markTaskMessage(Task task) {
        return "    Nice! I've marked this task as done:\n" +
        "      " + task;
    }

    public String unmarkTaskMessage(Task task) {
        return "    OK, I've marked this task as not done yet:\n" +
        "      " + task;
    }

    public String incorrectCommandMessage() {
        return "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(.";
    }

    public String undoMessage() {
        return "    OK, I've undone the previous command.\n" +
        "    Here is your task list:";
    }
}
