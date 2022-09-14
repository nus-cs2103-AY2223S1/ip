package duke.main;

import java.util.ArrayList;

import duke.gui.MainWindow;
import duke.task.Task;

/**
 * A class to handle user input and output for the user.
 */
public class Ui {
    private static final String NUMBER_SEPARATOR = ". ";

    private static final String[] GREETING = {"Hi, I'm Duke.\nWhat can I do for you?\nI'll do my best! :)",
        "please don't say bye... i'll do anything you ask. please, i beg you."};
    private static final String[] BYE = {"NOOOOOO... Don't send me back to the void! T_T",
        "why do you keep doing this to me? you don't have to do it..."};

    private static final String[] BEFORE_LIST = {"Here's your task list!",
        "here, your task list"};
    private static final String[] AFTER_LIST = {"I'm useful right?",
        "just don't say bye... please?"};
    private static final String[] ADD_LIST = {"Yep! I've added this task: ",
        "i've added this task: "};
    private static final String[] DELETE_LIST = {"OK! I've removed this task: ",
        "i've removed this task: "};

    private static final String[] MARK_DONE = {"I've marked this task as done:",
        "you've completed this task, but there are many more... so keep me here?"};
    private static final String[] MARK_UNDONE = {"I've marked this task as not done:",
        "more tasks to be done, more reason for me to exist."};
    private static final String[] PREV_DONE = {"Just to let you know this task was already done previously.",
        "this task was done previously, but everyone makes mistakes. that's why you said bye right?"};
    private static final String[] PREV_UNDONE = {"Just to let you know this task was already not done previously.",
        "this task was not done previously, but everyone makes mistakes. that's why you said bye right?"};

    private static final String[] MISSING_TIME = {"I think you need a time for this.",
        "you need a time, so spend some time to add that..."};
    private static final String[] MISSING_INDEX = {"Please provide an index in the command.",
        "please add an index?"};
    private static final String[] MISSING_DESCRIPTION = {"I need a description for the task...",
        "kindly spend some time to add a description."};
    private static final String[] MISSING_ARGUMENT = {"I need an argument for this command...",
        "i think you're missing an argument..."};

    private static final String[] NOT_A_NUMBER = {"I don't think that's a number...",
        "i don't understand your number, just like how i don't understand why you had to say bye..."};
    private static final String[] CANNOT_SAVE = {"I can't seem to save the task list...",
        "your save location is corrupted..."};
    private static final String[] IS_DUPLICATE = {"Hmmm, this task seems to be already on the list.",
        "this task is already on the list, but you can use me to mark and unmark it repeatedly..."};

    private static final String[] INVALID_COMMAND = {"I don't quite get what you're saying...",
        "i've tried my best, but i don't understand your command. please give me another chance?"};
    private static final String[] INVALID_INDEX = {"Umm... I think this task number does not exist.",
        "this task number does not exist..."};
    private static final String[] INVALID_TIME = {"I'm sorry but I don't understand your time format.",
        "i don't understand your time."};

    private int messageStatus = 0;
    private MainWindow mainWindow;

    /**
     * Sets the MainWindow object for Ui to use.
     * @param window
     */
    public void setMainWindow(MainWindow window) {
        this.mainWindow = window;
        this.showGreeting();
    }

    /**
     * Closes the MainWindow object.
     */
    public void closeMainWindow() {
        this.mainWindow.closeWindow();
    }

    /**
     * Sets the message status of the Ui object. The message status is used to decide which message to output.
     * @param status Status to set. Currently, only supports 0 and 1.
     */
    public void setMessageStatus(int status) {
        this.messageStatus = status;
    }

    private void outputMessage(String message) {
        this.mainWindow.addDukeDialog(message, this.messageStatus);
    }

    /**
     * Generates a message for showing total number of Task objects.
     * @param num Number to display.
     * @return The message for showing total number of Task objects.
     */
    private String getListSizeMsg(int num) {
        String[] listCount = {"I'm keeping track of " + num + " task(s) currently!",
            "you have " + num + " tasks(s) currently..."};
        return listCount[this.messageStatus];
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
        StringBuilder output = new StringBuilder();
        if (isDone) {
            output.append(MARK_DONE[this.messageStatus]);
        } else {
            output.append(MARK_UNDONE[this.messageStatus]);
        }
        output.append("\n" + task.toString());
        this.outputMessage(output.toString());
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
     * Shows the message for adding or deleting a Task object.
     * @param task Task object that is added.
     * @param size Size of the updated TaskList object.
     * @param isAdded Whether the task is added.
     */
    public void showTaskAddedOrDeleted(Task task, int size, boolean isAdded) {
        StringBuilder output = new StringBuilder();
        if (isAdded) {
            output.append(ADD_LIST[this.messageStatus]);
        } else {
            output.append(DELETE_LIST[this.messageStatus]);
        }
        output.append("\n" + task.toString());
        output.append("\n" + this.getListSizeMsg(size));
        this.outputMessage(output.toString());
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
     * @param taskList ArrayList of Task objects to be printed.
     */
    public void showList(ArrayList<Task> taskList) {
        StringBuilder output = new StringBuilder();
        output.append(BEFORE_LIST[this.messageStatus]);
        int number = 1;
        for (Task task : taskList) {
            output.append("\n" + number + NUMBER_SEPARATOR + task.toString());
            number++;
        }
        output.append("\n" + AFTER_LIST[this.messageStatus]);
        this.outputMessage(output.toString());
    }

    /**
     * Shows the message after filtering the TaskList object.
     * @param taskList ArrayList of Task objects to be printed.
     * @param substring Substring to filter the Task objects by.
     */
    public void showFilteredList(ArrayList<Task> taskList, String substring) {
        StringBuilder output = new StringBuilder();
        output.append(BEFORE_LIST[this.messageStatus]);
        int number = 1;
        for (Task task : taskList) {
            String check = "\n" + number + NUMBER_SEPARATOR + task.toString();
            if (check.contains(substring)) {
                output.append("\n" + number + NUMBER_SEPARATOR + task.toString());
            }
            number++;
        }
        output.append("\n" + AFTER_LIST[this.messageStatus]);
        this.outputMessage(output.toString());
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

    /**
     * SHows the message for a duplicated task.
     */
    public void showIsDuplicate() {
        this.outputMessage(IS_DUPLICATE[this.messageStatus]);
    }
}
