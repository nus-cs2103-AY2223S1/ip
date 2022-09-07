package duke;

import static duke.utils.Ui.LOGO;
import static duke.utils.Ui.wrapWithLines;

import java.io.File;
import java.util.Scanner;

import duke.commands.CommandHandler;
import duke.commands.CommandHandlerFactory;
import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Duke is a program that helps its user to store tasks.
 */
public class Duke {

    private boolean isClosed;
    private final TaskList taskList;
    private final CommandHandlerFactory commandHandlerFactory;

    /**
     * Constructor of Duke.
     */
    public Duke() {
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                wrapWithLines("Could not create /duke.data directory");
            }
        }

        Storage db = new Storage("./data/duke.txt");
        taskList = db.load();
        commandHandlerFactory = new CommandHandlerFactory();
        isClosed = false;
    }

    /**
     * Checks if duke has terminated.
     * @return whether duke has terminated.
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Gets the response to the user.
     * @param input User input.
     * @return Duke's response to the user.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            this.isClosed = true;
        }
        try {
            CommandHandler commandHandler = commandHandlerFactory.getHandler(input);
            return commandHandler.handle(taskList);
        } catch (DukeException e) {
            return "☹ OOPS!!! " + e.getMessage();
        }
    }

    /**
     * The welcome message when duke initialises.
     * @return the welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello from\n" + LOGO;
    }

    private void initialise() {
        wrapWithLines("Hello from\n" + LOGO);

        Scanner sc = new Scanner(System.in);
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                CommandHandler commandHandler = commandHandlerFactory.getHandler(command);
                String message = commandHandler.handle(taskList);
                wrapWithLines(message);
            } catch (DukeException e) {
                wrapWithLines("☹ OOPS!!! " + e.getMessage());
            }
        }
        wrapWithLines("Bye. Hope to see you again soon!");
    }

    /**
     * Calls the initializer of Duke.
     * @param args any command line arguments given.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }
}
