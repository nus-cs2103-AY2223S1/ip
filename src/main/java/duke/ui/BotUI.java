package duke.ui;

import static duke.common.Messages.MESSAGE_ADD_SUCCESS;
import static duke.common.Messages.MESSAGE_BOT_DIVIDER;
import static duke.common.Messages.MESSAGE_BYE;
import static duke.common.Messages.MESSAGE_DELETE_SUCCESS;
import static duke.common.Messages.MESSAGE_HI;
import static duke.common.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static duke.common.Messages.MESSAGE_MARK_SUCCESS;
import static duke.common.Messages.MESSAGE_NOTHING_IN_LIST;
import static duke.common.Messages.MESSAGE_REQUIRE_INTEGER;
import static duke.common.Messages.MESSAGE_SHOW_FORMAT;
import static duke.common.Messages.MESSAGE_TASK_NOT_EXIST;
import static duke.common.Messages.MESSAGE_UNMARK_SUCCESS;
import static duke.common.Messages.MESSAGE_USER_DIVIDER;

import java.util.ArrayList;
import java.util.Scanner;

import duke.storage.TaskRecords;
import duke.task.Task;

public class BotUI {

    public String botSpeak(String phrase) {
        return this.botDivider() + phrase + "\n"
                + this.userDivider();
    }

    public String readCommand() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }
    public String sayHello() {
        return botSpeak(MESSAGE_HI);
    }

    public String sayBye() {
        return MESSAGE_BYE;
    }

    public String botDivider() {
        return MESSAGE_BOT_DIVIDER;
    }

    public String userDivider() {
        return MESSAGE_USER_DIVIDER;
    }

    public String showList(TaskRecords taskList) {
        ArrayList<Task> lst = taskList.getList();
        if (lst.size() == 0) {
            return botSpeak(MESSAGE_NOTHING_IN_LIST);
        }
        StringBuilder lstFormat = new StringBuilder();
        for(int i = 1; i <= lst.size(); i++) {
            lstFormat.append(i).append(". ").append(lst.get(i - 1).toString());
            lstFormat.append((i == lst.size()) ? "" : "\n");
        }
        return botSpeak(lstFormat.toString());
    }

    public String informMarkStatus(Task task) {
        return botSpeak((task.isDone()) ? String.format(MESSAGE_MARK_SUCCESS, task)
               : String.format(MESSAGE_UNMARK_SUCCESS, task));
    }

    public String addStatus(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        return botSpeak(String.format(MESSAGE_ADD_SUCCESS, task, lst.size()));
    }

    public String successRemoved(TaskRecords taskList, Task task) {
        ArrayList<Task> lst = taskList.getList();
        return botSpeak(String.format(MESSAGE_DELETE_SUCCESS, task, lst.size()));
    }

    public String invalidFormat() {
        return botSpeak(MESSAGE_SHOW_FORMAT);
    }

    public String taskNotExist(TaskRecords taskList) {
        return botSpeak(String.format(MESSAGE_TASK_NOT_EXIST,
                taskList.getList().size()));
    }

    public String invalidCheckFormat() {
        return botSpeak(MESSAGE_REQUIRE_INTEGER);
    }

    public String invalidDateFormat() {
        return botSpeak(MESSAGE_INVALID_DATE_FORMAT);
    }
}
