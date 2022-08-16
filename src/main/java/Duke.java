import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String INTRO = DIVIDER + "\n" + "Hello! I'm Duke!\n" + "What can I do for you?\n" + DIVIDER;
    private static final String OUTRO = DIVIDER + "\n" + "Bye. Hope to see you again soon!\n" + DIVIDER;
    private static final String EXIT_KEYWORD = "bye";
    private static final String LIST_KEYWORD = "list";

    public static void main(String[] args) {
        System.out.println(INTRO);

        Scanner scanner = new Scanner(System.in);
        DukeList list = new DukeList();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals(EXIT_KEYWORD)) {
                // Exit Duke
                scanner.close();
                break;
            } else if (input.equals(LIST_KEYWORD)) {
                // Print list
                System.out.println(DIVIDER + "\n" + list + "\n" + DIVIDER);
            } else {
                // Add item to list
                System.out.println(DIVIDER + "\n" + list.add(input) + "\n" + DIVIDER);
            }
        }

        System.out.println(OUTRO);
    }
}