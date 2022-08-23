import java.util.Scanner;

/**
 * This class deals with the interactions with the user
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(Constants.LINE + "\nHello! I'm " + Constants.NAME +
                "\nWhat can I do for you?\n" + Constants.LINE);
    }

    public void showLine() {
        System.out.println(Constants.LINE);
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you soon again!");
        scanner.close();
    }

    public void printAddTask(Task t, int size) {
        System.out.printf("Got it. I've added this task: \n %s \nNow you have %d tasks in the list.\n", t.toString(), size);
    }

    public void printMarkTask(Task t) {
        System.out.printf("Nice! I've marked this task as done: \n%s \n", t.toString());
    }

    public void printUnmarkTask(Task t) {
        System.out.printf("OK, I've marked this task as not done yet: \n %s \n", t.toString());
    }

    public void printDeleteTask(Task t, int size) {
        System.out.printf("Noted. I've removed this task: \n%s " +
                "\nNow you have %d tasks in the list.\n", t.toString(), size);
    }

    public void showError(String s) {
        System.out.println(s);
    }
}
