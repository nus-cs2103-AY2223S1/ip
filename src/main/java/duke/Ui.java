package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private final String line = "--------------------------------------------------------------------------------\n";
    //private TaskList taskList;

    public Ui() {
        scanner = new Scanner(System.in);
        //taskList = new TaskList();
    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line + "Hello! I'm Duke");
        System.out.println("What tasks do you have to do?\n" + line);
    }

    public void printGoodbyeMessage() {
        System.out.println("Bye! See you soon!");
    }

    public void printList(ArrayList<Task> tasks) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i).toString());
        }
        drawLine();
    }

    public void printDone(Task task) {
        drawLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
        drawLine();
    }

    public void printUndone(Task task) {
        drawLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
        drawLine();
    }

    public void printTodo(Task task) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    /**public void printEvent(Task task) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    public void printDeadline(Task task) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }*/

    public void printDelete(Task task) {
        drawLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        drawLine();
    }

    public void printTasksLeft(int num) {
        System.out.println("Now you have " + num + " tasks in the list.");
        drawLine();
    }

    /**public void printErrorMsg(String str) {
        System.out.println(str);
    }*/

    public void printLoadingError() {
        System.out.println("Unable to load file");
    }

    public void drawLine() {
        System.out.println(line);
    }

}
