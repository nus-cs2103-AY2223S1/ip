package duke.ui;

import duke.Duke;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the Graphical User Interface through which the user interacts with Duke Aemon.
 */
public class Gui extends Application {

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/SherlockH.png"));
    private final Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/KingOfOld.png"));

    private final Duke aemon = new Duke();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox(10);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("ASK");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Ask Aemon");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);
        stage.show();

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty()
                .addListener((obs) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325);
        sendButton.setPrefWidth(55);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Opening message from Aemon
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        new Label("You have returned.\nSpeak, and I shall attend."),
                        new ImageView(dukeImage))
        );

        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
    }

    /**
     * Handles user input and displays both user and Duke messages.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        Label userText = new Label(input);
        Label dukeText = new Label(aemon.interact(input));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );

        userInput.clear();
    }

    /**
     * Represents a dialog box consisting of text and an image.
     */
    public static class DialogBox extends HBox {

        private final Label text;
        private final ImageView displayPicture;

        private DialogBox(Label text, ImageView image) {
            this.text = text;
            this.displayPicture = image;

            text.setWrapText(true);
            displayPicture.setFitWidth(80);
            displayPicture.setFitHeight(80);
            displayPicture.setPreserveRatio(true);

            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(text, displayPicture);
        }

        public static DialogBox getUserDialog(Label text, ImageView image) {
            DialogBox db = new DialogBox(text, image);
            db.setAlignment(Pos.TOP_RIGHT);
            return db;
        }

        public static DialogBox getDukeDialog(Label text, ImageView image) {
            DialogBox db = new DialogBox(text, image);
            db.flip();
            return db;
        }

        private void flip() {
            ObservableList<Node> nodes =
                    FXCollections.observableArrayList(this.getChildren());
            FXCollections.reverse(nodes);
            this.getChildren().setAll(nodes);
        }
    }
}
