package duke;

import java.util.ArrayList;

public class Ui {
    public void printMessage(String msg) {
        String border = "____________________________________________________________\n";
        System.out.println(border + msg + "\n" + border);
    }

    public void printStartupMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startupMsg = "Hello! I'm duke.Duke\n" + "What can I do for you?";
        printMessage(startupMsg);
    }

    public void printExitMessage() {
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    public void listTasks(ArrayList<Task> tasks) {
        String reply = "Here are the tasks in your list:";
        printMessage(enumerateTasks(tasks, reply));
    }

    public void listFoundTasks(ArrayList<Task> tasks) {
        String reply = "Here are the matching tasks in your list:";
        printMessage(enumerateTasks(tasks, reply));
    }

    private String enumerateTasks(ArrayList<Task> tasks, String str) {
        int pointer = 1;
        for (Task task : tasks) {
            str += "\n" + pointer + "." + task;
            pointer++;
        }
        return str;
    }

    public void printMarkedTask(Task task) {
        printMessage("Nice! I've marked this task as done:\n" + task);
    }

    public void printUnmarkedTask(Task task) {
        printMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    private String getTaskCountReply(int count) {
        return "Now you have " + count + " task(s) in the list.";
    }

    public void printRemovedTask(Task removedTask, int tasksCountLeft) {
        String msg = "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + getTaskCountReply(tasksCountLeft);
        printMessage(msg);
    }

    public void printAddedTask(Task task, int tasksCountLeft) {
        String msg = "Got it. I've added this task:\n"
                + task + "\n"
                + getTaskCountReply(tasksCountLeft);
        printMessage(msg);
    }

    public void printBannedCharacterInputResponse(String s) {
        printMessage("☹ Woah there!!! Your input contains a \"" + s
                + "\" character which is not allowed!!");
    }

    public void printInvalidInputResponse() {
        printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printMissingInputResponse(String s) {
        printMessage("☹ OOPS!!! The description after a \""
                + s + "\" is missing or incomplete!!");
    }

    public void printInputIndexOutOfBoundsResponse(String cmd, String inputNum) {
        printMessage("☹ OOPS!!! You tried to " + cmd + " task " + inputNum
                + " but it doesn't exist in the list!");
    }

    public void printDateTimeErrorResponse() {
        printMessage("☹ OOPS!!! I can't recognise the date you just inputted :-(\n"
                + "Dates should be inputted in a 'YYYY-MM-DD HH:MM' format, and events should have 2 dates.");
    }
}
