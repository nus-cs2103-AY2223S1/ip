import java.util.Scanner;
public class UwuHandler {
    private Storage storage;
    private TaskList userTaskList;
    private UwuChat chat;

    public UwuHandler(String filePath) {
        this.storage = new Storage(filePath);
        this.userTaskList = storage.load();
        this.chat = new UwuChat(userTaskList);
    }

    public void handleResponse(String userCommand) throws UwuException {
        Scanner scanner = new Scanner(System.in);
        Storage storedTaskList = new Storage("data/taskList.txt");


        try {
            if (userCommand.equals("bye")) {
                chat.leaveChat();
                System.exit(0);
            } else if (userCommand.trim().equals("list")) {
                chat.listTasks();
            } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
                chat.markTasks(userCommand);
                storage.save(chat.userTaskList.taskListToStorageString());
            } else if (userCommand.startsWith("todo") ||
                        userCommand.startsWith("deadline") ||
                        userCommand.startsWith("event")) {
                if (userCommand.replaceFirst("todo", "").isBlank() ||
                        userCommand.replaceFirst("deadline", "").isBlank() ||
                        userCommand.replaceFirst("event", "").isBlank()) {
                        throw new EmptyInputException("No task given.");
                }

                chat.addTask(userCommand);
                storage.save(chat.userTaskList.taskListToStorageString());
            } else if (userCommand.startsWith("delete")) {
                chat.deleteTask(userCommand);
                storage.save(chat.userTaskList.taskListToStorageString());
            } else {
                throw new UnknownCommandException("Unknown User Command.");
            }
        } catch (UnknownCommandException unknownCommandException) {
            chat.unknownCommand();
        } catch (EmptyInputException emptyInputException) {
            chat.emptyInput();
        } catch (IncorrectFormatException incorrectFormatException) {
            chat.incorrectFormat();
        } catch (InvalidDateException invalidDateException) {
            chat.invalidDate();
        } catch (NullTaskException nullTaskException) {
            chat.nullTask();
        }
    }
}
