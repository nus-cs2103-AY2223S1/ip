/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import duke.task.*;

import java.util.Scanner;

/**
 * public class Ui to handle text shown to users when command is typed in to Duke.
 */
public class Ui {

    public static final String initText = "Hello! I'm Duke\n    What can I do for you?";
    public static final String endText = "Bye bye! Hope to see you again soon!";

    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scan;

    /**
     * public class constructor for Ui.
     */
    public Ui() {
        this.scan = new Scanner(System.in);
    }

    /**
     * class method to read next line in file.
     * @return
     */
    public String readLine() {
        return scan.nextLine();
    }

    /**
     * class method to print welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello from\n" + logo);
        printMessage(initText);
    }

    /**
     * class method to print bye message.
     */
    public void printBye() {
        printMessage(endText);
    }

    /**
     * class method to print marked message.
     */
    public void printMark(Task t) {
        t.mark();
        printMessage("Nice! I've marked this task as done :)\n      "
                + t.toString());
    }

    /**
     * class method to print unmarked message.
     */
    public void printUnmark(Task t) {
        t.unmark();
        printMessage("ok I mark this task as not done yet... \n      "
                + t.toString());
    }

    /**
     * class method to print add Task message.
     */
    public void printAddTask(Task t, int n) {
        String note = "Now you have " + n + " tasks in the list.";
        printMessage("Got it, I've added this task:\n      " + t.toString()
                + "\n    " + note);
    }

    /**
     * class method to print delete Task message.
     */
    public void printDeleteTask(Task t, int n) {
        String noteUpdated = "Now you have " + n + " tasks in the list.";
        printMessage("Noted. I've deleted this task:\n      " + t.toString()
                + "\n    " + noteUpdated);
    }

    /**
     * class method to print message - the lines and indentation and formatting.
     */
    public static void printMessage (String str){
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + str);
        System.out.println("  ____________________________________________________________");
    }
}
