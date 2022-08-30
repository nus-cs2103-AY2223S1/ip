package duke;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.TaskList;

/**
 * Main class used to handle inputs
 */
public class Duke {
    private TaskList list;
    private Storage storage;

    /**
     * Constructor of the duke.Duke class given a filepath
     * @param filePath path of file to be saved/loaded from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        list = new TaskList(storage.load());
    }

    /**
     * This method runs the duke program
     */
    public void run() {
        Ui.welcome();
    }

    public String getResponse(String input) {
        return Parser.parse(input, list);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
