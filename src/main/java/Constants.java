public class Constants {
    public static final String initMsg = "Hello! I'm Duke \n What can I do for you?";

    public static final String byeMsg = "Bye. Hope to see you soon again!";

    public static final String markMsg = "Nice! I've marked this task as done:";

    public static final String unmarkMsg = "OK, I've marked this task as not done yet:";

    public static final String list = "Here are the tasks in your list:";

    public static final String addTask = "Got it. I've added this task: ";

    public static final String deleteTask = "Noted. I've removed this task: ";

    public static String numTasks(int i) {
        return String.format("Now you have %d tasks in the list.", i);
    }

    public static final String invalid = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static final String invalidInput = "Something is missing form your input";

    public static final String invalidFile = "Invalid String in File";

    public static final String invalidIndex = "Invalid Index, no such task exists";
}
