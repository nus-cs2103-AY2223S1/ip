/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * public class Ui to handle text shown to users when command is typed in to Duke.
 */
public class Ui {

    private static final String initText = "Hello! I'm Duke! What can I do for you? \n"
            + "The commands I currently have are as follows: \n";
    private static final String endText = "Bye bye! Hope to see you again soon!";

    private static final String instructions = "1) hi\n"
            + "2) bye \n"
            + "3) list \n"
            + "4) mark <index of task in list to mark> \n"
            + "5) unmark <index of task in list to unmark> \n"
            + "6) todo <description of task> \n"
            + "7) deadline <description of task> /by <date in DD/MM/YYYY format> \n"
            + "8) event <description of task> /at <date in DD/MM/YYYY format> \n"
            + "9) delete <index of task in list to delete> \n"
            + "10) find <keywords of task in list to find> \n"
            + "11) priority <priority level> /for <index of task in list to update> (default priority level is 0)";

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
    public static String printWelcome() {
        return printMessage(initText + instructions);
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
                + "\n" + note);
    }

    /**
     * class method to print delete Task message.
     */
    public String printDeleteTask(Task t, int n) {
        String noteUpdated = "Now you have " + n + " tasks in the list.";
        String str = "Noted. I've deleted this task:\n      " + t.toString()
                + "\n" + noteUpdated;
        return printMessage(str);
    }

    /**
     * class method to print Priority for Task Message.
     * @param priorityLevel level of priority
     * @param t the task to prioritize
     * @return
     */
    public String printPriority(int priorityLevel, Task t) {
        t.priority(priorityLevel);
        String s = "Nice! I've marked this task to your specific priority level :) \n"
                + "Default priority level is 0! \n      "
                + t.toString();
        return printMessage(s);
    }

    /**
     * public method printMessage that helps format necessary message style if needed.
     * @param str
     * @return desired string formatting output.
     */
    public static String printMessage(String str) {
        return str;
    }
}
