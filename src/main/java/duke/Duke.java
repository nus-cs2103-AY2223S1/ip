package duke;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Duke is an interactive personal chat robot to keep track of user inputted tasks.
 *
 * @author Liu Han
 */

public class Duke {
    private final static String PATH = "src/main/java/data/duke.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;

    /**
     * Duke Constructor
     * @param filePath the file path where user would like to store the .txt file consisting of list of tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }

    private void run() {
        ui.showWelcome();
        //storage.saveData(this.tasks);
        //ui.farewell();
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.saveData(this.tasks);
        }
        return new Parser(this.tasks).parser(input);
    }

//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//    }
}
