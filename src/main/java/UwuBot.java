import java.util.ArrayList;
import java.util.Scanner;

public class UwuBot {
    private static UwuChat chat = new UwuChat();

    public static ArrayList<Task> userToDoArray = new ArrayList<Task>();

    public static void main(String[] args) throws UwuException {
        Scanner scanner = new Scanner(System.in);
        chat.greetingUsers();

        while(scanner.hasNextLine()) {
            String userCmd = scanner.nextLine();

            String userCommand = userCmd.toLowerCase();

            try {
                if (userCommand.equals("bye")) {
                    chat.leavingChat();
                    break;
                } else if (userCommand.trim().equals("list")) {
                    chat.listTasks();
                } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
                    chat.markTasks(userCommand);
                } else if (userCommand.startsWith("todo") ||
                        userCommand.startsWith("deadline") ||
                        userCommand.startsWith("event")) {
                    chat.addTask(userCommand);
                } else if (userCommand.startsWith("delete")) {
                    chat.deleteTask(userCommand);
                } else {
                    throw new UnknownCommandException("Unknown User Command.");
                }
            } catch (UnknownCommandException unknownCommandException) {
                chat.unknownCommand();
            } catch (EmptyInputException emptyInputException) {
                chat.emptyInput();
            } catch (IncorrectFormatException incorrectFormatException) {
                chat.incorrectFormat();
            } catch (NullTaskException nullTaskException) {
                chat.nullTask();
            }
        }
    }
}
