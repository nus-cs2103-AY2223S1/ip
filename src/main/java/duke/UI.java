package duke;

import duke.task.Task;

import java.util.Scanner;

public class UI {
    private Scanner sc = new Scanner(System.in);
    
    public String readCommand() {
        return sc.nextLine();
    }
    
    public void showLine() {
        System.out.println("_______\n");
    }
    
    public void showError(Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }
    
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?\n");
    }
    
    public void addTask(Task task, int size) {
        System.out.print("Got it. I've added this task:\n" +
                " " + task + "\n" +
                "Now you have " + size + " tasks in the list.\n");
    }
    
    public void markTask(Task task, boolean isDone) {
        System.out.print(
                ( isDone ? "Nice! I've marked this task as done:\n " : "OK, I've marked this task as not done yet:\n ")
                        + task + "\n"
        );
    }
    
    public void deleteTask(Task task, int size) {
        System.out.print("Noted. I've removed this task:\n " +
                task + "\n" +
                "Now you have " + size + " tasks in the list.\n");
    }
    
    public static void printList(TaskList tasks) {
        System.out.print(tasks);
    }
}
