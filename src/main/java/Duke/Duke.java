
package Duke;


import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;


import Duke.UserServer.ServerCLI;

import java.awt.print.Printable;


/**
 * Duke App Main class
 */

public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/A.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/B.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private static final ServerCLI serverCLI = new ServerCLI();

    public static void main(String[] args) { //throws DukeException, FileNotFoundException {

        serverCLI.run();


//        System.out.println("Testing Tasks: Deadline, Event");
//        TaskList tasks = new TaskList();
//        Event e = new Event("Go to the lecture", LocalDateTime.of(LocalDate.parse("2022-02-22"), LocalTime.parse("11:22")), false);
//        Deadline ddl = new Deadline("Go to the lecture", LocalDateTime.of(LocalDate.parse("2022-02-22"), LocalTime.parse("11:22")), true);
//        System.out.println("    Event " + e.toString() + "\n" + "    Deadline " + ddl.toString());
//
//        System.out.println("Testing Commands:  AddTaskCommand, MarkDoneCommand");
//        UserCommand A = new AddTaskCommand(ddl, tasks);
//        A.execute();
//        UserCommand B = new AddTaskCommand(e, tasks);
//        B.execute();
//        // Mark Task
//        UserCommand C = new MarkDoneCommand(1,tasks);
//        System.out.println("    " + C.execute());
//        System.out.println("    " + tasks.showTasks());
//
//        System.out.println("Testing CLIParser");
//        CLIParser parser = new CLIParser();
//        String input = "todo hw1";
//        System.out.println("    Input:" + input);
//        parser.parseCommand(input, tasks).execute();
//        System.out.println("    " + tasks.showTasks());
//
//        System.out.println("Testing CLIParser.run()");
//


    }

    @Override
    public void start(Stage stage) {
//
//        // Step 1. Setting up required components
//
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
//        // Step 2. Formatting the window to look as expected
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // Step 3. Add functionality to handle user input.
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
//
//        // Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
    }
//
//    /**
//     * Iteration 1
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
//
//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */


    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

//
//


}
