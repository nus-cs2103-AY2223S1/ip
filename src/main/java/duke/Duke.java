package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.gui.Launcher;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * Initializer for the overall duke program.
 * Starts interaction with the user.
 *
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;


    /**
     * Constructor for a Duke object.
     *
     * Instantiates a new Duke object and reads input file.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();

        storage.readSavedTasks();
    }

    /**
     * Main method that starts up the chatbot (CLI version).
     * Starts the boot-up phase and calls method to handle user input.
     */
    public void run() {
        ui.bootUpDuke();
        loopInputRead();
    }

    /**
     * Reads and handles user input until parser returns boolean to indicate end.
     *
     */
    private void loopInputRead() {
        Parser parser = new Parser(new Scanner(System.in));
        while (true) {
            try {
                boolean complete = parser.handleInput();
                if (complete) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Gets response from parser based on user input.
     * @param input User input.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(new Scanner(System.in));
            return parser.handleGuiInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getIntro() {
        return ui.getWelcomeMessage();
    }

    /**
     * Main method that initialises an instance of the Duke chatbot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
