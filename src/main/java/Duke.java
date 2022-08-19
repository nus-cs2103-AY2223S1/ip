import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Sup bro! My name is Candice.");
        System.out.println("I'm here to help you track your tasks!");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                Action action = Action.actionParser(input);
                action.resolve(taskList);
            } catch (UnknownActionException | EmptyTaskNameException | InvalidFormattingException |
                    EmptyTimingException | IllegalArgumentException e) {
                System.out.println(e);
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye bro. See you soon.");
    }
}
