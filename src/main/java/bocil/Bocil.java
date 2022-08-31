package bocil;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Wraps the UI, parser storage, and task list as a Bocil object.
 */
public class Bocil {
    private static final String NAME = "Windah Bocil";
    private static final String TITLE = "Bocil";
    private final Storage storage;
    private final Ui ui;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructs a {@link Bocil} object using pre-defined name and storage directory.
     *
     * @param fileDirectoryString Folder directory of the task list file.
     * @param fileName File name for the task list file.
     */
    public Bocil(String fileDirectoryString, String fileName) {
        this.storage = new Storage(fileDirectoryString, fileName);
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * Prints the introduction line of the UI.
     */
    public void initialize() {
        try {
            this.taskList = this.storage.readFile();
            this.parser = new Parser(this.taskList);
        } catch (BocilException e) {
            this.ui.printOutput(this.ui.showError(e));
        }
    }

    /**
     * Shows the introduction line of the UI.
     *
     * @return Introduction line.
     */
    public String getTitle() {
        return TITLE;
    }

    /**
     * Shows the introduction line of the UI.
     *
     * @return Introduction line.
     */
    public static String introduce() {
        String line1 = String.format("Hello! I'm %s", NAME);
        String line2 = "What can I do for you?";
        return String.join("\n", line1, line2);
    }

    /**
     * Accepts user input and shows the appropriate response.
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

    /**
     * Saves the current task list.
     */
    public void endProgram() {
        this.storage.writeFile(this.taskList);
    }
}
