import java.util.Scanner;

public class Duke {
    private static final String LINE = "--------------------------------------------------";

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("%s%n%50s%n%50s%n%s%n", LINE, "Hello! I'm Cortana", "What can I do for you?", LINE);

        while (true) {
            String input = sc.nextLine().trim();
            System.out.println(LINE);

            if (input.equals("bye")) {
                System.out.printf("%50s%n%s%n","Bye. Hope to see you again soon!", LINE);
                break;
            } else {
                System.out.printf("%50s%n%s%n", input, LINE);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
