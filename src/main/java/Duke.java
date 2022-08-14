import java.util.Scanner;

/**
 * Duke is a personal assistant Chat-bot that aims to help users to keep track of various things
 */
public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main method that runs the program
     * @param args Arguments passed to the program
     */
    public static void main(String[] args) {
        Output.GREETINGS.print();
        commandParser();
    }

    /**
     * Parses the user input and calls the appropriate method
     */
    private static void commandParser(){
        boolean endLoop = false;
        StorageList storageList = new StorageList();

        while (!endLoop) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];

            switch (command) {
                case "bye":
                    Output.GOODBYE.print();
                    endLoop = true;
                    break;
                case "list":
                    Output.LIST.list(storageList);
                    break;
                case "mark":
                    //TODO: handle when index is out of range
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    storageList.mark(index);
                    Output.MARK.changeStatus(storageList.get(index));
                    break;
                case "unmark":
                    //TODO: handle when index is out of range
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    storageList.unmark(index);
                    Output.UNMARK.changeStatus(storageList.get(index));
                    break;
                case "deadline":
                    String by = findSecondCommand(input, "/by");
                    addTask(new Deadline(findFirstCommand(input, command), by), storageList);
                    break;
                case "event":
                    String at = findSecondCommand(input, "/at");
                    addTask(new Event(findFirstCommand(input, command), at), storageList);
                    break;
                case "todo":
                    addTask(new Todo(findFirstCommand(input, command)), storageList);
                    break;
                default:
                    // add as to-do
                    addTask(new Todo(input), storageList);
            }
        }
    }

    /**
     * Finds the String between first command and second command (if exist)
     * @param input User input
     * @param command First command
     * @return String between first command and second command (if exist)
     */
    private static String findFirstCommand(String input, String command) {
        int endOfCommand = input.indexOf("/");
        int beginIndex = input.indexOf(command) + command.length() + 1;
        return endOfCommand == -1
                ? input.substring(beginIndex)
                : input.substring(beginIndex, endOfCommand);
    }

    /**
     * Finds the String between second command (if exist) and end
     * @param input User input
     * @param command Second command
     * @return String between second command (if exist) and end
     */
    private static String findSecondCommand(String input, String command) {
        return input.contains(command)
                ? input.substring(input.indexOf(command) + command.length() + 1)
                : "";
    }

    /**
     * Adds a Task to the StorageList, print out the appropriate String
     * @param task Task to be added to the StorageList
     * @param storageList StorageList to be added to
     */
    private static void addTask(Task task, StorageList storageList) {
        storageList.add(task);
        Output.ADD.addTask(task, storageList);
    }
}
