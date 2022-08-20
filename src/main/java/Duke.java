import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    UserInputHistory userInputHistory = new UserInputHistory();
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
    public void markTask(int n) throws IOException {
        Task taskToModify = userInputHistory.getTask(n);
        taskToModify.markAsDone();
        System.out.printf("Marked task %d \n%s\n", n, taskToModify);
        System.out.print(">>");
    }

    /**
     * Un-mark task at index n in list.
     * No checks performed to check if task is already unmarked.
     * @param n task to mark as not done (n - 1) index in actual list
     */
    public void unmarkTask(int n) throws IOException {
        Task taskToModify = userInputHistory.getTask(n);
        taskToModify.markAsNotDone();
        System.out.printf("Unmarked task %d \n%s\n", n, taskToModify);
        System.out.print(">>");
    }

    /**
     * Filter userInput and call addEventToHistory
     * @param event
     */
    private void handleEvent(String event) throws DukeException{
        String description, at;
        description = getDescription(event, "event");
        at = getDate(event);
        userInputHistory.addEventToHistory(description, at);
    }

    /**
     * Filter userInput and call addDeadlineToHistory
     * @param deadline
     */
    private  void handleDeadline(String deadline) throws DukeException{
        String description, by;
        description = getDescription(deadline, "deadline");
        by = getDate(description);
        userInputHistory.addDeadlineToHistory(description, by);
        }

    /**
     * Filter userInput and call addDeadlineToHistory
     * @param task
     */
    private  void handleTask(String task) throws DukeException {
        String description;
        description = getDescription(task, "todo");
        userInputHistory.addTaskToHistory(description);
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
    private void handleInput(String userInput) throws DukeException, IOException{
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
                userInputHistory.showHistory();
                break;
            case MARK:
                markTask(userInputHistory.getTaskNumber(userInput));
                break;
            case UNMARK:
                unmarkTask(userInputHistory.getTaskNumber(userInput));
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
                userInputHistory.deleteTask(userInputHistory.getTaskNumber(userInput));
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
     * @param command is the string to extract date from
     * @return <date> component of command
     * @throws DukeException
     */
    private static String getDate(String command) throws DukeException{
        String date = "";
        int startDateIndex = command.indexOf("/") + 3;
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
        Duke dukeProgram = new Duke();
        Duke.greetUser();
        Scanner in = new Scanner(System.in);
        String s;
        while(true) {
            try {
                s = in.nextLine();
                dukeProgram.handleInput(s);
            } catch (InputMismatchException ime) {
                System.out.print(ime);
                System.exit(0);
            } catch (DukeException de) {
                System.out.print(de);
            } catch (IOException ioe) {
                System.out.print(ioe);
            }
        }
    }
}
