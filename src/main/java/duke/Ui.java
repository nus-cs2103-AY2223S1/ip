package duke;

import duke.task.Task;

import java.util.List;

public class Ui {

    private static String INDENT = "     ";
    private static String LINE = "    _______________________________________________________";

    public Ui() {
    }

    private void printWithFormat(String text) {
        System.out.println(LINE
                + "\n"
                + INDENT
                + text
                + "\n"
                + LINE);
    }

    public void printWelcomeMsg() {
        String welcomeMsg = "Hello! I'm Duke\n\n     "
                + "I'm capable of doing the following:\n     "
                + "   todo <your task>\n     "
                + "   deadline <your deadline> /by <2022-01-02>\n     "
                + "   event <your event> /at <2022-01-02> <2359>\n     "
                + "   find <content>\n     "
                + "   bye (to exit the program)\n\n     "
                + "What can I do for you?";
        printWithFormat(welcomeMsg);
    }

    public void printEndingMsg() {
        String endingMsg = "Bye. Hope to see you again soon!";
        printWithFormat(endingMsg);
    }

    public void printTaskList(TaskList list) {
        String text = "Here are the tasks in your list:\n     " + list.getAllTask();
        printWithFormat(text);
    }

    public void printTaskMarkedMsg(Task task) {
        String markedMsg = "Nice! I've marked this task as done:\n     "
                + task;
        printWithFormat(markedMsg);
    }

    public void printTaskUnmarkedMsg(Task task) {
        String unmarkedMsg = "OK, I've marked this task as not done yet:\n     "
                + task;
        printWithFormat(unmarkedMsg);
    }

    public void printDeleteTaskMsg(Task task, TaskList listOfTasks) {
        printWithFormat("Noted. I've removed this task:\n     "
                + task
                +"\n     Now you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.");
    }

    public void printAddTaskMsg(Task task, TaskList listOfTasks) {
        printWithFormat("Got it, I've added this task:\n       "
                + task.toString()
                + "\n     Now you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.");
    }

    public void showLoadingError() {
        printWithFormat("There is some problem loading your task(s) ☹");
    }

    public void showInvalidCommandError() {
        printWithFormat("Invalid command!");
    }

    public void showNoDescriptionError(String userInput) {
        printWithFormat("☹ OOPS!!! The description of a "
                + userInput
                + " cannot be empty.");
    }

    public void printFilteredList(String filteredListString) {
        String text = "Here are the matching tasks in your list:\n     "
                + filteredListString;
        printWithFormat(text);
    }
}
