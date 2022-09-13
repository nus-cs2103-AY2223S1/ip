package duke;

import java.io.IOException;
import javafx.util.Pair;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a chatbot named Duke.
 * It helps a person to keep track of various things.
 *
 * @author Rexong
 */
public class Duke {
    private static final String DUKE_INTRODUCTION = "Hello, I'm your personal assistant, Duke.";
    private static final String DUKE_HELP = "How can I assist you today?";

    private static final String FILE_NAME = "duke.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a duke object which consist of Ui, Storage and TaskList.
     */
    public Duke() throws IOException {
        storage = new Storage(FILE_NAME);
        tasks = storage.setUp();
    }

    public Pair<String, Boolean> getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return new Pair<>(command.execute(tasks, storage), false);
        } catch (DukeException e) {
            return new Pair<>(e.getMessage(), true);
        }
    }

    public static String welcomeText() {
        return DUKE_INTRODUCTION + "\n" + DUKE_HELP;
    }

}




















