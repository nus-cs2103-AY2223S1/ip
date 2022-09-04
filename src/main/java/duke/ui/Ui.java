package duke.ui;

import duke.command.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Handles interactions with user through command line.
 */
public class Ui {

    static final String WELCOME_MESSAGE = "Hello! I'm Snoopy\nWhat can I do for you?";
    static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    static final String LOADING_ERROR = "There was an error loading tasks from the file";

    public void showLoadingError() {
        System.out.println(LOADING_ERROR);
    }

    public String printWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String printList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int i = tasks.size();
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int a = 1; a <= i; a++) {
            Task curr = tasks.get(a - 1);
            String taskString = curr.toString();
            output.append(a).append(".").append(taskString);
            if (curr.checkPriority()) {
                output.append(" (HIGH PRIORITY)");
            }
            output.append("\n");
        }
        return output.toString();
    }

    public String printMatchingList(ArrayList<Task> tasks) {
        int i = tasks.size();
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int a = 1; a <= i; a++) {
            String taskString = tasks.get(a - 1).toString();
            output.append(a).append(".").append(taskString).append("\n");
        }
        return output.toString();
    }

    public String printDeleteMessage(TaskList taskList, int taskNumber) {
        String output = "Noted. I've removed this task:\n";
        output += taskList.retrieveTask(taskNumber) + "\n";
        int i = taskList.getListSize() - 1;
        output += "Now you have " + i + " tasks in the list." + "\n";
        return output;
    }

    public String printAddMessage(TaskList taskList) {
        int listSize = taskList.getListSize();
        String output = "Got it. I've added this task:\n";
        output += taskList.retrieveTask(listSize) + "\n";
        output += "Now you have " + listSize + " tasks in the list.\n";
        return output;
    }

    public String printGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }
}
