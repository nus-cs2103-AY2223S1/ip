package bocil;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Wraps the UI, parser storage, and task list as a Bocil object.
 */
public class Bocil {

    private static final String NAME = "Bocil";
    private static final String FILE_DIRECTORY_STRING = "./data/";
    private static final String FILE_NAME = "duke.txt";
    private final Storage storage;
    private final Ui ui;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructs a {@link Bocil} object using pre-defined name and storage directory.
     */
    public Bocil() {
        this.storage = new Storage(FILE_DIRECTORY_STRING, FILE_NAME);
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui(NAME);
    }

    /**
     * Runs the main logic of the application.
     */
    public static void main(String[] args) {
        Bocil bocil = new Bocil();
        bocil.initialize();
        bocil.run();
    }

    /**
     * Prints the introduction line of the UI.
     */
    public void initialize() {
        try {
            this.taskList = this.storage.readFile();
            this.parser = new Parser(this.taskList);
            this.ui.introduce();
        } catch (BocilException e) {
            this.ui.printOutput(this.ui.showError(e));
        }
    }

    /**
     * Accepts user input and prints the response.
     */
    public void run() {
        while (true) {
            String input = this.ui.readInput();
            this.ui.printOutput(this.getResponse(input));
            if (this.ui.isEnd(input)) {
                break;
            }
        }
        this.storage.writeFile(this.taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input String that the user inputs.
     * @return Response of Bocil
     */
    public String getResponse(String input) {
        try {
            return this.parser.processInput(input);
        } catch (BocilException e) {
            return this.ui.showError(e);
        }
    }
}
