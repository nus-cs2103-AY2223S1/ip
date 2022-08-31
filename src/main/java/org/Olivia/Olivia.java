package org.Olivia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import org.Olivia.IO.FileHandler;
import org.Olivia.IO.UiHandler;
import org.Olivia.calendar.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;


public class Olivia extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user;// = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.jpg")));
    private Image olivia;// = new Image(this.getClass().getResourceAsStream("/images/Olivia.jpg"));
    private GuiEventDispatcher dispatcher;


    public static void main(String[] args) {
        //each worker is attached to a calendar table to work on and an UiHandler to handle IO
        //this design would allow the program to handle multi-user with multi-input-source case (with multi-thread)
        //the UiHandler can be binded to a specific InputStream and a specific PrintStream (default to be stdin & stdout)
        EventDispatcher worker_1 = new EventDispatcher(new Calendar(), new UiHandler(), new FileHandler());
        worker_1.startWorking();
    }

    //@Override
    public void start(Stage stage) {
        // somehow the getResourceAsStream function does not work on my system
        // (it always returns null)
        try {
            File userIconFile = new File("images/User.jpg");
            InputStream userIconStream = new FileInputStream(userIconFile);
            File oliviaIconFile = new File("images/Olivia.jpg");
            InputStream oliviaIconStream = new FileInputStream(oliviaIconFile);
            user = new Image(userIconStream);
            olivia = new Image(oliviaIconStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.dispatcher=new GuiEventDispatcher(new Calendar(), new FileHandler());
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getOliviaDialog(dukeText, new ImageView(olivia))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            return dispatcher.dispatchCommand(input);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }


}
