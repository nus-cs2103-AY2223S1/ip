package duke;

import duke.ui.Ui;
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
 * Represents Duke - an interactive virtual assistant to organize tasks.
 */
public class Duke extends Application {

    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/SherlockH.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/KingOfOld.png"));


    /**
     * Constructs a Duke object and creates a new Ui object for user interaction
     */
    public Duke() {
        this.ui = new Ui(System.in, System.out);
    }

    /**
     * Introduces Duke and initiates interactive conversation with user
     */
    public String interact(String input) {
        return this.ui.readAndRespond(input);
    }


    @Override
    public void start(Stage stage){

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("ASK");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Ask Aemon");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    //adapted from https://se-education.org/guides/tutorials/javaFxPart3.html


    public static class DialogBox extends HBox {

        private Label text;
        private ImageView displayPicture;

        public DialogBox(Label l, ImageView iv) {
            text = l;
            displayPicture = iv;

            text.setWrapText(true);
            displayPicture.setFitWidth(125.0);
            displayPicture.setFitHeight(150.0);

            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(text, displayPicture);
        }

        private void flip() {
            this.setAlignment(Pos.CENTER);
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            FXCollections.reverse(tmp);
            this.getChildren().setAll(tmp);
        }

        public static DialogBox getDukeDialog(Label l, ImageView iv) {
            var db = new DialogBox(l, iv);
            db.flip();
            db.setStyle("-fx-border-color: black");
            return db;
        }


        public static DialogBox getUserDialog(Label l, ImageView iv) {
            return new DialogBox(l, iv);
        }

    }



    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    private String getResponse(String input) {
        return interact(input);
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }



    /**
     * Creates a new Duke object and begins interaction 
     */
    public static void main(String[] args) {
       Duke AemonT = new Duke();
//       AemonT.interact();
    }

}