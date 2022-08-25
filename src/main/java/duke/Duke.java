package duke;

import duke.command.Action;
import duke.command.Command;
import duke.exception.DukeException;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    private final HashMap<Action, Consumer<Command>> actionConsumerMap = new HashMap<>();
    private boolean isTerminated;
    private duke.MessagePrinter messagePrinter;
    private TaskList tasks;
    private Storage storage;

    public Duke(String storagePath) {
        this.tasks = new TaskList();
        this.messagePrinter = new MessagePrinter(3, 100, '-');
        this.storage = new Storage(storagePath);
        this.execute(Command.greet());
    }

    public static void main(String[] args) {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        new Duke(storagePath).run();
    }

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

    public boolean getIsTerminated() {
        return this.isTerminated;
    }

    public void execute(Command command) {
        command.execute(this.tasks, this.messagePrinter, this.storage);
    }

    public void handle(DukeException dukeException) {
        this.messagePrinter.printMessage(dukeException.getMessage());
    }
}
