package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final static String LINE = "____________________________________________________________";
    private final static String INDENTATION = "   ";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(INDENTATION + LINE);
    }

    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }


    public void showWelcome() {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Hello! I'm duke.Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + LINE);
    }

    public void showGoodByeMessage() {
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        sc.close();
    }

    public void showListMessage(TaskList taskList) {
        int numOfTasks = taskList.getSize();
        if (numOfTasks == 0) {
            System.out.println(INDENTATION + "You do not have any tasks in your list right now.");
        }
        System.out.printf(INDENTATION + "Here %s the task%s in your list:\n",
                numOfTasks > 1 ? "are" : "is", numOfTasks > 1 ? "s" : "");
        for (int i = 1; i <= numOfTasks; i++) {
            System.out.println(INDENTATION + String.valueOf(i) + ". " +
                    taskList.getTask(i).toString());
        }
    }

    public void showMarkMessage(Task task) {
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + task.toString());
    }

    public void showUnmarkMessage(Task task) {
        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
        System.out.println(INDENTATION + INDENTATION + task.toString());
    }

    public void showAddTaskMessage(TaskList taskList, Task task) {
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + "added: " + task.toString());
        System.out.println(INDENTATION +
                "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
    }

    public void showDeleteMessage(TaskList taskList, Task task) {
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + INDENTATION + task.toString());
        System.out.println(INDENTATION +
                "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
    }

    public void showError(String message) {
        System.out.println(INDENTATION + message);
    }

    public void showLoadingError() {
        System.out.println(INDENTATION + "There was no saved data found.");
    }

    public void showOutOfBoundsError() {
        System.out.println(INDENTATION + "Please choose a index provided within the list of tasks");
    }

    public void showIllegalArgumentError() {
        System.out.println(INDENTATION + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
