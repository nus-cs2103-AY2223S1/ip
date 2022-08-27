package duke;

import java.io.IOException;


public class Duke {
    /**
     * Main class that contains duke,
     * Duke keeps track of all tasks to be done
     *
     */

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Runs by getting data from text file in filepath
     * Data contains list of tasks to be done
     *
     * @param filePath Contains text file with list of tasks or empty if no tasks yet
     * @throws DukeException Exception thrown if file empty
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            ui.hello();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
        public void run() throws DukeException, IOException {
        parser = new Parser(tasks,storage);
        parser.read();

        }

    /**
     * Main method to run duke program
     * @param args
     * @throws DukeException
     * @throws IOException
     */
        public static void main(String[] args) throws DukeException, IOException {
            new Duke("data/duke.txt").run();
        }
    }
