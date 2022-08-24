package Duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void printWelcome() {
        System.out.println("Hello! I'm Duke \n What can I do for you?");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void printAddTask(Task t, int size) {
        System.out.printf("Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.\n",
                t, size);
    }

    public void printDeleteTask(Task t, int size) {
        System.out.printf("Noted. I've removed this task: \n  %s \nNow you have %d tasks in the list.\n",
                t, size);
    }

    public void printMark(Task t) {
        System.out.printf("Nice! I've marked this task as done: \n  %s \n", t);
    }

    public void printUnMark(Task t) {
        System.out.printf("OK, I've marked this task as not done yet: \n %s \n", t);
    }

    public void printMsg(String s) {
        System.out.println(s);
    }
}