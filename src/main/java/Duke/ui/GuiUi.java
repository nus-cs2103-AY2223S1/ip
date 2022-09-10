package Duke.ui;

import Duke.Duke;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class GuiUi {

    private final Duke duke;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private final Stage stage;
    private final Image userImage
            = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/cat.jpeg")));
    private final Image dukeImage
            = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/neko.jpeg")));

    public GuiUi(Stage stage, Duke duke) {
        this.stage = stage;
        this.duke = duke;
        AnchorPane mainLayout = createLayout();
        setStageScene(mainLayout);
        formatLayout(mainLayout);
        addUserInputHandler();
    }

    private void setStageScene(AnchorPane anchorPane) {
        stage.setScene(new Scene(anchorPane));
    }

    private AnchorPane createLayout() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");

        scrollPane.setContent(dialogContainer);
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        return mainLayout;
    }

    private void formatLayout(AnchorPane mainLayout) {
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void addUserInputHandler() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user's input and the other containing Duke's reply.
     */
    public void handleUserInput() {
        String input = userInput.getText();
        String output = null;

        if (isValidInput(input)) {
            displayInput(input);
            output = duke.receiveInput(input);
        }

        if (isValidOutput(output)) {
            displayOutput(output);
        }

        userInput.clear();
    }

    private boolean isValidInput(String input) {
        return !input.trim().isBlank();
    }

    private boolean isValidOutput(String output) {
        return output != null;
    }

    public void displayInput(String input) {
        Label inputText = new Label(input);
        dialogContainer.getChildren().add(
                new DialogBoxInput(inputText, new ImageView(userImage))
        );
    }

    public void displayOutput(String output) {
        Label outputText = new Label(output);
        dialogContainer.getChildren().add(
                new DialogBoxResponse(outputText, new ImageView(dukeImage))
        );
    }

    public void show() {
        stage.show();
    }
}
