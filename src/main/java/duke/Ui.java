package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    
    private final Scanner scanner;
    
    public Ui() {
        scanner = new Scanner(System.in);
        welcome();
    }
    
    protected void welcome() {
        System.out.println("Hello! I'm SoCCat\nWhat can I do for you?");
    }

    protected void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    protected void printAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list: ");
        loopThroughTasks(tasks);
    }
    
    protected void printMatchingTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        loopThroughTasks(tasks);
    }
    
    protected void loopThroughTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }
    
    protected String numberOfTasks(int size) {
        return "Now you have " + size + (size < 2 ? " task" : " tasks") + " in your list.";
    }
    
    protected void printTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + task + "\n" + numberOfTasks(size));
    }
    
    protected void printTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task: \n" + task + "\n" + numberOfTasks(size));
    }
    
}
