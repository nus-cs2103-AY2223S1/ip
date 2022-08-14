import java.util.Scanner;

public class Duke {
    /** List of items stored by Duke. */
    private static final String[] list = new String[100];

    /** List that keeps track of whether item is marked as done. */
    private static final boolean[] isDoneList = new boolean[100];

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

            // End service.
            if (userInput.equals("bye")) {
                System.out.println(Duke.formatText("Bye. Hope to see you again soon!"));
                break;
            }

            // List items in list.
            if (userInput.equals("list")) {
                StringBuilder string = new StringBuilder("");
                for (int i = 0; i < Duke.itemsCount; i++) {
                    int itemIndex = i + 1;

                    if (Duke.isDoneList[i]) {
                        string.append(itemIndex).append(".").append("[X] ").append(Duke.list[i]).append("\n");
                    } else {
                        string.append(itemIndex).append(".").append("[ ] ").append(Duke.list[i]).append("\n");
                    }
                }

                System.out.println(Duke.formatText(string.toString()));
                continue;
            }

            // Mark item as done.
            if (userInput.contains("mark")) {
                String action = userInput.split(" ")[0];
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (action.equals("mark")) {
                    System.out.println(Duke.formatText("Nice! I've marked this task as done:\n" + "[X] " +
                            Duke.list[index - 1]));
                    Duke.isDoneList[index - 1] = true;
                    continue;
                }

                if (action.equals("unmark")) {
                    System.out.println(Duke.formatText("OK, I've marked this task as not done yet:\n" + "[ ] " +
                            Duke.list[index - 1]));
                    Duke.isDoneList[index - 1] = false;
                    continue;
                }
            }

            // Add item to list.
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
