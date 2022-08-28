package unc;

import unc.task.Deadline;
import unc.task.Event;
import unc.task.Task;
import unc.task.Todo;

import java.util.Scanner;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {

        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome to UNC\n");
    }

    public void goodbye() {
        scanner.close();
        System.out.println("Bye");

    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void displayList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    public void addToDo (TaskList taskList, Todo todo) {
        System.out.println("added: \n " + todo + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    public void addEvent (TaskList taskList, Event event) {
        System.out.println("added: \n " + event + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    public void addDeadline (TaskList taskList, Deadline deadline) {
        System.out.println("added: \n " + deadline + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    public void mark (TaskList taskList, int index) {
        System.out.println("Marked as done: \n" + taskList.get(index));
    }

    public void unmark (TaskList taskList, int index) {
        System.out.println("Marked as not done: \n" + taskList.get(index));
    }

    public void delete (Task task, int size) {
        System.out.println("Deleted: \n" + task + "\nNow you have " + size +
                " tasks on the list.");
    }

    public void displayFoundList(TaskList taskList) {
        displayList(taskList);
    }
}
