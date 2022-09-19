package duke;

import commands.Command;
import data.TaskList;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import utils.Pair;

/**
 * Duke class to encapsulate all logic involving the chatbot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Instantiates a Duke object with storage at some file path.
     *
     * @param filePath File path for storage.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public static String getWelcomeMessage() {
        return "Hello my name is Duke\nWhat can I do for you?";
    }

    public Pair<String, Boolean> getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        return new Pair<>(c.execute(tasks, storage), c.isExit());
    }
}
