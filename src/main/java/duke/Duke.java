package duke;

import duke.Storage;
import duke.Parser;
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
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;

    /**
     * Duke Constructor
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui.showWelcome();
        assert false;
        storage.saveData(this.tasks);
        boolean isBye = false;
        while (!isBye) {
            String input = ui.readCommand();
            ui.showLine();
            System.out.println(new Parser(this.tasks).parser(input));
            storage.saveData(this.tasks);
            isBye = input.equals("bye");
            ui.showLine();
        }
        ui.farewell();
    }


    public String getResponse(String input) {
//        if (input.equals("bye")) {
//            storage.saveData(this.tasks);
//        }
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
