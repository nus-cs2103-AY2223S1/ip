package duke;

import duke.command.Command;
import duke.exception.DukeCommandAlreadyExecutedException;
import duke.ui.CliUi;
import duke.util.Parser;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * The only public-facing class that interacts directly with the user.
 * Duke is a personal time management virtual assistant, targeted at those who prefer using CLI instead of GUI.
 */
public class Duke {

    public static final String TAB = "    ";

    private static final String DEFAULT_FILE_PATH = "saved_list.txt";
    private static final String GREETING_MESSAGE = "Hi there! I' am Duke, your personal time manager."
            + "\n" + TAB + "What can I help you?";

    private final TaskList taskList;
    private final CliUi cliUi;
    private final Storage storage;
    private final Parser parser;

    /**
     * An empty constructor that will be used to initialise Duke by Launcher
     */
    public Duke() {
        this.taskList = new TaskList();
        this.cliUi = new CliUi();
        this.storage = new Storage(DEFAULT_FILE_PATH);
        this.parser = new Parser();
    }

    /**
     * The constructor to instantiate a new Duke chatbot.
     *
     * @param filePath A relative file path that specifies where to save the list.
     */
    public Duke(String filePath) {
        this.taskList = new TaskList();
        this.cliUi = new CliUi();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    /**
     * Starts the main logic of the chatbot if the user chooses to use CLI.
     * It keeps listening to user input until an exit command.
     */
    public void run() {
        greet();
        startListening();
    }

    private void greet() {
        cliUi.printOutput(GREETING_MESSAGE);
    }

    private void startListening() {

        boolean isExit = false;

        while (!isExit) {
            String input = cliUi.readInput();

            Command nextCommand = parser.parse(input);
            isExit = nextCommand.isExit();

            try {
                nextCommand.execute(cliUi, taskList, storage);
            } catch (DukeCommandAlreadyExecutedException exception) {
                cliUi.printOutput(exception.getMessage());
            }
        }
    }

    /**
     * Interacts with the user if they choose to use GUI.
     *
     * @param input The user input from the dialog box.
     * @return A wrapper class containing  to be displayed on the screen as a response to the user input.
     */
    public Response getResponse(String input) {
        Command nextCommand = parser.parse(input);
        String message;
        try {
            message = nextCommand.execute(cliUi, taskList, storage);
            return new Response(message, nextCommand.isExit());
        } catch (DukeCommandAlreadyExecutedException exception) {
            message = exception.getMessage();
            return new Response(message, nextCommand.isExit());
        }
    }

    /**
     * Runs a chatbot.
     *
     * @param args String arguments for starting the logic.
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke(DEFAULT_FILE_PATH);
        chatBot.run();
    }
}
