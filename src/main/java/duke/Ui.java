package duke;

import java.util.Scanner;
public class Ui {
    private Scanner sc;
    private static final String HELLO = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE = "Bye! Hope to see you again soon!";

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void sayHello() {
        System.out.printf("%s", HELLO);
    }

    public static void sayBye() {
        System.out.printf("%s", BYE);
    }

    public static void showLoadingError() {
        System.out.println("loading error");
    }

}
