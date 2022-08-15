import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String GREETING = LINE + "\n" + "Hello! I am Duke\n" + "What can I do for you?\n" + LINE;
    private static final String GOODBYE = LINE + "\n" + "Bye. Hope to see you again soon!" + "\n" + LINE;
    private static final List<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                scanner.close();
                System.out.println(GOODBYE);
                break;
            } else if (input.equals("list")) {
                System.out.println(LINE + "\n");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i) + "\n");
                }
                System.out.println(LINE + "\n");
            } else {
                taskList.add(input);
                System.out.println(LINE + "\n" + "added: " + input + "\n" + LINE);
            }
        }
    }
}