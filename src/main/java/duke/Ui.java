package duke;

public class Ui {
    private static final String GREETING = "Hi friend! How may I help you?";
    private static final String FAREWELL = "See you soon, friend!";

    public static void sayHello() {
        System.out.println(Ui.GREETING);
    }
    public static void sayGoodbye() {
        System.out.println(Ui.FAREWELL);
    }
    public static void echo(String message) {
        System.out.println(message);
    }
}
