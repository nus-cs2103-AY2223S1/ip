package duke;


import java.io.IOException;

/**
 * Represents the entire chatbot. Central class that initialises all chatbot variables and runs the chatbot
 */
public class Duke {

    private final Storage fileStorage;
    private final TaskList tasks;


    /**
     * Initialises Duke chatbot
     * @throws IOException
     */
    public Duke() throws IOException {
        fileStorage = new Storage("tasks.txt");
        tasks = new TaskList(fileStorage.loadTasks());
    }


    public String getResponse(String input) throws IOException {
        Parser parser = new Parser(input);
        return parser.executeCommand(tasks, fileStorage);
    }

}
