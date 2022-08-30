package duke.ui;
import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    public static final String NAME = "Duke";
    private final static String GREATING_MESSAGE = "Hello! I'm " + NAME + "\n"
            + "What can I do for you?\n";
    private final static String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";

//    public void echo (TaskList taskList, String input) {
//        Parser.parse(taskList, input);
//    }

    public static String readCommand(Scanner sc) {
            return sc.nextLine();
    }

    public static void showWelcomeMessage() {
        System.out.println(GREATING_MESSAGE);
    }

    public static void showByeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    public static void showError(Exception e) {
        System.out.println("The following error occur: " + e);
    }
    public static void showLine() {
        System.out.println("____________________________________________");
    }
}
