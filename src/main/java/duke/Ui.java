package duke;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String BORDER = LINE + "\n";
    private static final String GREETING = "Hello! I am duke.\n" + "What can I do for you?\n";
    private static final String INVALID = "☹ OOPS!!! ";

    public void printMessage(String message) {
        System.out.println(BORDER + message + BORDER);
    }

    public void welcomeMessage() {System.out.println(BORDER + GREETING + BORDER);}

    public void invalidMessage(String error) {System.out.println(BORDER + INVALID + error + "\n" + BORDER);}
}
