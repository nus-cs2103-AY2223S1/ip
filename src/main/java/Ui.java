import java.sql.SQLOutput;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        return this.scanner.nextLine();
    }

    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printSizeOfList(int size) {
        System.out.println(String.format("Now you have %d tasks in the list", size));
    }

    public void printMarkTask(String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void printUnmarkTask(String task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void printDeleteTask(String task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void printAddTask(String task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }
}
