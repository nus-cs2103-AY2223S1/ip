import java.util.Objects;
import java.util.Scanner;

public class Duke {

    /** Prints Duke's greeting on opening the app */
    private static void IntroduceSelf() {
        SayLines(new String[] {
                "____________________________________________________________",
                "Hello! I'm Duke",
                "What can I do for you?",
                "____________________________________________________________"
        });
    }

    /** Prints Duke's response to the input */
    private static void Respond(String input) {
        SayLines(new String[] {
                "____________________________________________________________",
                input,
                "____________________________________________________________"
        });
    }

    /** Prints Duke's closing statement on exiting the app */
    private static void SayGoodbye() {
        SayLines(new String[] {
                "____________________________________________________________",
                "Bye. Hope to see you again soon!",
                "____________________________________________________________"
        });
    }

    /** Prints the given lines */
    private static void SayLines(String[] lines) {
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        IntroduceSelf();

        Scanner InputScanner = new Scanner(System.in);
        String UserInput = InputScanner.nextLine();
        while (!Objects.equals(UserInput, "bye")) {
            Respond(UserInput);
            UserInput = InputScanner.nextLine();
        }
        InputScanner.close();

        SayGoodbye();
    }
}
