package duke;
import java.util.Scanner;
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void greeting() {
        String initMessage = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(LINE + initMessage + LINE);
    }

    public static void exit() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + goodbyeMessage + LINE);
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void showLoadingError() {
        showLine();
        System.out.println("Error: Cannot load file!");
        showLine();
    }
}
