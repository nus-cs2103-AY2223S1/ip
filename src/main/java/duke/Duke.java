package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

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

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static String WelcomeText() {
        return DUKE_INTRODUCTION + "\n" +  DUKE_HELP;
    }

}




















