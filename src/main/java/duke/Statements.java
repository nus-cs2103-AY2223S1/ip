package duke;

public class Statements {
    public static void entryStatement() {
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/ ^  ^\\");
        System.out.println("Hello from Chan-bot!");
    }

    public static void initStatement() {
        System.out.println("What can I do for you?\n");
    }

    public static void byeStatement() {
        System.out.println("Bye bye!");
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/    ^\\");
    }

    public static void addStatement(String res, int len) {
        System.out.println("Got it. I've added this task:\n" + "  " + res + "\nNow you have " + len + " tasks in the list.\n");
    }
}
