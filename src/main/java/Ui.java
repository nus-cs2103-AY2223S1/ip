package anya;

import java.util.List;

/**
 * Represents the interaction with the users.
 */
public class Ui {


    Ui() {

    }

    /**
     * Greet the users at the start of the program.
     * @return the greeting by Anya.
     */
    String greet() {
        return "Helloooo! I'm Anya\n" + "What can Anya do for you,master?\n" + "Waku Waku :)\n";
    }

    /**
     * Reply the users when the users' input is bye.
     */
    String exit() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Reply the users when the users' input is to add todo, deadline, and event.
     * @return the reply by Anya showing the task that is being added and the current number of tasks in the list.
     */
    String added(String taskFullDescription, int totalNoOfTask) {
        return "Got it, Anya have added this task:\n" + taskFullDescription + "\n"
                + "Now you have " + totalNoOfTask + " tasks in the list.\n";
    }

    /**
     * Reply the users when the users' input is to delete todo, deadline, and event.
     * @return the reply by Anya showing the task that is being deleted and the current number of tasks in the list.
     */
    String deleted(String taskFullDescription, int totalNoOfTask) {
        return "Noted. Anya have removed this task:\n" + taskFullDescription + "\n"
                + "Now you have " + totalNoOfTask + " tasks in the list.\n";
    }

    /**
     * Reply the users when the users' input is to mark a task as done.
     * @return the reply by Anya that shows the task that is marked as done currently.
     */
    String marked(String taskFullDescription) {
        return "Nice! Anya have marked this task as done:\n" + taskFullDescription + "\n";
    }

    /**
     * Reply the users when the users' input is to unmark a task as not done.
     * @return the reply by Anya that shows the task that is unmarked currently.
     */
    String unmarked(String taskFullDescription) {
        return "OK! Anya have marked this task as not done yet:\n" + taskFullDescription + "\n";
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
     * @return the reply by Anya that shows the current taskList or no list.
     */
    String printList(List<Task> taskList) {
        String printedList = "";

        if (taskList.isEmpty()) {
            printedList = "There are no tasks in the list yet.";
        } else if (taskList != null) {
            printedList = "Here are the tasks in your list:\n";
        }

        printedList = printedList + getPrintedList(taskList);
        return printedList;
    }

    /**
     * Reply the users when the users' input is to find tasks with a certain keyword.
     * @return the reply by Anya that shows a list of matching tasks that contain the keyword or no matching task.
     */
    String printMatchingList(List<Task> taskList) {
        String printedList = "";

        if (taskList.isEmpty()) {
            printedList = "There are no matching tasks, please recheck your keyword.";
        } else if (taskList != null) {
            printedList = "Here are the matching tasks in your list:\n";
        }

        printedList = printedList + getPrintedList(taskList);
        return printedList;
    }

    /**
     * Reply the users when the users' input is to sort deadlines.
     * @return the reply by Anya that shows a list of sorted-by-date deadlines.
     */
    String printSortedDeadlineList(List<Deadline> sortedDeadlines) {
        String printedList = "";

        if (sortedDeadlines.isEmpty()) {
            printedList = "There are no deadlines.";
            return printedList;
        } else if (sortedDeadlines != null) {
            printedList = "Here are the sorted deadlines:\n";
            for (int i = 1; i < sortedDeadlines.size() + 1; i++) {
                String task = sortedDeadlines.get(i - 1).toString();
                printedList = printedList + i + ". " + task + "\n";
            }
        }
        return printedList;
    }

    /**
     * Reply the users when require help.
     * @return a list of available commands.
     */
    String getHelp() {
        return "The available commands:\n" + "- todo DESCRIPTION\n" + "- deadline DESCRIPTION /by YYYY-MM-DD\n" +
                "- event DESCRIPTION /at YYYY-MM-DD\n" + "- list\n" + "- mark TASK_NO\n" +
                "- unmark TASK_NO\n" + "- delete TASK_NO\n" + "- find KEYWORD\n" +
                "- sortdeadlines\n" + "- bye\n";
    }

    /**
     * Reply the users when there is a mismatch in the user's input.
     */
    String printMisMatchInputError() {
        return "Anya do not understand what you means.\n" +
                "Please type help if you need the available commands info.\n";
    }

    /**
     * Reply the users when there is no task description given.
     */
    String printNoTaskDescriptionError(String task) {
        return "The description of a " + task + " cannot be empty.\n" + "Please give a task description.\n";
    }

    /**
     * Reply the users when the task number given is out of bound of tasklist.
     */
    String printIndexOutOfBoundError(int taskNo) {
        return "Your tasklist has only " + taskNo + " tasks.\n" + "Please re-enter the task no.\n";
    }

    /**
     * Reply the users when there is missing dates in the deadline or event.
     */
    String printMissingDateError(String task) {
        String missingData = "";
        if (task.equals("deadline")) {
            missingData = "/by YYYY-MM-DD";
        } else if (task.equals("event")) {
            missingData = "/at YYYY-MM-DD";
        }
        return "There is missing date in " + task + ".\n" + "Please add " + missingData + " in your command.\n";
    }

    /**
     * Reply the users when the date typed is of incorrect format.
     */
    String printDateFormatError() {
        return "Please retype the date in YYYY-MM-DD format.\n";
    }





}
