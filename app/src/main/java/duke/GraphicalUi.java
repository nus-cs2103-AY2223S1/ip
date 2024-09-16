package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A graphical-based user interface handler.
 */
public class GraphicalUi extends Ui {
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a new GUI handler.
     */
    public GraphicalUi(InputAcceptor ia, Stage stage) {
        super(ia);
        this.stage = stage;

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        setupGui();
        showGui();

        mainLayout.setPrefSize(400.0, 600.0);
    }

    private void setupStage() {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setupDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void setupScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setupAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setupGui() {
        setupStage();
        setupDialogContainer();
        setupScrollPane();
        setupAnchorPane();

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);
    }

    private void showGui() {
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void runInputLoop() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        dialogContainer.getChildren().add(
                MessageBox.getUserDialog(userText));
        processInput(userInput.getText());
        userInput.clear();
    }

    @Override
    public void stopInputLoop() {
        sendButton.setDisable(true);
        userInput.setDisable(true);
    }

    @Override
    public void respond(String response) {
        dialogContainer.getChildren().add(
                MessageBox.getDukeDialog(response));
    }
}
