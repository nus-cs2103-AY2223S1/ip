package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;
    private final String divider = "------------------------";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(divider);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        System.out.println(divider);
    }

    public void showError (DukeException e) {
        System.out.println(e);
    }

//    public void close() {
//        System.out.println("Bye. Hope to see you again soon!");
//        in.close();
//    }

}
