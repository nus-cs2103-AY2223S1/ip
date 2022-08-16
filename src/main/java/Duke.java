import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String INTRO = DIVIDER + "Hello! I'm Duke!\n" + "What can I do for you?\n" + DIVIDER;
    private static final String OUTRO = DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER;
    private static final String EXIT_KEYWORD = "bye";

    public static void main(String[] args) {
        System.out.println(INTRO);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals(EXIT_KEYWORD)) {
                scanner.close();
                break;
            }

            System.out.println(DIVIDER + input + "\n" + DIVIDER);
        }

        System.out.println(OUTRO);
    }
}