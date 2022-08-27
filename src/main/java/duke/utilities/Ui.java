package duke.utilities;

import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static String line = "--------------------";

    private Scanner s;

    public Ui() {
        this.s = new Scanner(System.in);
    }

    public void printDukeOpening() {
        String name = "Turtle";
        System.out.println("Hello I am " + name + "!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void printDukeClosing() {
        System.out.println("Goodbye!");
        System.out.println(line);
    }

    public void printDukeException(DukeException e) {
        System.out.println("OOPS!!!" + e.getMessage());
        System.out.println(line);
    }

    public void printIoException(IOException e) {
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(line);
    }

    public void printDateTimeParseException() {
        System.out.println("All dates must be in the format (yyyy-MM-dd HH:mm)!");
        System.out.println(line);
    }

    public void printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println("Here are the tasks in your list: ");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(line);
    }

    public void printAddTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
        System.out.println(line);
    }

    public void printChangeTaskStatus(Task task, boolean isDone) {
        if (isDone) {
            System.out.println(("Nice! I've marked this task as done:"));
            System.out.println(task.toString());
        } else {
            System.out.println(("OK, I've marked this task as not done yet:"));
            System.out.println(task.toString());
        }
        System.out.println(line);
    }

    public void printDeleteTask(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
        System.out.println(line);
    }

    public void showLoadingError() { System.out.println("Loading ...");}

    public void closeScanner() {
        this.s.close();
    }

    public String nextLine() {
        return this.s.nextLine();
    }
}
