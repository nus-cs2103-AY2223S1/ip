import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String INTRO = DIVIDER + "\n" + "Hello! I'm Duke!\n" + "What can I do for you?\n" + DIVIDER;
    private static final String OUTRO = DIVIDER + "\n" + "Bye. Hope to see you again soon!\n" + DIVIDER;

    private static final String EXIT_KEYWORD = "bye";
    private static final String LIST_KEYWORD = "list";
    private static final String MARK_KEYWORD = "mark";
    private static final String UNMARK_KEYWORD = "unmark";

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
            } else {
                System.out.println(DIVIDER);
                if (input.equals(LIST_KEYWORD)) {
                    // Print list
                    System.out.println(list);
                } else if (input.startsWith(MARK_KEYWORD)) {
                    // Mark task as done
                    System.out.println(list
                            .done(Integer.parseInt(input.substring(5).trim())));
                } else if (input.startsWith(UNMARK_KEYWORD)) {
                    // Mark task as undone
                    System.out.println(list
                            .undone(Integer.parseInt(input.substring(7).trim())));
                } else {
                    // Add item to list
                    Task t = new Task(input);
                    System.out.println(list.add(t));
                }
                System.out.println(DIVIDER);
            }
        }

        System.out.println(OUTRO);
    }
}