package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String[] GREETING = {"Hi, I'm Duke.\nWhat can I do for you?\nI'll do my best! :)"};
    private static final String[] BYE = {"NOOOOOO... Don't send me back to the void! T_T"};

    private static final String[] BEFORE_LIST = {"Here's your task list!"};
    private static final String[] AFTER_LIST = {"I'm useful right?"};
    private static final String[] ADD_LIST = {"Yep! I've added this task: "};
    private static final String[] DELETE_LIST = {"OK! I've removed this task: "};

    private static final String[] MARK_DONE = {"I've marked this task as done:"};
    private static final String[] MARK_UNDONE = {"I've marked this task as not done:"};
    private static final String[] PREV_DONE = {"Just to let you know this task was already done previously."};
    private static final String[] PREV_UNDONE = {"Just to let you know this task was already not done previously."};

    private static final String[] MISSING_TIME = {"I think you need a time for this."};
    private static final String[] MISSING_INDEX = {"Please provide an index in the command."};
    private static final String[] MISSING_DESCRIPTION = {"I need a description for the task..."};
    private static final String[] NOT_A_NUMBER = {"I don't think that's a number..."};
    private static final String[] CANNOT_SAVE = {"I can't seem to save the task list..."};

    private static final String[] INVALID_COMMAND = {"I don't quite get what you're saying..."};
    private static final String[] INVALID_INDEX = {"Umm... I think this task number does not exist."};
    private static final String[] INVALID_TIME = {"I'm sorry but I don't understand your time format."};

    private int messageStatus = 0;
    private Scanner sc = new Scanner(System.in);

    private String getListSizeMsg(int num) {
        String[] listCount = {"I'm keeping track of " + num + " task(s) currently!"};
        return listCount[this.messageStatus];
    }

    private void outputMessage(String message) {
        String[] messageLines = message.split("\n");
        for (String line : messageLines) {
            System.out.println("Duke: " + line);
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showGreeting() {
        this.outputMessage(GREETING[this.messageStatus]);
    }

    public void showBye() {
        this.outputMessage(BYE[this.messageStatus]);
    }

    public void showSavingError() {
        this.outputMessage(CANNOT_SAVE[this.messageStatus]);
    }

    public void showYou() {
        System.out.print("You: ");
    }

    public void showInvalidCommand() {
        this.outputMessage(INVALID_COMMAND[this.messageStatus]);
    }

    public void showAlreadyMarked(boolean isDone) {
        if (isDone) {
            this.outputMessage(PREV_DONE[this.messageStatus]);
        } else {
            this.outputMessage(PREV_UNDONE[this.messageStatus]);
        }
    }

    public void showStatusChange(Task task, boolean isDone) {
        if (isDone) {
            this.outputMessage(MARK_DONE[this.messageStatus]);
        } else {
            this.outputMessage(MARK_UNDONE[this.messageStatus]);
        }
        this.outputMessage(task.toString());
    }

    public void showInvalidIndex() {
        this.outputMessage(INVALID_INDEX[this.messageStatus]);
    }

    public void showNotANumber() {
        this.outputMessage(NOT_A_NUMBER[this.messageStatus]);
    }

    public void showTaskAdded(Task task, int size) {
        this.outputMessage(ADD_LIST[this.messageStatus]);
        this.outputMessage(task.toString());
        this.outputMessage(this.getListSizeMsg(size));
    }

    public void showTaskDeleted(Task task, int size) {
        this.outputMessage(DELETE_LIST[this.messageStatus]);
        this.outputMessage(task.toString());
        this.outputMessage(this.getListSizeMsg(size));
    }

    public void showMissingIndex() {
        this.outputMessage(MISSING_INDEX[this.messageStatus]);
    }

    public void showMissingDescription() {
        this.outputMessage(MISSING_DESCRIPTION[this.messageStatus]);
    }

    public void showMissingTime() {
        this.outputMessage(MISSING_TIME[this.messageStatus]);
    }

    public void showList(ArrayList<Task> taskList) {
        this.outputMessage(BEFORE_LIST[this.messageStatus]);
        int number = 1;
        for (Task task : taskList) {
            this.outputMessage(Integer.toString(number) + ". " + task.toString());
            number++;
        }
        this.outputMessage(AFTER_LIST[this.messageStatus]);
    }

    public void showInvalidTime() {
        this.outputMessage(INVALID_TIME[this.messageStatus]);
    }
}
