package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * This class encapsulates Duke and its functionalities.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Creates a new Duke to chat with.
     *
     * @throws DukeException If there is a problem retrieving the saved tasks.
     */
    public Duke() throws DukeException {
        this.storage = new Storage();
        this.taskList = this.storage.load();
    }

    /**
     * Parses the given text using the Parser.
     *
     * @param text The text to parse.
     * @return The corresponding command after parsing the text.
     */
    public Command parseText(String text) {
        return Parser.parseText(text, this.taskList);
    }

    /**
     * Saves the TaskList to the hard disk.
     *
     * @throws DukeException If there is a problem with writing to the hard disk.
     */
    public void saveTasks() throws DukeException {
        this.storage.save(this.taskList);
    }
}
