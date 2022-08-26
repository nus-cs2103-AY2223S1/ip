package duke;
/**
 * Default constructor for Ui.
 */
public class Ui {

    public static Object welcomeMsg;

    static void addedMsg(Task task) {
        System.out.println("Got it. I've added this task: \n" +
                "   " + task.toString() +
                "\n Now you have " + TaskList.taskList.size() +
                (TaskList.taskList.size() > 1 ? " tasks in your list." : " task in your list."));
    }

    static void deleteMsg(Task task) {
        System.out.println("Noted. I've removed this task: \n" +
                "   " + task.toString() +
                "\n Now you have " + TaskList.taskList.size() +
                (TaskList.taskList.size() > 1 ? " tasks in your list." : " task in your list."));
    }
    static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    static void markMsg(int index) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + "  " + TaskList.taskList.get(index).toString());
    }

    static void unmarkMsg(int index) {
        System.out.println( "Nice! I've marked this task as not done yet: \n" + TaskList.taskList.get(index).toString());

    }

    static void farewellMsg() {
        System.out.println("Bye Bye!");
    }

    static void errorMsg(String message) {
        System.out.println(message);
    }
}
