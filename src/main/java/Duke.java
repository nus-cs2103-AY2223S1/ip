import java.util.Scanner;

public class Duke {
    /** List of items stored by Duke. */
    private static final String[] list = new String[100];

    /** Total number of items in list. */
    private static int itemsCount = 0;

    private static void greetUser() {
        String message = Duke.formatText("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(message);
    }

    private static void startService() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(Duke.formatText("Bye. Hope to see you again soon!"));
                break;
            }

            if (userInput.equals("list")) {
                StringBuilder string = new StringBuilder("");
                for (int i = 0; i < Duke.itemsCount; i++) {
                    int itemIndex = i + 1;
                    string.append(itemIndex).append(". ").append(Duke.list[i]).append("\n");
                }

                System.out.println(Duke.formatText(string.toString()));
                continue;
            }

            Duke.list[Duke.itemsCount++] = userInput;

            System.out.println(Duke.formatText("added: " + userInput));
        }
    }

    /**
     * Styles a given text with indentation and wraps the text around horizontal lines.
     *
     * @param text String that needs to be styled.
     * @return Formatted String that has proper indentation and wrapped around horizontal lines.
     */
    private static String formatText(String text) {
        final String HORIZONTAL_LINE = "\t--------------------------------------------------\n";

        String[] lines = text.split("\\r?\\n");
        StringBuilder formattedText = new StringBuilder(HORIZONTAL_LINE);
        for (String line : lines) {
            formattedText.append("\t").append(line).append("\n");
        }

        return formattedText + HORIZONTAL_LINE;
    }

    public static void main(String[] args) {
        Duke.greetUser();
        Duke.startService();
    }
}
