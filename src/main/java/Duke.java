import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    enum CommandType {TODO, MARK, UNMARK, DEADLINE, EVENT, BYE, LIST, DELETE};
    private static void greetUser() {
        String logo = "_______     _\n" +
                "|  ___|    | |\n" +
                "|  |_  ____| |_____ ____  _  __\n" +
                "|   _|/ _  \\ | ___|/  _ \\| |/  \\\n"+
                "|  | | |_| | | |___| |_| |  / \\ |\n" +
                "|__|  \\__|_|_|____|\\____/|_|  |_|\n";
        System.out.println("Hello from" );
        System.out.println(logo);
        // prompt user
        System.out.println("Where would you like to go next?");
        System.out.print(">> ");
    }
    
    /**
     * Mark Task at index n in list.
     * No checks performed to check if task is already marked.
     * @param n task to mark as done (n - 1) index in actual list
     */
    public static void markTask(int n) {
        Task taskToModify = userInputHistory.get(n - 1);
        taskToModify.markAsDone();
        System.out.printf("Marked task %d \n%s\n", n, taskToModify);
        System.out.print(">>");
    }

    /**
     * Un-mark task at index n in list.
     * No checks performed to check if task is already unmarked.
     * @param n task to mark as not done (n - 1) index in actual list
     */
    public static void unmarkTask(int n)  {
        Task taskToModify = userInputHistory.get(n - 1);
        taskToModify.markAsNotDone();
        System.out.printf("Unmarked task %d \n%s\n", n, taskToModify);
        System.out.print(">>");
    }

    /**
     * Extract task number from string input
     * @param s extracts task number from user input
     * @return index of the task in the list plus one
     */
    private static int getTaskNumber(String s) throws DukeException{
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        String numberOnly= s.replaceAll("[^0-9]", "");
        int n;
        if (numberOnly.length() <= 0) {
            throw new DukeException("no number given\n>>");
        } else {
            n = Integer.parseInt(numberOnly);
            if (n > userInputHistory.size()) {
                throw new DukeException("task does not exist is list\n>>");
            } else {
                return n;
            }
        }
    }

    /**
     * Delete task at index n in userInputHistory
     * @param n index to be deleted
     */
    private static void deleteTask(int n)   {
        Task taskToModify = userInputHistory.get(n - 1);
        userInputHistory.remove(n - 1);
        System.out.printf("Task removed: \n%s\n", taskToModify);
        System.out.printf("Total: %d\n", userInputHistory.size());
        System.out.print("______\n");
        System.out.print(">>");
    }

    /**
     * Filter userInput and call addEventToHistory
     * @param event
     */
    private static void handleEvent(String event) throws DukeException{
        String description, at;
        description = getDescription(event, "event");
        at = getDate(event);
        addEventToHistory(description, at);
    }

    /**
     * Filter userInput and call addDeadlineToHistory
     * @param deadline
     */
    private static void handleDeadline(String deadline) throws DukeException{
        String description, by;
        description = getDescription(deadline, "deadline");
        by = getDate(description);
        addDeadlineToHistory(description, by);
        }

    /**
     * Filter userInput and call addDeadlineToHistory
     * @param task
     */
    private static void handleTask(String task) throws DukeException {
        String description;
        description = getDescription(task, "todo");
        addTaskToHistory(description);
    }

    /**
     * Return enum command type used
     * @param userInput
     * @return
     * @throws DukeException
     */
    private static CommandType getCommand(String userInput) throws DukeException{
        int firstWhiteSpace = userInput.trim().indexOf(" ");
        String command;
        CommandType commandGiven;
        command = firstWhiteSpace < 0 ? userInput: userInput.trim().substring(0, firstWhiteSpace);
        if (command.equals("")) {
            throw new DukeException("no command given\n>>");
        } else {
            for (CommandType c : CommandType.values()) {
                if(c.name().equalsIgnoreCase(command)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * Menu handler
     * @param userInput
     */
    private static void handleInput(String userInput) throws DukeException{
        CommandType command = getCommand(userInput);
        if (command == null) {
            throw new DukeException( "Enter a valid command (todo, event, deadline, list, mark, unmark, bye)\n>>");
        } else {
            switch(command) {
            case BYE:
                System.out.println("Thank you for swinging by :)");
                System.exit(0);
                break;
            case LIST:
                showHistory();
                break;
            case MARK:
                markTask(getTaskNumber(userInput));
                break;
            case UNMARK:
                unmarkTask(getTaskNumber(userInput));
                break;
            case TODO:
                handleTask(userInput);
                break;
            case EVENT:
                handleEvent(userInput);
                break;
            case DEADLINE:
                handleDeadline(userInput);
                break;
            case DELETE:
                deleteTask(getTaskNumber(userInput));
                break;
            default:
                handleDeadline(userInput);
                throw new DukeException("Enter a valid command (todo, event, deadline, list, mark, unmark, bye)\n>>");
            }

        }
    }

    /**
     * Grabs description from string which is expected to have format:
     * <command> <description>...
     * @param command
     * @param commandUsed
     * @return
     */
    private static String getDescription(String command, String commandUsed) throws DukeException{
        String description;
        int startDescriptionIndex = command.indexOf(commandUsed) + commandUsed.length();
        int endDescriptionIndex = command.indexOf("/");
        if (startDescriptionIndex < 0) {
            throw new DukeException("Command does not follow pattern <command> <description>...\n>>");
        } else {
            if (commandUsed == "event" || commandUsed == "deadline") {
                if (endDescriptionIndex < 0) {
                    throw new DukeException("Command does not follow pattern  ... /<at/by> <date in HH:MM DD:MM:YYYY>\n>>");
                } else {
                    description = command.substring(startDescriptionIndex, endDescriptionIndex).trim();
                }
            } else {
                description = command.substring(startDescriptionIndex).trim();
            }
        }
        if (description.equals("")) {
            throw new DukeException("Empty description field\n>>");
        }
        return description;
    }

    /**
     * Grabs date from string which is expected to have format:
     * ... /<at/by> <date in HH:MM DD:MM:YYYY>
     * @param command - string to extract date from
     * @return <date> component of command
     * @throws DukeException
     */
    private static String getDate(String command) throws DukeException{
        String date = "";
        int startDateIndex = command.indexOf("/") + 2;
        if (startDateIndex < 0) {
            throw new DukeException("Command does not follow pattern ... /<at/by> <date in HH:MM DD:MM:YYYY>\n>>");
        } else {
            date = command.substring(startDateIndex).trim();
        }
        if (date.equals("")) {
            throw new DukeException("Empty date field\n>>");
        }
        return date;
    }

    public static void main(String[] args) {
        greetUser();
        Scanner in = new Scanner(System.in);
        String s;
        while(true) {
            try {
                s = in.nextLine();
                handleInput(s);
            } catch (InputMismatchException ime) {
                System.out.print(ime);
                System.exit(0);
            } catch (DukeException de) {
                System.out.print(de);
            }
        }
    }
}
