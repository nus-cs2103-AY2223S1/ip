package UI;

import Duck.Duck;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Class that encapsulates
 * the JavaFX User Interface
 */
public class UI extends Application {
    private Duck duck;
    private VBox container;
    private final int UI_WIDTH = 400;
    private final int UI_HEIGHT = 640;
    private final int BUTTON_LENGTH = 50;
    private final int INPUTFIELD_LENGTH = UI_WIDTH - BUTTON_LENGTH;
    private final double BACKGROUND_IMAGE_WIDTH = 0.45;
    private final double BACKGROUND_IMAGE_HEIGHT = 0.3;

    private Image duckImage = new Image(Objects.requireNonNull(this.getClass().
            getResourceAsStream("/duck.png")));


    public UI(){
        this.duck = new Duck();
    }

    /**
     * The override start for the JavaFX UI
     * @param stage the stage to be passed in, required as an overridden function
     */
    @Override
    public void start(Stage stage) {
        assert this.duck != null : "Duck object should be created";
        stageSetup(stage);
        containerSetup();
        Button button = buttonSetup();
        TextField userInputField = textFieldSetup();
        ScrollPane scrollPane = scrollPaneSetup();
        AnchorPane layout = layoutSetup(scrollPane, userInputField, button);
        //for submit button
        button.setOnMouseClicked((event) -> {
            submitEvent(userInputField);
        });

        //for "enter" the in textbox
        userInputField.setOnAction((event) -> {
            submitEvent(userInputField);
        });

        //scroll-down everytime the height changes
        container.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        sendTextToUi("Hello! Got any grapes?");
    }

    /**
     * Causes user input to be displayed in the UI as messages
     * @param text the input text
     * @return returns a Label object
     */
    private Label displayUserInput(String text) {
        return LabelTemplate.createMessageLabel(text, Color.BLACK);
    }
    /**
     * Reads the commands from the user input
     * @param text the user input text
     */
    private void readCommands(String text) {
        duck.passOnCommands(text, this);
    }

    /**
     * Public function to send text to the UI as messages,
     * Used by other classes/methods to engage the UI
     * @param text the user input text
     */
    public void sendTextToUi(String text) {
        container.getChildren().add(LabelTemplate.createMessageLabel(text, Color.GOLD));
    }

    /**
     * sets up the window for the GUI
     * @param stage stage variable to set up
     */
    private void stageSetup(Stage stage) {
        stage.setTitle("Duck!");
        stage.setResizable(false);
        stage.setMinHeight(UI_HEIGHT);
        stage.setMinWidth(UI_WIDTH);
    }

    /**
     * sets up the background for the GUI
     */
    private void containerSetup() {
        container = new VBox();
        container.setPrefSize(UI_WIDTH - 20, UI_HEIGHT - 25);
        container.setBackground(new Background(new BackgroundImage(
                duckImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(BACKGROUND_IMAGE_WIDTH,
                BACKGROUND_IMAGE_HEIGHT, true, true, false, false))));
    }

    /**
     * sets up the scrollpane for the GUI
     * @return scrollPane which has already been set up with dimensions dictated by constants
     */
    private ScrollPane scrollPaneSetup() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(container);
        scrollPane.setPrefSize(UI_WIDTH, UI_HEIGHT - 25);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        return scrollPane;
    }
    /**
     * sets up the user input field for the GUI
     * @return text field which has already been set up with dimensions dictated by constants
     */
    private TextField textFieldSetup() {
        TextField userInputField = new TextField();
        userInputField.setPrefWidth(INPUTFIELD_LENGTH);
        return userInputField;
    }
    /**
     * sets up the submit button for the GUI
     * @return a button which has already been set up with dimensions dictated by constants
     */
    private Button buttonSetup() {
        Button button = new Button("SEND");
        button.setPrefWidth(BUTTON_LENGTH);
        return button;
    }

    /**
     * sets up the layout of the elements of the GUI
     * where each element should sit relative to the UI
     * @param scrollPane scroll pane to cover most of the UI
     * @param userInputField input field at the bottom for user input
     * @param button button to the right of the input field
     * @return a complete layout of the UI
     */
    private AnchorPane layoutSetup(ScrollPane scrollPane, TextField userInputField, Button button){
        AnchorPane layout = new AnchorPane();
        layout.setPrefSize(UI_WIDTH, UI_HEIGHT);
        layout.getChildren().addAll(scrollPane, userInputField, button);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(button, 0.0);
        AnchorPane.setRightAnchor(button, 0.0);

        AnchorPane.setLeftAnchor(userInputField , 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);
        return layout;
    }

    /**
     * abstracts the "submit" button and enter key from the user
     * @param userInputField the text field to be read from
     */
    private void submitEvent(TextField userInputField) {
        container.getChildren().add(new Label(""));
        container.getChildren().add(displayUserInput(userInputField.getText()));
        readCommands(userInputField.getText());
        userInputField.clear();
    }
}
