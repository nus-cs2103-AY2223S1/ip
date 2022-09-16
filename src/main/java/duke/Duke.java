package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.gui.Launcher;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.utils.OutputHandler;


/**
 * Initializer for the overall duke program.
 * Starts interaction with the user.
 *
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private boolean isExit;


    /**
     * Constructor for a Duke object.
     *
     * Instantiates a new Duke object and reads input file.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        storage.readSavedTasks();
    }

    /**
     * Gets response from parser based on user input.
     * @param input User input.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command action = parser.handleGuiInput(input);
            OutputHandler handler = new OutputHandler();
            String outputForGui = handler.getOutput(action);

            if (action instanceof ByeCommand) {
                this.setExit();
            }

            assert !outputForGui.equals("");
            return outputForGui;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getIntro() {
        return ui.getWelcomeMessage();
    }

    private void setExit() {
        this.isExit = true;
    }

    public boolean getExit() {
        return this.isExit;
    }

    /**
     * Starts the GUI application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
