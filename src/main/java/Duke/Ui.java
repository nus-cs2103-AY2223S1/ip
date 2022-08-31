package duke;

import java.util.Scanner;

/**
 * User interface for Duke application.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for the user interface.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getUserCommand() {
        return sc.nextLine();
    }

    /**
     * Returns boolean indicating whether there is further input from the user.
     *
     * @return true if there is further input, else false.
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Greets user with opening statements.
     *
     * @return opening statements.
     */
    public String greeting() {
        return "Hello! I'm Duke\nWhat can I do for you?\n";
    }

    /**
     * Says bye to user with closing statement.
     *
     * @return closing statements.
     */
    public String sayBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Tells user that file is not found.
     *
     * @return error message.
     */
    public String printfileNotFound() {
        return "File not Found!\n";
    }

    /**
     * Outputs list of tasks in Duke application.
     *
     * @param taskList taskList from Duke application.
     * @return list of tasks in taskList.
     */
    public String list(TaskList taskList) {
        String response = "Here are the tasks in your list:\n";
        for (int x = 0; x < taskList.size(); x++) {
            response += String.format("%s.%s\n", x + 1, taskList.get(x).toString());
        }
        return response;
    }

    /**
     * Outputs response if user adds task to taskList.
     *
     * @param addedTask task added to taskList.
     * @param taskList taskList from Duke application.
     * @return response to added task.
     */
    public String addResponse(Task addedTask, TaskList taskList) {
        String response = "Got it. I've added this task:\n";
        response += addedTask + "\n";
        response += String.format("Now you have %s task(s) in the list\n", taskList.size());
        return response;
    }

    /**
     * Outputs response if user deletes task from taskList.
     *
     * @param taskList taskList from Duke application.
     * @param index index of task to be deleted.
     * @return response to deleted task.
     */
    public String deleteResponse(TaskList taskList, int index) {
        String response = "Noted, I've removed this task:\n";
        response += taskList.get(index).toString() + "\n";
        response += String.format("Now you have %s task(s) in the list\n", taskList.size() - 1);
        return response;
    }

    /**
     * Outputs response if user marks task on taskList.
     *
     * @param taskList taskList from Duke application.
     * @param index index of task to be marked.
     * @return response to marked task.
     */
    public String markResponse(TaskList taskList, int index) {
        String response = "Nice! I've marked this task as done:\n";
        response += String.format("%s.%s\n", index + 1, taskList.get(index).toString());
        return response;
    }

    /**
     * Outputs response if user unmarks task on taskList.
     *
     * @param taskList taskList from Duke application.
     * @param index index of task to be unmarked.
     * @return response to unmarked task.
     */
    public String unmarkResponse(TaskList taskList, int index) {
        String response = "OK, I've marked this task as not done yet:\n";
        response += String.format("%s.%s\n", index + 1, taskList.get(index).toString());
        return response;
    }

    /**
     * Finds tasks in list with matching description.
     *
     * @param taskList taskList from duke application.
     * @param word word to be matched.
     * @return tasks with matching description.
     */
    public String find(TaskList taskList, String word) {
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(word)) {
                response += String.format("%s.%s\n", i + 1, taskList.get(i).toString());
            }
        }
        return response;
    }
}
