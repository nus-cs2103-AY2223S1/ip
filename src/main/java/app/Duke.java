package app;

import objects.Task;
import utils.Parser;
import utils.Storage;
import utils.TaskList;

import java.io.IOException;
import java.util.List;

/**
 * The Duke class has all the ui and util objects,
 * and handles the bot response to the command inputs
 * the user types in.
 */
public class Duke {
    private static TaskList taskList;
    private static List<Task> tasks;
    private static Storage storage;
    private static Parser parser;

    public Duke() {
        taskList = new TaskList();
        tasks = taskList.getTasks();
        storage = new Storage();
        parser = new Parser();
    }

    /**
     * Loads tasks from hard disk to the app.
     * @throws IOException exception related to input or output
     */
    public static void loadDuke() throws IOException {
        assert tasks.size() == 0;
        storage.loadTasks(tasks);
    }

    /**
     * Saves tasks from the app to the hard disk.
     * @throws IOException exception related to input or output
     */
    public static void saveDuke() throws IOException {
        storage.saveTasks(tasks);
    }

    /**
     * Uses the parser to parse the input by the user,
     * and creates the appropriate bot response.
     * @param input the user's command input
     * @return response of the bot to the user's command input
     */
    public String getResponse(String input) {
        return "Duck says: \n\n" + parser.parseCommand(tasks, input);
    }
}
