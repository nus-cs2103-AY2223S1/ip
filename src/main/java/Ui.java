package duke;

import java.util.List;

/**
 * Represents the interaction with the users.
 */
public class Ui {


    Ui() {

    }

    /**
     * Greet the users at the start of the program.
     * @return String : the greeting by Duke.
     */
    String greet() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Reply the users when the users' input is bye.
     * @return String : the reply by Duke.
     */
    String exit() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Reply the users when the users' input is to add todo, deadline, and event.
     * @return the reply by Duke : the task that is being added and the current number of tasks in the list.
     */
    String added(String taskFullDescription, int totalNoOfTask) {
        return "Got it, I've added this task:\n" + taskFullDescription + "\n"
                + "Now you have " + totalNoOfTask + " task in the list.\n";
    }

    /**
     * Reply the users when the users' input is to delete todo, deadline, and event.
     * @return the reply by Duke : the task that is being deleted and the current number of tasks in the list.
     */
    String deleted(String taskFullDescription, int totalNoOfTask) {
        return "Noted. I've removed this task:\n" + taskFullDescription + "\n"
                + "Now you have " + totalNoOfTask + " tasks in the list.\n";
    }

    /**
     * Reply the users when the users' input is to mark a task as done.
     * @return String : the reply by Duke that shows the task that is marked as done currently.
     */
    String marked(String taskFullDescription) {
        return "Nice! I've marked this task as done:\n" + taskFullDescription + "\n";
    }

    /**
     * Reply the users when the users' input is to unmark a task as not done.
     * @return String : the reply by Duke that shows the task that is unmarked currently.
     */
    String unmarked(String taskFullDescription) {
        return "OK! I've marked this task as not done yet:\n" + taskFullDescription + "\n";
    }

    private String getPrintedList(List<Task> taskList) {
        String listString = "";
        for (int i = 1; i < taskList.size() + 1; i++) {
            String task = taskList.get(i - 1).toString();
            listString = listString + i + ". " + task + "\n";
        }
        return listString;
    }

    /**
     * Reply the users when the users' input is list.
     * @return the reply by Duke that shows the current taskList or no list.
     */
    String printList(List<Task> taskList) {
        String printedList = "";

        if (taskList == null) {
            printedList = "There are no tasks in the list.";
        } else if (taskList != null) {
            printedList = "Here are the tasks in your list:\n";
        }

        printedList = printedList + getPrintedList(taskList);
        return printedList;
    }

    /**
     * Reply the users when the users' input is to find tasks with a certain keyword.
     * @return the reply by Duke that shows a list of matching tasks that contain the keyword or no matching task.
     */
    String printMatchingList(List<Task> taskList) {
        String printedList = "";

        if (taskList == null) {
            printedList = "There are no matching tasks, please recheck your keyword.";
        } else if (taskList != null) {
            printedList = "Here are the matching tasks in your list:\n";
        }

        printedList = printedList + getPrintedList(taskList);
        return printedList;
    }


}
