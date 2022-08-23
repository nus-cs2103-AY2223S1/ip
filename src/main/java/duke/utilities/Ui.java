package duke.utilities;

import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    /** Line used for formatting. */
    private static String line = "----------------------------------------";

    /** Scanner object to read from the system input. */
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printDukeOpening() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "");
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);
    }

    public void printDukeClosing() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void printDukeException(DukeException e) {
        System.out.println(line);
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(line);
    }

    public void printIoException(IOException e) {
        System.out.println(line);
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(line);
    }

    public void printDateTimeParseException() {
        System.out.println(line);
        System.out.println("All dates must be in the format (yyyy-MM-dd HH:mm)!");
        System.out.println(line);
    }

    public void printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            System.out.println((i + 1) + "." + current.toString());
        }
        System.out.println(line);
    }

    /**
     * Method to print the matching tasks after filtering for tasks that contains
     * the query term.
     *
     * @param taskList The input task list to print to the user.
     */
    public void printMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            System.out.println((i + 1) + "." + current.toString());
        }
        System.out.println(line);
    }

    public void printAddTask(Task task, TaskList taskList) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
        System.out.println(line);
    }

    public void printDeleteTask(Task task, TaskList taskList) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
        System.out.println(line);
    }

    public void printChangeTaskStatus(Task task, boolean isDone) {
        System.out.println(line);
        if (isDone) {
            System.out.println(("Nice! I've marked this task as done:"));
            System.out.println("  " + task.toString());
        } else {
            System.out.println(("OK, I've marked this task as not done yet:"));
            System.out.println("  " + task.toString());
        }
        System.out.println(line);
    }

    public void closeScanner() {
        this.sc.close();
    }

    public String nextLine() {
        return this.sc.nextLine();
    }
}
