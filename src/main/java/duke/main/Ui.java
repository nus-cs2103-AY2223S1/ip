package duke.main;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * A class to handle user input and output for the user.
 */
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
    private static final String[] MISSING_ARGUMENT = {"I need an argument for this command..."};
    private static final String[] NOT_A_NUMBER = {"I don't think that's a number..."};
    private static final String[] CANNOT_SAVE = {"I can't seem to save the task list..."};

    private static final String[] INVALID_COMMAND = {"I don't quite get what you're saying..."};
    private static final String[] INVALID_INDEX = {"Umm... I think this task number does not exist."};
    private static final String[] INVALID_TIME = {"I'm sorry but I don't understand your time format."};

    private int messageStatus = 0;
    private Scanner sc = new Scanner(System.in);

    /**
     * Generates a message for showing total number of Task objects.
     * @param num Number to display.
     * @return The message for showing total number of Task objects.
     */
    private String getListSizeMsg(int num) {
        String[] listCount = {"I'm keeping track of " + num + " task(s) currently!"};
        return listCount[this.messageStatus];
    }

    /**
     * Prints the message in a certain format.
     * @param message The message to be printed.
     */
    private void outputMessage(String message) {
        String[] messageLines = message.split("\n");
        for (String line : messageLines) {
            System.out.println("Duke: " + line);
        }
    }

    /**
     * Reads the command from the user.
     * @return The read command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the greeting message.
     */
    public void showGreeting() {
        this.outputMessage(GREETING[this.messageStatus]);
    }

    /**
     * Shows the exit or bye message.
     */
    public void showBye() {
        this.outputMessage(BYE[this.messageStatus]);
    }

    /**
     * Shows the message when saving failed.
     */
    public void showSavingError() {
        this.outputMessage(CANNOT_SAVE[this.messageStatus]);
    }

    /**
     * Shows the message so the user knows it is time for them to enter a command.
     */
    public void showYou() {
        System.out.print("You: ");
    }

    /**
     * Shows the message for an invalid command.
     */
    public void showInvalidCommand() {
        this.outputMessage(INVALID_COMMAND[this.messageStatus]);
    }

    /**
     * Shows the message that the state of the Task object was already what the user wanted to set it to.
     * @param isDone
     */
    public void showAlreadyMarked(boolean isDone) {
        if (isDone) {
            this.outputMessage(PREV_DONE[this.messageStatus]);
        } else {
            this.outputMessage(PREV_UNDONE[this.messageStatus]);
        }
    }

    /**
     * Shows the message that the status of the Task object has changed.
     * @param task
     * @param isDone
     */
    public void showStatusChange(Task task, boolean isDone) {
        if (isDone) {
            this.outputMessage(MARK_DONE[this.messageStatus]);
        } else {
            this.outputMessage(MARK_UNDONE[this.messageStatus]);
        }
        this.outputMessage(task.toString());
    }

    /**
     * Shows the message that the index is invalid.
     */
    public void showInvalidIndex() {
        this.outputMessage(INVALID_INDEX[this.messageStatus]);
    }

    /**
     * Shows the message that the input is not a number.
     */
    public void showNotANumber() {
        this.outputMessage(NOT_A_NUMBER[this.messageStatus]);
    }

    /**
     * Shows the message for adding a Task object.
     * @param task Task object that is added.
     * @param size Size of the updated TaskList object.
     */
    public void showTaskAdded(Task task, int size) {
        this.outputMessage(ADD_LIST[this.messageStatus]);
        this.outputMessage(task.toString());
        this.outputMessage(this.getListSizeMsg(size));
    }

    /**
     * Shows the message for deleting a Task object.
     * @param task Task object that is deleted.
     * @param size Size of the updated TaskList object.
     */
    public void showTaskDeleted(Task task, int size) {
        this.outputMessage(DELETE_LIST[this.messageStatus]);
        this.outputMessage(task.toString());
        this.outputMessage(this.getListSizeMsg(size));
    }

    /**
     * Shows the message for missing index.
     */
    public void showMissingIndex() {
        this.outputMessage(MISSING_INDEX[this.messageStatus]);
    }

    /**
     * Shows the message for missing description.
     */
    public void showMissingDescription() {
        this.outputMessage(MISSING_DESCRIPTION[this.messageStatus]);
    }

    /**
     * Shows the message for missing time.
     */
    public void showMissingTime() {
        this.outputMessage(MISSING_TIME[this.messageStatus]);
    }

    /**
     * Shows the message for the whole TaskList object.
     * @param taskList ArrayList of Task object to be printed.
     */
    public void showList(ArrayList<Task> taskList) {
        this.outputMessage(BEFORE_LIST[this.messageStatus]);
        int number = 1;
        for (Task task : taskList) {
            this.outputMessage(Integer.toString(number) + ". " + task.toString());
            number++;
        }
        this.outputMessage(AFTER_LIST[this.messageStatus]);
    }

    /**
     * Shows the message for missing arguments.
     */
    public void showMissingArgument() {
        this.outputMessage(MISSING_ARGUMENT[this.messageStatus]);
    }

    /**
     * Shows the message for invalid time.
     */
    public void showInvalidTime() {
        this.outputMessage(INVALID_TIME[this.messageStatus]);
    }
}
