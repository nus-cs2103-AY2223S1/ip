import java.util.ArrayList;

public class UI {
    private static final String LINE = "____________________________________________________________";

    private static final String LOGO = "Hello from\n" +
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_LOGO = "Hello from\n" + LOGO;

    private static final String GREETING = "Hello! I'm Duke\n" +
            "What can I do for you?";

    private static final String GOODBYE_MESSAGE = "Bye, Hope to see you again soon!";

    public static void getLOGO() {
        System.out.println(LOGO);
    }

    public static void getLINE() {
        System.out.println(LINE);
    }

    public static void getGREETING() {
        System.out.println(GREETING);
    }

    public static void end() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }

    public static void getGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    //method to print in list format
    public static void printList() {
        int len = TaskList.taskListLength();
        if (len < 1) {
            throw new DukeException("List is empty, you have no tasks!");
        }
        ArrayList<Task> taskArrayList = TaskList.getTaskList();
        int counter = 0;
        int numbering = 1;
        UI.getLINE();
        while (counter < len) {
            Task temp = taskArrayList.get(counter);
            System.out.println(numbering + "." + temp);
            counter++;
            numbering++;
        }
        getLINE();
    }

    public static void printAddition(Task task) {
        int tasksLeft = TaskList.taskListLength();
        UI.getLINE();
        ;
        System.out.println("Got it. I've added this task:\n " + " " + task.toString() +
                "\nNow you have " + tasksLeft + " tasks in the list.");
        UI.getLINE();
        ;

    }

    public static void markAsDoneUI(Task tsk) {
        UI.getLINE();
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " + tsk);
        UI.getLINE();
    }

    public static void markAsUndoneUI(Task tsk) {
        UI.getLINE();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "  " + tsk);
        UI.getLINE();
    }
}
