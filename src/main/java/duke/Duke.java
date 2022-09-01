package duke;

import java.nio.file.Path;
import java.util.function.Consumer;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.DataFileCorruptedException;
import duke.util.Parser;
import duke.util.Storage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The main class of the Duke chat-bot.
 */
public class Duke {

    private final Storage storage;
    private final Consumer<String> printer;
    private TaskList tasks;

    /**
     * Constructs a new {@code Duke} with a datafile path.
     *
     * @param path    The path to the datafile.
     * @param printer A {@code Consumer<String>} that prints a message to GUI.
     */
    public Duke(Path path, Consumer<String> printer) {
        this.printer = printer;
        this.storage = new Storage(path);
        try {
            tasks = new TaskList(storage.load());
        } catch (DataFileCorruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage() + "\n"
                    + "Do you want to reset the datafile?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                tasks = new TaskList();
                storage.save(tasks);
            } else {
                System.exit(0);
            }
        }
        printer.accept("Welcome to Duke!");
    }

    /**
     * Executes a command.
     *
     * @param command The command to execute.
     */
    public void execute(String command) {
        try {
            Command cmd = Parser.parseCommand(command);
            cmd.execute(storage, printer, tasks);
        } catch (Exception e) {
            printer.accept(e.getMessage());
        }
    }
}
