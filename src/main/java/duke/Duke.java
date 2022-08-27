package duke;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import duke.command.Action;
import duke.command.Command;
import duke.exception.DukeException;
import duke.util.MessagePrinter;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;


/**
 * The Duke.
 */
public class Duke {
    private final HashMap<Action, Consumer<Command>> actionConsumerMap = new HashMap<>();
    private boolean isTerminated;
    private final MessagePrinter messagePrinter;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * The constructor of the Duke with given storage path.
     * @param storagePath The given storage path.
     */
    public Duke(String storagePath) {
        this.tasks = new TaskList();
        this.messagePrinter = new MessagePrinter(3, 100, '-');
        this.storage = new Storage(storagePath);
        this.execute(Command.greet());
    }

    /**
     * The main method of Duke.
     * @param args The arguments.
     */
    public static void main(String[] args) {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        new Duke(storagePath).run();
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
                    Command command = Parser.parseCommand(entry);
                    this.execute(command);
                    this.isTerminated = command.isTerminated();
                }
            } catch (DukeException dukeException) {
                this.handle(dukeException);
            }
        }
    }

    /**
     * The method to execute a givenCommand.
     * @param command The given Command.
     */
    public void execute(Command command) {
        command.execute(this.tasks, this.messagePrinter, this.storage);
    }

    /**
     * The method to print given DukeException Details.
     * @param dukeException The given DukeException.
     */
    public void handle(DukeException dukeException) {
        this.messagePrinter.printMessage(dukeException.getMessage());
    }
}
