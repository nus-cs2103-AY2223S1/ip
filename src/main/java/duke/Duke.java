package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.CompileException;
import duke.exception.DukeException;
import duke.exception.DukeRuntimeException;
import duke.util.MessagePrinter;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * The Duke.
 */
public class Duke {
    private boolean isTerminated;
    private MessagePrinter messagePrinter;
    private TaskList tasks;
    private Storage storage;

    /**
     * The constructor of the Duke with given storage path.
     *
     * @param storagePath The given storage path.
     */
    public Duke(String storagePath) {
        initialize(storagePath);
    }

    /**
     * The constructor of the Duke with given storage path.
     */
    public Duke() {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        initialize(storagePath);
    }

    /**
     * The main method of Duke.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        new Duke(storagePath).run();
    }

    private void initialize(String storagePath) {
        this.tasks = new TaskList();
        this.messagePrinter = new MessagePrinter(39, '*', 0);
        this.storage = new Storage(storagePath);
        this.execute(Command.greet());
    }

    /**
     * The method to launch Duke.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!this.isTerminated) {
            try {
                if (scanner.hasNext()) {
                    String entry = scanner.nextLine();
                    Command command = parse(entry);
                    execute(command);
                    this.isTerminated = command.isTerminated();
                }
            } catch (DukeException dukeException) {
                handle(dukeException);
            }
        }
    }

    private Command parse(String entry) throws CompileException {
        return Parser.parseCommand(entry);
    }

    /**
     * The method to execute a givenCommand.
     *
     * @param command The given Command.
     */
    public String execute(Command command) throws DukeRuntimeException {
        return command.execute(this.tasks, this.messagePrinter, this.storage);
    }

    /**
     * The method to print given DukeException Details.
     *
     * @param dukeException The given DukeException.
     */
    public String handle(DukeException dukeException) {
        return this.messagePrinter.printMessage(dukeException.getMessage());
    }

    /**
     * Returns the response of Duke with given input.
     *
     * @param input The given input.
     * @return The response of Duke
     */
    public String getResponse(String input) {
        try {
            if (isTerminated) {
                return "...";
            }
            Scanner scanner = new Scanner(input);
            if (scanner.hasNext()) {
                String entry = scanner.nextLine();
                Command command = parse(entry);
                this.isTerminated = command.isTerminated();
                return execute(command);
            }
        } catch (DukeException dukeException) {
            return handle(dukeException);
        }
        return null;
    }
}
