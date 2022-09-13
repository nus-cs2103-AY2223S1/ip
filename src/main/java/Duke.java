import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.UI;
import duke.command.Command;
import duke.Parser;

import duke.command.ResponseCommand;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


/**
 * Main class of the Duke program
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Duke {
    //extends application

    private Storage storage;
    private TaskList taskList;


    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = this.storage.load();
        } catch (DukeException de) {
            System.out.println("Duke exception!!");
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void run() {
        UI.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String rawCommand = sc.nextLine();
                Command c = Parser.parse(rawCommand);
                c.execute(taskList, storage);
                isExit = c.isByeCommand();
            } catch (AssertionError ae) {
                UI.response(ae.getMessage());
                Command c = new ResponseCommand(ae.getMessage());
//                c.execute(taskList, storage);
            } catch (DukeException de) {
                System.out.println(de);
            }
        }
    }


    public static void main(String[] args) {
        Duke d = new Duke("duke.txt");
        d.run();
    }
}


//    @Override
//    public void start(Stage stage) {
//        //Step 1. Formatting the window to look as expected.
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
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // more code to be added here later
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//    }
//
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

