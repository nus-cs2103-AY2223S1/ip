package bocil;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Links the UI, parser and storage as a Duke object.
 */
public class Bocil {

    private static final String NAME = "Bocil";
    private static final String FILE_DIRECTORY_STRING = "./data/";
    private static final String FILE_NAME = "duke.txt";
    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a {@link Bocil} object using pre-defined name and storage directory.
     *
     * @throws BocilException If file is unable to be loaded.
     */
    public Bocil() throws BocilException {
        this.storage = new Storage(FILE_DIRECTORY_STRING, FILE_NAME);
        this.taskList = this.storage.readFile();
        this.parser = new Parser(this.taskList);
        this.ui = new Ui(NAME);
    }

    /**
     * Runs the main logic of the application.
     *
     * @throws BocilException If file is unable to be loaded.
     */
    public static void main(String[] args) throws BocilException {
        Bocil bocil = new Bocil();
        bocil.initialize();
        bocil.run();
    }

    /**
     * Prints the introduction line of the UI.
     */
    public void initialize() {
        this.ui.introduce();
    }

    /**
     * Accepts user input and prints the response.
     */
    public void run() {
        while (true) {
            String input = this.ui.readInput();
            try {
                this.ui.printOutput(this.parser.processInput(input));
                if (this.ui.isEnd(input)) {
                    break;
                }
            } catch (BocilException e) {
                this.ui.printError(e);
            }
        }
        this.storage.writeFile(this.taskList);
    }
}
