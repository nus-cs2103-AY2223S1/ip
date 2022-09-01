package duke;

import duke.command.Command;
import duke.exception.DukeCommandAlreadyExecutedException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The only public-facing class that interacts directly with the user.
 * Duke is a personal time management virtual assistant, targeted at those who prefer using CLI instead of GUI.
 */
public class Duke extends Application {

    public static final String TAB = "    ";

    private static final String FILE_PATH = "saved_list.txt";
    private static final String GREETING_MESSAGE = "Hi there! I' am Duke, your personal time manager."
            + "\n" + TAB + "What can I help you?";

    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    /**
     * The constructor to instantiate a new Duke chatbot.
     * @param filePath A relative file path that specifies where to save the list.
     */
    public Duke(String filePath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    /**
     * Starts the main logic of the chatbot.
     * It keeps listening to user input until an exit command.
     *
     */
    public void run() {
        greet();
        startListening();
    }

    private void greet() {
        ui.printOutput(GREETING_MESSAGE);
    }

    private void startListening() {

        boolean isExit = false;

        while (!isExit) {
            String input = ui.readInput();

            Command nextCommand = parser.parse(input);
            isExit = nextCommand.isExit();

            try {
                nextCommand.execute(ui, taskList, storage);
            } catch (DukeCommandAlreadyExecutedException exception) {
                ui.printOutput(exception.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * The main logic that runs a chatbot.
     * @param args String arguments for starting the logic.
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke(FILE_PATH);
        chatBot.run();
    }
}
