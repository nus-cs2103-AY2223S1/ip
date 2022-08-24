package duke;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {

    protected final Parser parser;
    protected final Storage storage;
    protected final TaskList taskList;
    protected final Ui ui;

    protected static final String DUKE_NAME = "Bocil";
    protected static final String FILE_DIRECTORY_STRING = "./data/";
    protected static final String FILE_NAME = "duke.txt";

    public Duke() throws DukeException {
        this.parser = new Parser();
        this.storage = new Storage(FILE_DIRECTORY_STRING, FILE_NAME);
        this.taskList = this.storage.readFile();
        this.ui = new Ui(DUKE_NAME, taskList);
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.initialize();
        duke.run();
    }

    public void initialize() {
        this.ui.introduce();
    }

    public void run() {
        while (true) {
            String input = this.ui.readInput();
            this.ui.printOutput(this.parser.processInput(input, taskList));
            if (input.equals("bye")) {
                break;
            }
        }
        this.storage.writeFile(this.taskList);
    }
}

