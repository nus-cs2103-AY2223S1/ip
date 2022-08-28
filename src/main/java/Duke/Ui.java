package Duke;

import Tasks.Task;

import java.util.Scanner;

/**
 * Handles all the text Ui Duke responds
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String printWelcome() {
        String s = "Hello! I'm Duke.Duke \nWhat can I do for you?";
        System.out.println(s);
        return s;
    }

    public String printBye() {
        String s = "Bye. Hope to see you soon again!";
        System.out.println(s);
        scanner.close();
        return s;
    }

    public String printAddTask(Task t, int size) {
        String s = String.format("Got it. I've added this task: \n %s \n Now you have %d tasks left.%n",
                t.toString(), size);
        System.out.printf(s);
        return s;
    }

    public String printDeleteTask(Task t, int size) {
        String s = String.format("Noted. I've removed this task: \n%s \nNow you have %d tasks left.%n",
                t.toString(), size);
        System.out.printf(s);
        return s;
    }

    public String printMark(Task t) {
        String s = String.format("Nice! I've marked this task as done: \n%s \n", t.toString());
        System.out.printf(s);
        return s;
    }

    public String printUnmark(Task t) {
        String s = String.format("OK, I've marked this task as not done yet: \n %s \n", t.toString());
        System.out.printf(s);
        return s;
    }

    public String printMsg(String s) {
        System.out.println(s);
        return s;
    }
}
