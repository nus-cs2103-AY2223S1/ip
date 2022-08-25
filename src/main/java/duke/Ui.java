package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {

    private Scanner sc = new Scanner(System.in);
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    public void showWelcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke \nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("_______");
    }
    public void showLoadingError() {
        System.out.println("File not found.");
    }


    public void showDelete(Task t, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        if (taskList.getTasksNumber() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.getTasksNumber() + " tasks in the list.");
        }

    }

    public void showAddTask(Task t, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        if (taskList.getTasksNumber() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.getTasksNumber() + " tasks in the list.");
        }
    }

    public void showMark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    public void showUnmark(Task t) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(t);
    }

    public void showTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println((i + 1) + "." + currTask.toString());
        }
    }

    public void showGetDate(ArrayList<Task> onDateTasks) {
        for (Task t : onDateTasks) {
            System.out.println(t);
        }
        int count = onDateTasks.size();
        if (count == 0) {
            System.out.println("YAY! You have no deadlines/events on this day.");
        } else if (onDateTasks.size() == 1) {
            System.out.println("Shag man. You have " + count + " deadline/event on this day.");
        } else {
            System.out.println("Shag man. You have " + count + " deadlines/events on this day.");
        }
    }

    /**
     * Displays error message.
     * @param error The description for the error.
     */
    public void showError(String error) {
        System.out.println("☹ OOPS!!!" + error);
    }

    public void showBye() {
        System.out.println("Bye, hope to see you again next time!");
    }

}
