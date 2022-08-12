import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private LinkedList<String> list;
    private static final String LINE = "--------------------------------------------------";

    private void run() {
        list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.printf("%s%n%s%n%s%n%s%n", LINE, "Hello! I'm Cortana", "What can I do for you?", LINE);

        while (true) {
            String input = sc.nextLine().trim();
            System.out.println(LINE);

            if (input.equals("bye")) {
                System.out.printf("%s%n%s%n","Bye. Hope to see you again soon!", LINE);
                break;
            } else if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.printf("%d. %s%n", i, list.get(i - 1));
                }
                System.out.println(LINE);
            } else {
                list.add(input);
                System.out.printf("added: %s%n%s%n", input, LINE);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
