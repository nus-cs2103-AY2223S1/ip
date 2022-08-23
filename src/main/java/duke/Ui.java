package duke;

import java.util.Scanner;

public class Ui {

    public static final String initText = "Hello! I'm Duke\n    What can I do for you?";
    public static final String endText = "Bye bye! Hope to see you again soon!";

    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public String readLine() {
        return scan.nextLine();
    }

    public void printWelcome() {
        System.out.println("Hello from\n" + logo);
        printMsg(initText);
    }

    public void printBye() {
        printMsg(endText);
    }

    public void printMark(Task t) {
        t.mark();
        printMsg("Nice! I've marked this task as done :)\n      "
                + t.toString());
    }

    public void printUnmark(Task t) {
        t.unmark();
        printMsg("ok I mark this task as not done yet... \n      "
                + t.toString());
    }

    public void printAddTask(Task t, int n) {
        String note = "Now you have " + n + " tasks in the list.";
        printMsg("Got it, I've added this task:\n      " + t.toString()
                + "\n    " + note);
    }

    public void printDeleteTask(Task t, int n) {
        String noteUpdated = "Now you have " + n + " tasks in the list.";
        printMsg("Noted. I've deleted this task:\n      " + t.toString()
                + "\n    " + noteUpdated);
    }

    public void showLoadingError() {
        printMsg("error!");
    }

    public static void printMsg (String str){
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + str);
        System.out.println("  ____________________________________________________________");
    }
}
