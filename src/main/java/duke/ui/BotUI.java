package duke.ui;

import static duke.common.Messages.ADD_SUCCESS;
import static duke.common.Messages.BOT_RESPONSE;
import static duke.common.Messages.BYE;
import static duke.common.Messages.COMMAND_CANCELED;
import static duke.common.Messages.DELETE_SUCCESS;
import static duke.common.Messages.FOUND_TASK;
import static duke.common.Messages.HI;
import static duke.common.Messages.INVALID_DATE_FORMAT;
import static duke.common.Messages.MARK_SUCCESS;
import static duke.common.Messages.NOTHING_IN_LIST;
import static duke.common.Messages.NOT_FOUND;
import static duke.common.Messages.REQUIRE_INTEGER;
import static duke.common.Messages.SAME_DATE_DETECTED;
import static duke.common.Messages.SAME_TASK_DETECTED;
import static duke.common.Messages.SHOW_FORMAT;
import static duke.common.Messages.SHOW_LIST_DESCRIPTION;
import static duke.common.Messages.TASK_NOT_EXIST;
import static duke.common.Messages.UNKNOWN_RESPONSE;
import static duke.common.Messages.UNMARK_SUCCESS;
import static duke.common.Messages.USER_SAY;

import java.util.ArrayList;
import java.util.Scanner;

import duke.storage.TaskList;
import duke.task.Task;





/**
 * Represents the user interface of duke chatBot.
 * Contains necessary messages to respond to the user.
 * Contains method that format the responses to more interactive.
 */
public class BotUI {

    private String botSpeak(String phrase) {
        return BOT_RESPONSE + phrase;
    }

    public static String userSpeak() {
        return USER_SAY;
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
        return botSpeak(HI);
    }
    /**
     * Returns bye message.
     * @return hello message from Duke.
     */
    public String sayBye() {
        return botSpeak(BYE);
    }
    /**
     * Returns the tasks contain in the TaskList.
     * @param taskList TaskList that store the tasks registered by user.
     * @return Numbering-formatted String of the tasks in the task list.
     */
    public String showList(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        if (list.size() == 0) {
            return botSpeak(NOTHING_IN_LIST);
        } else {
            StringBuilder listFormat = new StringBuilder();
            for (int i = 1; i <= list.size(); i++) {
                listFormat.append(i).append(". ").append(list.get(i - 1).toString());
                listFormat.append((i == list.size()) ? "" : "\n");
            }
            return botSpeak(SHOW_LIST_DESCRIPTION + listFormat);
        }
    }
    /**
     * Returns mark/unmark status of the task.
     * @param task Task that have been mark/unmark after the user command.
     * @return The String of completed task.
     */
    public String informMarkStatus(Task task) {
        return botSpeak((task.isDone()) ? String.format(MARK_SUCCESS, task)
               : String.format(UNMARK_SUCCESS, task));
    }
    /**
     * Returns task added into the TaskList.
     * @param task Task that have been added by user.
     * @param taskList the updated TaskList to show user the updated number of tasks in it.
     * @return The String of task added.
     */
    public String addStatus(TaskList taskList, Task task) {
        ArrayList<Task> list = taskList.getList();
        return botSpeak(String.format(ADD_SUCCESS, task, list.size()));
    }
    /**
     * Returns task removed from the TaskList.
     * @param task Task that have been removed by user.
     * @param taskList the updated TaskList to show user the updated number of tasks in it.
     * @return The String of task removed.
     */
    public String successRemoveTask(TaskList taskList, Task task) {
        ArrayList<Task> list = taskList.getList();
        return botSpeak(String.format(DELETE_SUCCESS, task, list.size()));
    }
    /**
     * Returns the list of tasks found in the TaskList according to user find command keyword.
     * @param foundTaskList the updated TaskList to show user the updated number of tasks in it.
     * @return The String task found.
     */
    public String taskFound(TaskList foundTaskList) {
        StringBuilder taskString = new StringBuilder();
        for (Task t : foundTaskList.getList()) {
            taskString.append(t).append("\n");
        }
        String responseString = taskString.substring(0, taskString.lastIndexOf("\n"));
        return botSpeak(FOUND_TASK + "\n" + responseString);
    }
    /**
     * Returns the message of no task is found.
     * @return String of "no task found" message.
     */
    public String taskNotFound() {
        return botSpeak(NOT_FOUND);
    }
    /**
     * Returns the format of the input that user should follow.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String invalidFormat() {
        return botSpeak(SHOW_FORMAT);
    }
    /**
     * Returns the message of task not exist.
     * Uses when user trying to modify task that does not exist in the TaskList.
     * @param taskList current taskList to show user the number of tasks in it.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String taskNotExist(TaskList taskList) {
        return botSpeak(String.format(TASK_NOT_EXIST,
                taskList.getList().size()));
    }
    /**
     * Returns the message of error when user type invalid mark/unmark/delete input format.
     * Informs user the these command require integer information.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String invalidCheckFormat() {
        return botSpeak(REQUIRE_INTEGER);
    }
    /**
     * Returns the message of error when user type invalid date input format.
     * Informs user the correct date format for the input.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String invalidDateFormat() {
        return botSpeak(INVALID_DATE_FORMAT);
    }
    /**
     * Returns the message of error when user type invalid response after anomaly raised.
     * Informs user to try again.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String unknownResponseDetected() {
        return botSpeak(UNKNOWN_RESPONSE);
    }

    /**
     * Returns the message of anomaly detection.
     * Informs user which two tasks has same task detail.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String sameTaskDetected(Task newTask, Task oldTask) {
        return botSpeak(String.format(SAME_TASK_DETECTED, newTask.toString(), oldTask.toString()));
    }

    /**
     * Returns the message of  detection.
     * Informs user which two tasks has close task timing.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String closeTimeDetected(Task newTask, Task oldTask) {
        return botSpeak(String.format(SAME_DATE_DETECTED, newTask.toString(), oldTask.toString()));
    }

    /**
     * Returns the message of command cancellation.
     * Informs user that the previous anomaly detected command is cancelled.
     * @return String of correct input format wrapped by the bot and user divider.
     */
    public String cancelCommand() {
        return botSpeak(COMMAND_CANCELED);
    }
}
