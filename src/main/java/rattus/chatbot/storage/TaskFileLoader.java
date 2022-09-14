package rattus.chatbot.storage;

import java.io.File;
import java.io.FileNotFoundException;

import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.data.task.TaskList;
import rattus.chatbot.util.Parser;

/**
 * A file loader which loads tasks into the application task list during runtime.
 *
 * @author jq1836
 */
public class TaskFileLoader {
    /**
     * The file to load the list of tasks from.
     */
    private final File file;

    protected TaskFileLoader(File file) {
        this.file = file;
    }

    /**
     * Returns an instance of {@link TaskList} after loading them from the stored file.
     *
     * @return A list of tasks loaded from the stored file.
     * @throws FileNotFoundException If a file is not found.
     * @throws InvalidInputException If the date and time portion of the encoded task is not in the correct format.
     */
    public TaskList getTasks() throws FileNotFoundException {
        return Parser.parseFile(file);
    }
}
