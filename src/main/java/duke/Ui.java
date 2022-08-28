package duke;

import java.util.Scanner;

/**
 * Class to deal with user interface
 */
public class Ui {
    private static final String HELLO = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE = "Bye! Hope to see you again soon!";
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public void sayHello() {
        System.out.printf("%s", HELLO);
    }

    public void sayBye() {
        System.out.printf("%s", BYE);
    }

    public void showLoadingError() {
        System.out.println("loading error");
    }

}
