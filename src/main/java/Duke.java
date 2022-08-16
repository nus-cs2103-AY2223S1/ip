import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {
    // Name of the chatbot
    private static final String NAME = "Duke";
    //  Horizontal line separator used in beautifying print commands
    private static final String SEPARATOR = "____________________________________________________________";

    // The greeting message used by the chatbot when the program starts
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\n\tWhat can I do for you?", Duke.NAME);
    // The goodbye message used by the chatbot when the program terminates
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    private enum Command {
        // The 'bye' command is used to indicate to the program to exit
        BYE,
        // The 'list' command is used to indicate to the program to list the inputted commands
        LIST;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    /**
     * Returns the formatted input string that was received using the separator and tab formatter
     *
     * @param input Input string that was received
     * @return The formatted output string
     */
    private static String outputFormatter(String input) {
        return Duke.outputFormatter(new String[]{input});
    }

    /**
     * Returns the formatted input strings that was received using the separator and tab formatter
     *
     * @param inputs Input strings that was received
     * @return The formatted output string
     */
    private static String outputFormatter(String[] inputs) {
        String lineSeparator = String.format("\t%s\n", Duke.SEPARATOR);
        String formattedInputs = Arrays.stream(inputs).map(input -> "\t" + input).collect(Collectors.joining("\n"));
        return String.format("%s%s\n%s", lineSeparator, formattedInputs, lineSeparator);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();

        // Greet the user
        System.out.println(Duke.outputFormatter(Duke.GREETING_MESSAGE));

        // Receive the command entered by the user
        String command = scanner.nextLine();
        while (!command.equals(Command.BYE.toString())) {
            if (command.equals(Command.LIST.toString())) {
                String[] commandList = commandManager.list();
                // Form the ordered command list here, as the command manager has no knowledge on the output
                // format of the list of commands
                String[] orderedCommandList = IntStream.range(0, commandList.length)
                                .mapToObj(index -> String.format("%d. %s", index + 1, commandList[index]))
                                .toArray(String[]::new);
                System.out.println(Duke.outputFormatter(orderedCommandList));
            } else {
                commandManager.add(command);
                System.out.println(Duke.outputFormatter("added: " + command));
            }

            // Continue to retrieve the next command
            command = scanner.nextLine();
        }

        // Bid the user goodbye
        System.out.println(Duke.outputFormatter(Duke.GOODBYE_MESSAGE));
    }
}
