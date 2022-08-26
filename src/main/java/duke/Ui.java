package main.java.duke;

/**
 * Class for Ui components of Duke.
 */
public class Ui {

    public static String divider() {
        return "--------------------";
    }

    protected static void setup() {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("Hello from " + "DUKE");
        System.out.println("What can I do for you?");
        System.out.println(divider());
    }

    protected static String checkbox(Boolean mark) {
        return mark ? "[x]" : "[ ]";
    }
}
