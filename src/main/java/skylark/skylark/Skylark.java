package skylark.skylark;

import java.io.File;
import java.util.Scanner;

import skylark.command.Command;
import skylark.command.CommandList;
import skylark.task.TaskList;
import skylark.utils.Printer;

/** Represents the main entry point of Skylark. */
public class Skylark {

    /** Text that is shown to the user when they first access Skylark. */
    private static final String TEXT_HELLO = "Hello, I am Skylark, how can I help you today?";

    /** Represents a Scanner object that retrieves user input from the command line. */
    private final Scanner scan;

    /** Represents the TaskList object that stores the list of Tasks created by the user. */
    private final TaskList taskList;

    /**
     * Represents a Skylark object. <br><br>
     * Initialises the scanner to retrieve user input from the command line,
     * and creates a new TaskList object to read the current list of the tasks stored in a file path
     * specified. <br><br>
     * Prints a hello message to the user.
     */
    public Skylark() {
        this.scan = new Scanner(System.in);
        String filePath = System.getProperty("user.dir")
                + File.separator + "data" + File.separator + "text.txt";
        this.taskList = new TaskList(filePath);

        Printer.printText(Skylark.TEXT_HELLO);
    }

    /**
     * Starts Skylark until the user issues the Bye command.
     */
    public void startRunning() {
        while (true) {
            try {
                boolean isBye = response(scan, taskList);
                if (isBye) {
                    break;
                }
            } catch (SkylarkException exception) {
                Printer.printText(exception.toString());
            }
        }
    }

    /**
     * Parses the next line inputted by the user in the command line. <br><br>
     * Parses the command input and creates the relevant Command object. <br><br>
     * Runs the command by executing the run function of the Command object.
     *
     * @param scan Scanner object.
     * @param taskList TaskList object.
     * @return Boolean whether the command parsed is equals to the Bye command.
     * @throws SkylarkException If there is an exception that occurred when running the command.
     */
    private boolean response(Scanner scan, TaskList taskList) throws SkylarkException {
        String input = scan.nextLine();
        Command command = Command.createCommand(input);
        command.run(taskList);

        return command.getInput().equals(CommandList.COMMAND_BYE.toString());
    }

    /**
     * Represents the main entry point of the Skylark program. <br><br>
     * Creates a new Skylark object and calls the startRunning function to create the input loop.
     *
     * @param args Command-line parameters that can be passed into Skylark.
     */
    public static void main(String[] args) {
        Skylark skylark = new Skylark();
        skylark.startRunning();
    }
}
