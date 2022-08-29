package duke.chatbot.storage;

import java.io.File;
import java.io.FileNotFoundException;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.parser.Parser;

/**
 * A file loader which loads tasks into the application task list
 * during runtime.
 */
public class TaskFileLoader {
    /** The file to load the list of tasks from */
    private final File file;

    protected TaskFileLoader(File file) {
        this.file = file;
    }

    /**
     * Return list of tasks after loading them from the stored file.
     * @return A list of tasks loaded from the stored file.
     * @throws FileNotFoundException If a file is not found.
     * @throws InvalidInputException If the date and time portion
     *     of the encoded task is not in the correct format.
     */
    public TaskList getTaskList() throws FileNotFoundException, InvalidInputException {
        return Parser.parseFile(file);
    }
}
