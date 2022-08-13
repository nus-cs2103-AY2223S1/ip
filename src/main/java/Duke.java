import java.util.List;
import java.util.Scanner;

/**
 * Simple CLI chatbot that reacts on user input.
 */
public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Log logs = new Log();

    /**
     * Main function for the chatbot.
     * 
     * @param args System arguments. Not used for this program.
     */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);
        greetingMessage();
        listenForInputs();
    }

    private static void greetingMessage() {
        String[] messages = { "Hello! I'm Duke.", "What can I do for you?" };
        formatAndPrint(List.<String>of(messages));
    }

    private static void formatAndPrint(List<? extends String> texts) {
        String divider = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println(divider);
        texts.forEach((text) -> System.out.println(padding + text));
        System.out.println(divider);
    }

    private static void listenForInputs() {
        String input = scanner.nextLine();
        switch (input) {
            case "bye":
                formatAndPrint(List.<String>of("Bye bye"));
                return;
            case "list":
                formatAndPrint(logs.getLogs());
                break;
            default:
                logs.add(input);
                formatAndPrint(List.<String>of("added: " + input));
        }
        listenForInputs();
    }
}
