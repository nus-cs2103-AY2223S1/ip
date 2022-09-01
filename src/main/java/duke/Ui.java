/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import duke.task.Task;

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
    public String printWelcome() {
        return "Hello from\n" + logo + "\n" + initText;
    }

    /**
     * class method to print bye message.
     */
    public String printBye() {
        return printMessage(endText);
    }

    /**
     * class method to print marked message.
     */
    public String printMark(Task t) {
        t.mark();
        return printMessage("Nice! I've marked this task as done :)\n      "
                + t.toString());
    }

    /**
     * class method to print unmarked message.
     */
    public String printUnmark(Task t) {
        t.unmark();
        return printMessage("ok I mark this task as not done yet... \n      "
                + t.toString());
    }

    /**
     * class method to print add Task message.
     */
    public String printAddTask(Task t, int n) {
        String note = "Now you have " + n + " tasks in the list.";
        return printMessage("Got it, I've added this task:\n      " + t.toString()
                + "\n    " + note);
    }

    /**
     * class method to print delete Task message.
     */
    public String printDeleteTask(Task t, int n) {
        String noteUpdated = "Now you have " + n + " tasks in the list.";
        return printMessage("Noted. I've deleted this task:\n      " + t.toString()
                + "\n    " + noteUpdated);
    }

    /**
     * class method to print message - the lines and indentation and formatting.
     */
    public static String printMessage (String str){
        String line = "  ____________________________________________________________";
        return line + "\n    " + str + "\n" + line;
    }
}
