package duke;

import java.io.IOException;

/**
 * Main class.
 *
 * @author Safwan A0235287X
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke main class.
     * @param filePath Location of the file that stores the data as a result of running the main class.
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to execute the Duke programme.
     * @throws DukeException
     * @throws IOException
     */
    public void run() throws DukeException, IOException {
        ui.greetings();
        Parser parser = new Parser(tasks);
        parser.readInput();
    }

    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }





}
