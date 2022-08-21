import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    TaskList taskList;
    enum CommandType {TODO, MARK, UNMARK, DEADLINE, EVENT, BYE, LIST, DELETE, ISTODAY, LONGDESCRIPTION}

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
        this.taskList = new TaskList();
        greetUser();
    }

    /**
     * Menu handler
     * @param userInput text entered by user that must be handled.
     */
    private void handleInput(String userInput) throws DukeException, IOException{
        CommandType command = Parser.getCommand(userInput);
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
                taskList.showHistory();
                break;
            case MARK:
                numOfElements  = taskList.numberOfTasksInList();
                n = Parser.getTaskNumber(userInput, numOfElements);
                taskList.markTask(n);
                break;
            case UNMARK:
                numOfElements  = taskList.numberOfTasksInList();
                n = Parser.getTaskNumber(userInput, numOfElements);
                taskList.unMarkTask(n);
                break;
            case EVENT:
                Event e = Parser.stringToEvent(userInput);
                taskList.addEventToHistory(e);
                break;
            case DEADLINE:
                Deadline d = Parser.stringToDeadline(userInput);
                taskList.addDeadlineToHistory(d);
                break;
            case TODO:
                Task t = Parser.stringToTask(userInput);
                taskList.addTaskToHistory(t);
                break;
            case DELETE:
                numOfElements = taskList.numberOfTasksInList();
                n = Parser.getTaskNumber(userInput, numOfElements);
                taskList.deleteTask(n);
                break;
            case ISTODAY:
                numOfElements = taskList.numberOfTasksInList();
                n = Parser.getTaskNumber(userInput, numOfElements);
                taskList.isToday(n);
                break;
            case LONGDESCRIPTION:
                numOfElements = taskList.numberOfTasksInList();
                n = Parser.getTaskNumber(userInput, numOfElements);
                taskList.longDescription(n);
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
            } catch (DukeException de) {
                System.out.print(de);
            } catch (IOException ioe) {
                System.out.print(ioe);
            }
        }
    }
}
