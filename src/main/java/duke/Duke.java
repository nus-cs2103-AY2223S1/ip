package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.chatbot.ChatBot;
import java.util.Scanner;
public class Duke extends Application {
    private static final double WINDOW_MIN_WIDTH = 400.0;
    private static final double WINDOW_MIN_HEIGHT = 600.0;
    private Scene scene;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot christina = new ChatBot("Christina");
        christina.initialize();
        while (christina.isRunning()) {
            christina.processCommand(scanner.nextLine());
        }
        scanner.close();
        christina.terminate();
    }
    @Override
    public void start(Stage stage) {
        // Setting the scene
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // Styling the scene


        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinWidth(WINDOW_MIN_WIDTH);
        stage.setMinHeight(WINDOW_MIN_HEIGHT);

        mainLayout.setPrefSize(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT);

        scrollPane.setPrefSize(WINDOW_MIN_WIDTH, 570);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        stage.setScene(scene);
        stage.show();
    }
}
