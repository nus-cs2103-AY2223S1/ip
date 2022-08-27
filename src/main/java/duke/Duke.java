package duke;

import java.util.List;

import duke.command.Command;

/**
 * Simple chatbot that reacts on user input.
 */
public class Duke {
    public static final String DEFAULT_STORAGE_PATH = "data/duke.txt";

    private StorageInterface storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     * Initialises, Ui, TaskList and Storage used by the application.
     *
     * @param storagePath Filepath to use to store Tasks.
     */
    Duke(String storagePath) {
        this.storage = new Storage(storagePath);
        this.taskList = new TaskList();
        Command.setStorage(this.storage);
    }

    /**
     * Starts the program by loading saved tasks from the storage file and returning
     * a welcome message.
     *
     * @return A list containg the welcome message and load result message.
     */
    public List<String> start() {
        String loadResultMessage = loadSavedTasks();
        return List.of(Ui.getWelcomeMessage(), loadResultMessage);
    }

    /**
     * Creates and executes a command based on ther user input string and returns
     * the execution message.
     *
     * @param input Input string from user.
     * @return Command execution result message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String returnMessage = command.execute();
            return returnMessage;
        } catch (DukeException e) {
            return Ui.getErrorMessage(e.getMessage());
        }
    }

    private String loadSavedTasks() {
        try {
            this.taskList = new TaskList(this.storage.readFile());
            return "Successfully loaded from storage file.";
        } catch (DukeException e) {
            return e.getMessage();
        } finally {
            Command.setTaskList(this.taskList);
        }
    }
}
