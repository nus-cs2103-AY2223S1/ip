import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Duke is a personal assistant Chat-bot that aims to help users to keep track of various things
 */
public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    
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
    private static void commandParser() {
        boolean endLoop = false;
        StorageList storageList = new StorageList();
        FileIO.load(storageList, "./default.txt");
        
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
                    int index = getIndex(input);
                    try {
                        storageList.mark(index);
                        Output.MARK.changeStatus(storageList.get(index));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                case "unmark":
                    index = getIndex(input);
                    try {
                        storageList.unmark(index);
                        Output.UNMARK.changeStatus(storageList.get(index));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    parseTask(input, command, storageList, "/by");
                    break;
                case "event":
                    parseTask(input, command, storageList, "/at");
                    break;
                case "todo":
                    parseTask(input, command, storageList, "");
                    break;
                case "delete":
                    index = getIndex(input);
                    try {
                        Task task = storageList.get(index);
                        storageList.delete(index);
                        Output.DELETE.modifyTask(task, storageList);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "save":
                    String filename = "./default.txt";
                    if (input.split(" ").length == 2) {
                        filename = input.split(" ")[1];
                    }
                    
                    Output.SAVE.print();
                    FileIO.save(storageList, filename);
                    break; 
                case "load":    
                    filename = "./default.txt";
                    if (input.split(" ").length == 2) {
                        filename = input.split(" ")[1];
                    }
                    
                    Output.LOAD.print();
                    FileIO.load(storageList, filename);
                case "date":
                    try {
                        Output.DATE.listMatches(storageList, LocalDateTime.parse(findFirstCommand(input, command), formatter));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println(new DukeException("I'm sorry, but I don't know what that means :-(").getMessage());
            }
        }
    }

    /**
     * Finds the String between first command and second command (if exist)
     * @param input User input
     * @param command First command
     * @return String between first command and second command (if exist)
     */
    private static String findFirstCommand(String input, String command) throws DukeException {
        int endOfCommand = input.indexOf("/");
        int beginIndex = input.indexOf(command) + command.length() + 1;
        if (beginIndex > input.length()) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }
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
        return input.contains(command) && !command.equals("")
                ? input.substring(input.indexOf(command) + command.length() + 1)
                : "";
    }

    /**
     * Parses the user input and creates a Task object
     * @param input User input
     * @param command Command that the user inputs
     * @param storageList StorageList object
     * @param secCommand Second command that the user inputs
     */
    private static void parseTask(String input, String command, StorageList storageList, String secCommand) {
        try {
            String desc = findFirstCommand(input, command);
            String secondCommand = findSecondCommand(input, secCommand);
            switch (command) {
                case "deadline":
                    addTask(new Deadline(desc, secondCommand), storageList);
                    break;
                case "event":
                    addTask(new Event(desc, secondCommand), storageList);
                    break;
                case "todo":
                    addTask(new Todo(desc), storageList);
                    break;
            }
            FileIO.save(storageList, "./default.txt");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Adds a Task to the StorageList, print out the appropriate String
     * @param task Task to be added to the StorageList
     * @param storageList StorageList to be added to
     */
    private static void addTask(Task task, StorageList storageList) {
        storageList.add(task);
        Output.ADD.modifyTask(task, storageList);
    }

    /**
     * Finds the index of the Task that the user wants to mark/unmark/delete
     * @param input User input
     * @return Index of the Task
     */
    private static int getIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Return the formatter for the DateTime
     * @return DateTimeFormatter 
     */
    public static DateTimeFormatter getFormatter() {
        return formatter;
    }
}
