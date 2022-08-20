package duke;

public class Ui {
    private static String WELCOME_MESSAGE =  "Hello! I'm duke.Duke\n" + "What can I do for you?";
    private static String GOODBYE_MESSAGE =  "Bye. Hope to see you again soon!";

    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void showGoodByeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
