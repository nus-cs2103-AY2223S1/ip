import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Duke {
    DukeToStorage dukeToStorage;
    enum CommandType {TODO, MARK, UNMARK, DEADLINE, EVENT, BYE, LIST, DELETE, ISTODAY, LONGDESCRIPTION};

    /**
     * Greets user.
     */
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
     * Intialises duke storage and greets user.
     */
    public Duke() {
        this.dukeToStorage = new DukeToStorage();
        greetUser();
    }

    /**
     * Menu handler
     * @param userInput text entered by user that must be handled.
     */
    private void handleInput(String userInput) throws DukeException, IOException{
        CommandType command = TypeConverter.getCommand(userInput);
        int n, numOfElements;
        if (command == null) {
            throw new DukeException( "Enter a valid command (todo, event, deadline, list, mark, unmark, bye, longdescription, istoday)\n>>");
        } else {
            switch(command) {
            case BYE:
                System.out.println("Thank you for swinging by :)");
                System.exit(0);
                break;
            case LIST:
                dukeToStorage.showHistory();
                break;
            case MARK:
                numOfElements  = dukeToStorage.numberOfTasksInList();
                n = TypeConverter.getTaskNumber(userInput, numOfElements);
                dukeToStorage.markTask(n);
                break;
            case UNMARK:
                numOfElements  = dukeToStorage.numberOfTasksInList();
                n = TypeConverter.getTaskNumber(userInput, numOfElements);
                dukeToStorage.unMarkTask(n);
                break;
            case EVENT:
                Event e = TypeConverter.stringToEvent(userInput);
                dukeToStorage.addEventToHistory(e);
                break;
            case DEADLINE:
                Deadline d = TypeConverter.stringToDeadline(userInput);
                dukeToStorage.addDeadlineToHistory(d);
                break;
            case TODO:
                Task t = TypeConverter.stringToTask(userInput);
                dukeToStorage.addTaskToHistory(t);
                break;
            case DELETE:
                numOfElements = dukeToStorage.numberOfTasksInList();
                n = TypeConverter.getTaskNumber(userInput, numOfElements);
                dukeToStorage.deleteTask(n);
                break;
            case ISTODAY:
                numOfElements = dukeToStorage.numberOfTasksInList();
                n = TypeConverter.getTaskNumber(userInput, numOfElements);
                dukeToStorage.isToday(n);
                break;
            case LONGDESCRIPTION:
                numOfElements = dukeToStorage.numberOfTasksInList();
                n = TypeConverter.getTaskNumber(userInput, numOfElements);
                dukeToStorage.longDescription(n);
                break;
            default:
                throw new DukeException("Enter a valid command (todo, event, deadline, list, mark, unmark, bye)\n>>");
            }

        }
    }

    public static void main(String[] args) {
        Duke dukeProgram = new Duke();
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
