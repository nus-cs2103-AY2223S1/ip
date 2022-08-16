import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    // ChatBot constants
    public static final String NAME = "Duke";
    // ChatBot commands
    public static final String CMD_TERMINATE = "bye";
    public static final String CMD_LIST = "list";
    // UI constants
    public static final String INDENT_CHAR = "\t";
    public static final String LINE_STR = "-".repeat(50);

    private static final String[] history = new String[100]; // Max capacity: 100
    private static int currHistIdx = 0;

    /**
     * Utility function to print line to STDOUT
     *
     * @param line   Line to printed to STDOUT
     * @param indent Number of indentation prefix
     */
    private static void printLine(String line, int indent) {
        System.out.printf("%s%s\n", INDENT_CHAR.repeat(indent), line);
    }

    /**
     * Utility function to format Duke's response and display it to STDOUT
     * <p>
     * Note: Defaults to 1 indent prefix for every response
     * </p>
     *
     * @param response A single response
     */
    private static void respond(String response) {
        printLine(LINE_STR, 1);
        printLine(response, 1);
        printLine(LINE_STR, 1);
    }

    /**
     * Utility function to format Duke's responses and display it to STDOUT
     * <p>
     * Note: Defaults to 1 indent prefix for every response
     * </p>
     *
     * @param responses A collection of lines of responses
     */
    private static void respond(List<String> responses) {
        printLine(LINE_STR, 1);
        for (String respLine : responses) {
            printLine(respLine, 1);
        }
        printLine(LINE_STR, 1);
    }

    private static void handleListCommand() {
        if (currHistIdx == 0) {
            respond("There are no items in the list!");
            return;
        }
        List<String> responses = new ArrayList<>();
        for (int idx = 0; idx < currHistIdx; idx++) {
            responses.add(String.format("%d. %s", idx + 1, history[idx]));
        }
        respond(responses);
    }

    public static void main(String[] args) {
        // Greetings
        respond(Arrays.asList(String.format("Hi I'm %s", NAME), "What can I do for you?"));

        // Chat
        boolean terminate = false;
        Scanner input = new Scanner(System.in);
        while (!terminate && input.hasNextLine()) {
            String query = input.nextLine();
            switch (query) {
                case CMD_TERMINATE:
                    // terminate
                    terminate = true;
                    respond("Bye. Hope to see you again soon!");
                    break;
                case CMD_LIST:
                    handleListCommand();
                    break;
                default:
                    // add to history
                    history[currHistIdx] = query;
                    currHistIdx++;
                    respond(String.format("added: %s", query));
            }
        }
    }
}
