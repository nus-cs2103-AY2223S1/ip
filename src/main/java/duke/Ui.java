package duke;

/**
 * Interacts with the user.
 */
public class Ui {

    public static final String DELETE_HEADER = Duke.LINE + "\n"
            + "Noted. I've removed this task:" + "\n";
    public static final String TASK_LIST_HEADER = Duke.LINE + "\n"
            + "Here are the tasks in your list:";
    public static final String UNMARK_HEADER = Duke.LINE + "\n"
            + "OK, I've marked this task as not done yet:" + "\n";
    public static final String MARK_HEADER =  Duke.LINE + "\n"
            + "Nice! I've marked this task as done:" + "\n";
    public static final String ADD_TASK_HEADER = Duke.LINE + "\n"
            + "Got it. I've added this task:" + "\n";
    public static final String FIND_HEADER = Duke.LINE + "\n"
            + "Here are the matching tasks in your list:" + "\n";

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
        System.out.println(Duke.LINE + "\n" +
                        "Bye. Hope to see you again soon!" + "\n" + Duke.LINE);
    }
}
