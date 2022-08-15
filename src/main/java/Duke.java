import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {

    private static final String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {

        sendMessage(greetingMessage);
        Scanner userInput = new Scanner(System.in);
        boolean receiveInput = true;

        while(receiveInput) {
            String input = userInput.nextLine();
            receiveInput = parseInput(input);
        }

    }

    private static boolean parseInput(String input) {
        switch (input) {
            case "bye":
                sendMessage(exitMessage);
                return false;
            default:
                sendMessage(input);
                return true;
        }
    }

    private static void sendMessage(String message) {

        String indentation = "    ";
        String line = "____________________________________________________________";
        Stream<String> messageLines = message.lines();

        System.out.println(indentation + line);
        messageLines.forEach(x -> System.out.println(indentation + x));
        System.out.println(indentation + line);
    }
}
