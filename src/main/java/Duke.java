import java.util.Scanner;

public class Duke {
    private static final String line = " ____________________________________________________________";
    private static final String startMessage = "  Hello! I'm Duke\n" + "  What can I do for you?";
    private static final String endMessage = " Bye. Hope to see you again soon!hii";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sendMessage(startMessage);
        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else {
                sendMessage(userCommand);
            }
        }
        sendMessage(endMessage);
    }

    public static void sendMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
}
