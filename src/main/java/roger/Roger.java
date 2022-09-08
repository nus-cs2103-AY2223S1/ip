package roger;

import java.io.IOException;
import java.util.List;

import roger.commands.Command;
import roger.exceptions.RogerInvalidInputException;
import roger.storage.Storage;
import roger.storage.StorageParser;
import roger.tasks.TaskList;
import roger.ui.Parser;
import roger.ui.Response;

/**
 * Main class for the Roger task-tracking GUI program.
 */
public class Roger {
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    /**
     * Create an instance of the Roger program.
     *
     */
    public Roger(Parser parser, Storage storage) {
        this.parser = parser;
        this.storage = storage;
        this.tasks = new TaskList();
        this.loadStorage();
    }

    private void loadStorage() {
        try {
            List<String> taskStrings = this.storage.load();
            for (String taskString : taskStrings) {
                this.tasks.add(StorageParser.toTask(taskString));
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Storage is corrupted. Starting over with a fresh database.");
        }
    }

    /**
     * Save tasks to the database.
     */
    public void saveTasksToDatabase() {
        assert tasks != null;
        assert storage != null;

        try {
            List<String> newTaskStrings = this.tasks.toTaskStrings();
            storage.write(newTaskStrings);
        } catch (IOException e) {
            System.out.println("Unable to write tasks to database. Check the path provided.");
        }
    }

    /**
     * Returns Roger's response to the user input
     *
     * @param input User input
     * @return Roger's response.
     */
    public Response getResponse(String input) {
        assert tasks != null;
        assert parser != null;
        assert storage != null;

        try {
            Command command = this.parser.parse(input);
            String message = command.execute(this.tasks, this.storage);
            return new Response(message, command.isExit(), false);
        } catch (RogerInvalidInputException e) {
            return new Response(e.getMessage(), false, true);
        }
    }

    /**
     * Returns Roger's greeting to the user upon program startup
     *
     * @return Roger's greeting.
     */
    public Response getGreeting() {
        String greeting = "Hello, this is Roger. \n"
                + "What you wan? What you wan?";
        return new Response(greeting, false, false);
    }
}
