package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String GetUserInput() {
        return this.scanner.nextLine();
    }

    public void displayHello() {
        displaySeparator();
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
        displaySeparator();
    }

    public void displayLoading() {
        System.out.println("Loading previously saved tasks");
    }

    public void displayLoadingSuccess() {
        System.out.println("Successfully loaded");
    }

    public void displayLoadingError() {
        System.out.println("OOPS!!! Couldn't find any saved tasks");
    }

    public void displayTaskList(ArrayList<Task> tasks) {
        int size = tasks.size();
        displaySeparator();
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).TaskInfo());
        }
        displaySeparator();
    }

    public void displayMarkTask(Task task) {
        displaySeparator();
        System.out.println("Nice! I've marked this task as done:\n " + task.TaskInfo());
        displaySeparator();
    }

    public void displayUnmarkTask(Task task) {
        displaySeparator();
        System.out.println("Ok, I've marked this task as not done yet:\n " + task.TaskInfo());
        displaySeparator();
    }

    public void displayAddTask(Task task) {
        displaySeparator();
        System.out.println("Got it. I've added this task:\n " + task.TaskInfo());

    }

    public void displayDeleteTask(Task task) {
        displaySeparator();
        System.out.println("Noted. I've removed this task:\n " + task.TaskInfo());
    }

    public void displayBye() {
        displaySeparator();
        System.out.println("Bye. Hope to see you again soon!");
        displaySeparator();
    }

    public void displayNumOfTasks(int taskSize) {
        if (taskSize == 1) {
            System.out.println("Now you have " + taskSize + " task in the list.");
        } else {
            System.out.println("Now you have " + taskSize + " tasks in the list.");
        }
    }

    public void displayInvalidTaskIndex() {
        displaySeparator();
        System.out.println("There is no task at this index");
        displaySeparator();
    }

    public void displaySeparator() {
        System.out.println("--------------------------------");
    }

    public void displayMatchedTasks(ArrayList<Task> tasks) {
        int size = tasks.size();
        displaySeparator();
        System.out.println("Here are the matching tasks in your list");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).TaskInfo());
        }
        displaySeparator();
    }
}
