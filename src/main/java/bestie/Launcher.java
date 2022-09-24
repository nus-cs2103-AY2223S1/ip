package bestie;

import bestie.gui.DialogBox;
import bestie.utils.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;
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

/**
 * Launches the Bestie GUI.
 */
public class Launcher extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private static final String FILEPATH = "./data/data.txt";
    private static final String TITLE = "Bestie - Helping you SLAYYY\u2728\u2728";
    private static final int STAGE_MIN_HEIGHT = 700;
    private static final int STAGE_MAX_HEIGHT = 500;
    private static final int MAIN_LAYOUT_PREF_WIDTH = 500;
    private static final int MAIN_LAYOUT_PREF_HEIGHT = 700;
    private static final int SCROLLPANE_PREF_WIDTH = 500;
    private static final int SCROLLPANE_PREF_HEIGHT = 675;
    private static final int USER_INPUT_WIDTH = 440;
    private static final int SEND_BUTTON_WIDTH = 65;

    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bestieImage = new Image(this.getClass().getResourceAsStream("/images/bestie.png"));

    private Bestie bestie = new Bestie(FILEPATH);

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    @Override
    public void start(Stage stage) {
        initialise();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle(TITLE);
        stage.setResizable(true);
        stage.setMinHeight(STAGE_MIN_HEIGHT);
        stage.setMinWidth(STAGE_MAX_HEIGHT);

        mainLayout.setPrefSize(MAIN_LAYOUT_PREF_WIDTH, MAIN_LAYOUT_PREF_HEIGHT);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.setScene(scene);
        stage.show();
    }

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    /**
     * Initialises the components of the GUI.
     */
    private void initialise() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        scrollPane.setPrefSize(SCROLLPANE_PREF_WIDTH, SCROLLPANE_PREF_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(getDialogLabel(
                Ui.sayWelcome()), new ImageView(bestieImage)));

        userInput.setPrefWidth(USER_INPUT_WIDTH);
        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);
    }

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label bobText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(bobText, new ImageView(bestieImage))
        );
        userInput.clear();
    }

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    /**
     * Gets Bob's response to the input.
     */
    private String getResponse(String input) {
        return bestie.getResponse(input);
    }

    public void launch() {
        Application.launch();
    }
}
