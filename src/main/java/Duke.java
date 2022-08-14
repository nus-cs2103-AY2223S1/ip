import java.util.Scanner;

public class Duke {
    private static void greetUser() {
        String message = Duke.formatText("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(message);
    }

    private static void startEchoing() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(Duke.formatText("Bye. Hope to see you again soon!\n"));
                break;
            }

            System.out.println(Duke.formatText(userInput));
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
        Duke.startEchoing();
    }
}
