package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * Represents the Duke application.
 * Responsible for user interaction.
 */
public class Duke {
    private final Parser parser;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructor for a Duke application instance.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage("data", "data/tasks");

        TaskList tasks;
        try {
            // Attempt to load tasks from storage.
            tasks = storage.load();
        } catch (DukeException e) {
            e.printStackTrace();
            // Load empty list if fail to load from storage.
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    /**
     * Gets the response from the Duke application based on a particular input.
     *
     * @param input User input provided.
     * @return Response from the Duke application.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            // Populate command with tasks.
            command.setData(tasks);
            CommandResult result = command.execute();
            if (result.shouldExit()) {
                Platform.exit();
            }
            if (result.shouldUpdateFile()) {
                // Save to storage.
                storage.save(tasks);
            }
            return result.getUserMessage();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
