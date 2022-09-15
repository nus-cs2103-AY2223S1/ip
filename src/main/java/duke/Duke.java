package duke;

import java.time.format.DateTimeParseException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import duke.commands.Command;
import javafx.application.Platform;

/**
 * Main class encapsulating the logic of the program.
 */
public class Duke {
    private static final String FILE_PATH_DIR = "data";

    private Storage storage;

    private TaskList tasks;

    /**
     * Default constructor for Duke.
     */
    public Duke() {
        this.storage = new Storage(FILE_PATH_DIR);
        this.tasks = storage.load();
    }

    /**
     * Returns the response of duke given an input from user.
     *
     * @param input String input from user.
     * @return String output of duke.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        String output;
        try {
            Command command = parser.parseInput(input);
            output = command.execute(tasks, storage);
            if (command.isBye()) {
                exitService();
            }
        } catch (DukeException e) {
            output = e.toString();
        } catch (NumberFormatException e) {
            output = "Input String cannot be parsed to Integer.";
        } catch (DateTimeParseException e) {
            output = "Input Date cannot be parsed to Date!\nPlease enter yyyy-MM-dd HHmm";
        }
        return output;
    }

    /**
     * Stops the program in 3 seconds.
     */
    public void exitService() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }
}
