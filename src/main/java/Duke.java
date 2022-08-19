import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class that executes the Duke chatbot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Duke {
    /** List of tasks. */
    static ArrayList<Task> taskList = new ArrayList<>();

    /** Ui that prints statements for the bot. */
    static Ui ui = new Ui();

    /**
     * Determines whether a command line inputted by
     * the user is a valid command.
     *
     * @param command Command line that is inputted by the user.
     * @return A boolean value that states whether the command exists or not.
     */
    public static boolean doesCommandExist(String command) {
        return command.equals("list") || command.equals("mark")
                || command.equals("unmark") || command.equals("deadline") || command.equals("event")
                || command.equals("todo") || command.equals("delete") || command.equals("bye");
    }

    /**
     * Detects the commands inputted by a user; if the command exists,
     * it will execute the given command. Otherwise, the ui will
     * print a message saying that the command does not exist.
     *
     * @param typeOfCommandLine The type of command line inputted by the user.
     * @param userInput The whole string inputted by the user.
     */
    public static void detectCommand(CommandLine typeOfCommandLine, String userInput) {
        try {
            switch (typeOfCommandLine) {
                case BYE:
                    ui.exit();
                    break;
                case LIST:
                    ui.list(taskList);
                    break;
                case MARK:
                    Commands.mark(userInput, taskList);
                    break;
                case UNMARK:
                    Commands.unmark(userInput, taskList);
                    break;
                case DEADLINE:
                    Commands.deadline(userInput, taskList);
                    break;
                case EVENT:
                    Commands.event(userInput, taskList);
                    break;
                case TODO:
                    Commands.toDo(userInput, taskList);
                    break;
                case DELETE:
                    Commands.delete(userInput, taskList);
                    break;
                default:
                    ui.commandDoesNotExist();
                    break;
            }
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }

    /**
     * Executes the bot.
     *
     * @param args Main arguments.
     */
    public static void main(String[] args) {
        ui.greet();

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (true) {
            String commandLine = userInput.split(" ")[0];
            if (doesCommandExist(commandLine)) {
                CommandLine typeOfCommandLine = CommandLine.valueOf(commandLine.toUpperCase());
                detectCommand(typeOfCommandLine, userInput);
                if (typeOfCommandLine == CommandLine.BYE) {
                    return;
                }
            } else {
                ui.commandDoesNotExist();
            }
            userInput = sc.nextLine();
        }
    }

    /**
     * Enum class to represent the command lines that exist.
     */
    enum CommandLine {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE,
    }
}
