package duke.ui;

import static duke.common.Messages.MESSAGE_ADD_SUCCESS;
import static duke.common.Messages.MESSAGE_BOT_RESPONSE;
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
import static duke.common.Messages.MESSAGE_USER_SAY;

import java.util.ArrayList;
import java.util.Scanner;

import duke.storage.TaskRecords;
import duke.task.Task;

/**
 * Represents the user interface of duke chatBot.
 * Contains necessary messages to respond to the user.
 * Contains method that format the responses to more interactive.
 */
public class BotUI {

    private String botSpeak(String phrase) {
        return MESSAGE_BOT_RESPONSE + phrase;
    }

    public static String userSpeak() {
        return MESSAGE_USER_SAY;
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
     * Returns hello message.
     * @return hello message from Duke.
     */
    public String sayHello() {
        return botSpeak(MESSAGE_HI);
    }
    /**
     * Returns bye message.
     * @return hello message from Duke.
     */
    public String sayBye() {
        return botSpeak(MESSAGE_BYE);
    }
    /**
     * Returns the tasks contain in the TaskRecords.
     * @param taskList TaskRecords that store the tasks registered by user.
     * @return Numbering-formatted String of the tasks in the task list.
     */
    public String showList(TaskRecords taskList) {
        ArrayList<Task> lst = taskList.getList();
        if (lst.size() == 0) {
            return botSpeak(MESSAGE_NOTHING_IN_LIST);
        } else {
            StringBuilder lstFormat = new StringBuilder();
            for (int i = 1; i <= lst.size(); i++) {
                lstFormat.append(i).append(". ").append(lst.get(i - 1).toString());
                lstFormat.append((i == lst.size()) ? "" : "\n");
            }
            return botSpeak(lstFormat.toString());
        }
    }
    /**
     * Returns complete status of the task.
     * @param task Task that have been mark after the user command.
     * @return The String of completed task.
     */
    public String informMarkStatus(Task task) {
        String response = botSpeak((task.isDone()) ? String.format(MESSAGE_MARK_SUCCESS, task)
               : String.format(MESSAGE_UNMARK_SUCCESS, task));
        return response;
    }
    /**
     * Returns task added into the TaskRecords.
     * @param task Task that have been added by user.
     * @param taskList the updated TaskRecords to show user the updated number of tasks in it.
     * @return The String of task added.
     */
    public String addStatus(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        return botSpeak(String.format(MESSAGE_ADD_SUCCESS, task, lst.size()));
    }
    /**
     * Returns task removed from the TaskRecords.
     * @param task Task that have been added by user.
     * @param taskList the updated TaskRecords to show user the updated number of tasks in it.
     * @return The String of task added.
     */
    public String successRemoved(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        return botSpeak(String.format(MESSAGE_DELETE_SUCCESS, task, lst.size()));
    }
    /**
     * Returns the list of tasks found in the TaskRecords according to user find command.
     * @param foundTaskList the updated TaskRecords to show user the updated number of tasks in it.
     * @return The String task found.
     */
    public String taskFound(TaskRecords foundTaskList) {
        StringBuilder taskString = new StringBuilder();
        for (Task t : foundTaskList.getList()) {
            taskString.append(t).append("\n");
        }
        String responseString = taskString.substring(0, taskString.lastIndexOf("\n"));
        return botSpeak(MESSAGE_FOUND_TASK + "\n" + responseString);
    }
    /**
     * Returns the message of no task is found.
     * @return String of "no task found" message.
     */
    public String taskNotFound() {
        return botSpeak(MESSAGE_NOT_FOUND);
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
