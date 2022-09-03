package duke;

/**
 * Default constructor for Ui.
 */
public class Ui {

    static String addedMsg(Task task) {
        return ("Got it. I've added this task: \n"
                +
                "   " + task.toString()
                +
                "\n Now you have " + TaskList.taskList.size()
                + (TaskList.taskList.size() > 1 ? " tasks in your list." : " task in your list."));
    }

    static String deleteMsg(Task task) {
        return ("Noted. I've removed this task: \n"
                +
                "   " + task.toString()
                +
                "\n Now you have " + TaskList.taskList.size()
                + (TaskList.taskList.size() > 1 ? " tasks in your list." : " task in your list."));
    }

    static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    static String markMsg(int index) {
        return ("Nice! I've marked this task as done:" + "\n" + "  " + TaskList.taskList.get(index).toString());
    }

    static String unmarkMsg(int index) {
        return ("Nice! I've marked this task as not done yet: \n" + TaskList.taskList.get(index).toString());

    }

    static String farewellMsg() {
        return ("Bye Bye!");
    }

    static void errorMsg(String message) {
        System.out.println(message);
    }

    public static String resultsMsg(String list) {
        return ("Here are the matching tasks in your list: \n" + list);
    }
}
