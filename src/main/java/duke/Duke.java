package duke;

import duke.commands.Command;

import javafx.application.Platform;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
        } catch (Exception e) {
            output = "Unable to close properly";
        }
        return "Duke heard: " + output;
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
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }


    /**
     * Executes the program.
     * @param args main method.
     */
    public static void main(String[] args) {
        /* TODO: Add the printing of default statements on startup of the application */
        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");
    }
}
