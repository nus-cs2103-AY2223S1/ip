import java.util.ArrayList;
import java.util.Scanner;

public class UwuBot {
    private static UwuChat chat = new UwuChat();
//    private static Storage storedTaskList = new Storage();

    public static void main(String[] args) throws UwuException {
        Scanner scanner = new Scanner(System.in);
        Storage storedTaskList = new Storage();
        chat.greetUsers();

        while(scanner.hasNextLine()) {
            String userCmd = scanner.nextLine();

            String userCommand = userCmd.toLowerCase();

            try {
                if (userCommand.equals("bye")) {
//                    storedTaskList.store(chat.userToDoArray);
                    chat.leaveChat();
                    break;
                } else if (userCommand.trim().equals("list")) {
                    chat.listTasks();
                } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
                    chat.markTasks(userCommand);
                } else if (userCommand.startsWith("todo") ||
                        userCommand.startsWith("deadline") ||
                        userCommand.startsWith("event")) {
                    if (userCommand.replaceFirst("todo", "").isBlank() ||
                            userCommand.replaceFirst("deadline", "").isBlank() ||
                            userCommand.replaceFirst("event", "").isBlank()) {
                        throw new EmptyInputException("No task given.");
                    }

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
