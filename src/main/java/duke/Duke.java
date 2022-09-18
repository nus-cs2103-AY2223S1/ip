package duke;

import java.nio.file.Path;
import java.util.function.Consumer;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.DataFileCorruptedException;
import duke.storage.Storage;
import duke.task.TaskList;
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
            // If we are here, it means the load did succeed but the data file is corrupted.
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage() + "\n"
                    + "Do you want to reset the datafile?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                tasks = new TaskList();
                storage.save(tasks);
            } else {
                System.exit(0);
            }
        } catch (DukeException e) {
            // If we are here, it means data file could not be created.
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage() + "\n"
                    + "Check if the file permissions are set correctly.", ButtonType.OK);
            alert.showAndWait();
            System.exit(0);
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
