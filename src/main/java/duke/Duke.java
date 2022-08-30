package duke;

import duke.command.Command;
import duke.exception.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main file of Duke to be run to initiate the program.
 */
public class Duke extends Application {
    //Backend variables
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    //Frontend variables
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Duke instance by default with no properties initialised
     */
    public Duke() {

    }

    /**
     * Constructs a Duke instance and load the previous stored TaskList from the filePath.
     *
     * @param filePath the path of the .txt file in this project where the TaskList is to be stored.
     * @throws DukeException the parent Exception inherited by self-created exceptions.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            if (taskList.isEmpty()) {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Initialise the Duke program.
     */
    public void run() {
        this.ui.showGreetingMessage();
        this.ui.printList(this.taskList);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
        this.ui.showGoodbyeMessage();
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        
        scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
