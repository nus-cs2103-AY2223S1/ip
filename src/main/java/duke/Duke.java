package duke;

import java.nio.file.Path;

import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Io;
import duke.util.DataFileCorruptedException;
import duke.util.Parser;
import duke.util.Storage;

/**
 * The main class of the Duke chat-bot.
 */
public class Duke {

    private final Storage storage;
    private final Io io;
    private TaskList tasks;

    /**
     * Constructs a new {@code Duke} with a datafile path.
     *
     * @param path The path to the datafile.
     */
    public Duke(Path path) {
        storage = new Storage(path);
        io = new Io(System.in, System.out);
        try {
            tasks = new TaskList(storage.load());
        } catch (DataFileCorruptedException e) {
            io.print(e.getMessage());
            if (io.readYesNoResponse("Do you want to reset the data file?")) {
                tasks = new TaskList();
                storage.save(tasks);
            } else {
                io.exit();
                System.exit(0);
            }
        }
    }

    /**
     * Executes a command.
     *
     * @param command The command to execute.
     */
    public String execute(String command) {
        try {
            Command c = Parser.parseCommand(command);
            return c.execute(storage, tasks).replaceAll("\t", "    "); // JavaFX 11 doesn't support tab sizes
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
