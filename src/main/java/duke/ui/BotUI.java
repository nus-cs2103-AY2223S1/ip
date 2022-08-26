package duke.ui;

import static duke.common.Messages.MESSAGE_ADD_SUCCESS;
import static duke.common.Messages.MESSAGE_BOT_DIVIDER;
import static duke.common.Messages.MESSAGE_BYE;
import static duke.common.Messages.MESSAGE_DELETE_SUCCESS;
import static duke.common.Messages.MESSAGE_FOUND_TASK;
import static duke.common.Messages.MESSAGE_HI;
import static duke.common.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static duke.common.Messages.MESSAGE_MARK_SUCCESS;
import static duke.common.Messages.MESSAGE_NOTHING_IN_LIST;
import static duke.common.Messages.MESSAGE_NOT_FOUND;
import static duke.common.Messages.MESSAGE_REQUIRE_INTEGER;
import static duke.common.Messages.MESSAGE_SHOW_FORMAT;
import static duke.common.Messages.MESSAGE_TASK_NOT_EXIST;
import static duke.common.Messages.MESSAGE_UNMARK_SUCCESS;
import static duke.common.Messages.MESSAGE_USER_DIVIDER;

import java.util.ArrayList;
import java.util.Scanner;

import duke.storage.TaskRecords;
import duke.task.Task;

/**
 * Represents the user interface of duke chatBot.
 * Contains necessary method to display messages and interact with user input.
 */
public class BotUI {

    private String botSpeak(String phrase) {
        return MESSAGE_BOT_DIVIDER + phrase + "\n"
                + MESSAGE_USER_DIVIDER;
    }
    /**
     * Reads the user input.
     * @return the String of user input.
     */
    public String readCommand() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }
    /**
     * Prints hello message.
     */
    public void sayHello() {
        System.out.print(botSpeak(MESSAGE_HI));
    }
    /**
     * Prints bye message.
     */
    public void sayBye() {
        System.out.print(MESSAGE_BYE);
    }
    /**
     * Prints the divider of dukeBot.
     * Indicates the response of the dukeBot.
     */
    public void botDivider() {
        System.out.print(MESSAGE_BOT_DIVIDER);
    }
    /**
     * Prints the tasks contain in the TaskRecords.
     * @param taskList TaskRecords that store the tasks registered by user.
     */
    public void showList(TaskRecords taskList) {
        ArrayList<Task> lst = taskList.getList();
        if (lst.size() == 0) {
            System.out.print(botSpeak(MESSAGE_NOTHING_IN_LIST));
        } else {
            StringBuilder lstFormat = new StringBuilder();
            for (int i = 1; i <= lst.size(); i++) {
                lstFormat.append(i).append(". ").append(lst.get(i - 1).toString());
                lstFormat.append((i == lst.size()) ? "" : "\n");
            }
            System.out.print(botSpeak(lstFormat.toString()));
        }
    }
    /**
     * Prints isDone status of the task.
     * @param task Task that have been mark after the user command.
     */
    public void informMarkStatus(Task task) {
        String printMessage = botSpeak((task.isDone()) ? String.format(MESSAGE_MARK_SUCCESS, task)
               : String.format(MESSAGE_UNMARK_SUCCESS, task));
        System.out.print(printMessage);
    }
    /**
     * Prints task added into the TaskRecords.
     * @param task Task that have been added by user.
     * @param taskList the updated TaskRecords to show user the updated number of tasks in it.
     */
    public void addStatus(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        System.out.print(botSpeak(String.format(MESSAGE_ADD_SUCCESS, task, lst.size())));
    }
    /**
     * Prints task removed from the TaskRecords.
     * @param task Task that have been added by user.
     * @param taskList the updated TaskRecords to show user the updated number of tasks in it.
     */
    public void successRemoved(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        System.out.print(botSpeak(String.format(MESSAGE_DELETE_SUCCESS, task, lst.size())));
    }
    /**
     * Prints the list of tasks found in the TaskRecords according to user find command.
     * @param foundTaskList the updated TaskRecords to show user the updated number of tasks in it.
     */
    public void taskFound(TaskRecords foundTaskList) {
        StringBuilder taskString = new StringBuilder();
        for (Task t : foundTaskList.getList()) {
            taskString.append(t).append("\n");
        }
        String printString = taskString.substring(0, taskString.lastIndexOf("\n"));
        System.out.print(botSpeak(MESSAGE_FOUND_TASK + "\n" + printString));
    }
    /**
     * Prints and informs user no task is found according to the find command.
     */
    public void taskNotFound() {
        System.out.print(botSpeak(MESSAGE_NOT_FOUND));
    }
    /**
     * Returns the format of the input that user should follow.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String invalidFormat() {
        return botSpeak(MESSAGE_SHOW_FORMAT);
    }
    /**
     * Returns the message of task not exist.
     * Uses when user trying to modify task that does not exist in the TaskRecords.
     * @param taskList current taskList to show user the number of tasks in it.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String taskNotExist(TaskRecords taskList) {
        return botSpeak(String.format(MESSAGE_TASK_NOT_EXIST,
                taskList.getList().size()));
    }
    /**
     * Returns the message of error when user type invalid mark/unmark/delete input format.
     * Informs user the these command require integer information.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String invalidCheckFormat() {
        return botSpeak(MESSAGE_REQUIRE_INTEGER);
    }
    /**
     * Returns the message of error when user type invalid date input format.
     * Informs user the correct date format for the input.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String invalidDateFormat() {
        return botSpeak(MESSAGE_INVALID_DATE_FORMAT);
    }
}
