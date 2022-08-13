import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = " ____________________________________________________________";
    private static final String startMessage = "  Hello! I'm Duke\n" + "  What can I do for you?";
    private static final String endMessage = " Bye. Hope to see you again soon!";
    private static final ArrayList<String> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sendMessage(startMessage);
        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) {
                sendMessage(getList(tasks));
            } else {
                tasks.add(userCommand);
                sendMessage("added: " + userCommand);
            }
        }
        sendMessage(endMessage);
    }

    public static void sendMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static String getList(ArrayList<String> tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list = list + String.valueOf(i + 1) + ": " + tasks.get(i);
                break;
            }
            list = list + String.valueOf(i + 1) + ": " + tasks.get(i) + "\n";
        }
        return list;
    }
}
