package duke;

import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author totsukatomofumi
 */
public class Duke {
    /** Storage object for writing task history to a file. */
    private Storage storage;

    /** Task list for Duke to remember. */
    private TaskList taskList;

    /** User interface to communicate. */
    private Ui ui;

    /** Parser to parse user responses. */
    private Parser parser;

    /**
     * Constructor for Duke.
     *
     * @param filePath the file path of the stored task history.
     */
    private Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage);
        this.parser = new Parser(this.taskList);
        this.ui = new Ui();
    }

    /**
     * Starts Duke up.
     */
    private void run() {
        ui.start(parser);
    }

    public static void main(String[] args) {
        new Duke("./data/history.txt").run();
    }
}


