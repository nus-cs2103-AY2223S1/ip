import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String GREETING = LINE + "\n" + "Hello! I am Duke\n" + "What can I do for you?\n" + LINE;
    private static final String GOODBYE = LINE + "\n" + "Bye. Hope to see you again soon!" + "\n" + LINE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                scanner.close();
                System.out.println(GOODBYE);
                break;
            }
            else {
                System.out.println(LINE + "\n" + input + "\n" + LINE);
            }
        }
    }
}