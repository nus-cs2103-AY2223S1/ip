package duke;


public class Ui {

    public static String printEntryStatement() {
        String l1 = "Hello from Chan-bot!\n";
        String l2 = "What can I do for you?\n";
        return l1 + l2;
    }

    public static String printByeStatement() {
        String l1 = "Bye bye!\n";
        return l1;
    }

    public static String printAddStatement(String res, int len) {
        String s = "Got it. I've added this task:\n" + "  " + res + "\nNow you have " + len
                + " tasks in the list.\n";
        return s;
    }

    public static String printDeleteStatement(String res, int len) {
        String s = "Okay! The task: \n" + res + "\nhas been deleted forever.\n" +
                "You have " + len + " task" + ((len!=1)?"s ":" ") + "left!\n";
        return s;
    }
}
