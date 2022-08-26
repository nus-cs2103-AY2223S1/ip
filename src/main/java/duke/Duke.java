package duke;

import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        ui.hello();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }
        public void run() throws DukeException, IOException {
        parser = new Parser(tasks,storage);
        parser.read();

        }

        public static void main(String[] args) throws DukeException, IOException {
            new Duke("data/duke.txt").run();
        }
    }
