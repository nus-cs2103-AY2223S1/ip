import java.util.ArrayList;
import java.util.Scanner;

public class UwuBot {
    private static UwuChat chat = new UwuChat();

    public static ArrayList<Task> userToDoArray = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        chat.greetingUsers();

        while(scanner.hasNextLine()) {
            String userCommand = scanner.nextLine();

            if (userCommand.equals("bye")) {
                chat.leavingChat();
                break;
            } else if (userCommand.equals("list")) {
                chat.listTasks();
            } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
                chat.markTasks(userCommand);
            } else if (userCommand.startsWith("todo") ||
                       userCommand.startsWith("deadline") ||
                       userCommand.startsWith("event")){
                chat.addTask(userCommand);
            } else {
                //do nothing
            }
        }
    }
}
