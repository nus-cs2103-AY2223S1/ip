package duke;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Interacts with the user.
 */
public class Ui {

    public static final String DELETE_HEADER = Duke.LINE + "\n"
            + "Noted. I've removed this task:" + "\n";
    public static final String DELETE_HEADER_GUI = "Noted. I've removed this task:" + "\n";
    public static final String TASK_LIST_HEADER = Duke.LINE + "\n"
            + "Here are the tasks in your list:";
    public static final String TASK_LIST_HEADER_GUI = "Here are the tasks in your list:";
    public static final String UNMARK_HEADER = Duke.LINE + "\n"
            + "OK, I've marked this task as not done yet:" + "\n";
    public static final String MARK_HEADER = Duke.LINE + "\n"
            + "Nice! I've marked this task as done:" + "\n";
    public static final String UNMARK_HEADER_GUI = "OK, I've marked this task as not done yet:" + "\n";
    public static final String MARK_HEADER_GUI = "Nice! I've marked this task as done:" + "\n";
    public static final String ADD_TASK_HEADER = Duke.LINE + "\n"
            + "Got it. I've added this task:" + "\n";
    public static final String ADD_TASK_HEADER_GUI = "Got it. I've added this task:" + "\n";
    public static final String FIND_HEADER = Duke.LINE + "\n"
            + "Here are the matching tasks in your list:" + "\n";
    public static final String FIND_PRIORITY_HEADER = Duke.LINE + "\n"
            + "Tasks that matches priority ";
    public static final String FIND_HEADER_GUI = "Here are the matching tasks in your list:" + "\n";
    public static final String FIND_PRIORITY_HEADER_GUI = "Tasks that matches priority ";

    /**
     * Constructs a UI by greeting the user.
     */
    public Ui() {
        System.out.println(Duke.LINE + "\n" + "Hello! I'm Duke" + "\n"
                + "What can I do for you?" + "\n" + Duke.LINE + "\n");
    }

    /**
     * Closes the program.
     */
    public static void bye() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1200);
        System.out.println(Duke.LINE + "\n"
                + "Bye. Hope to see you again soon!" + "\n" + Duke.LINE);
    }

    /**
     * Closes the program.
     * @return the bye command.
     */
    public static String byeGui() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1200);
        return "Bye. Hope to see you again soon!";
    }
}
