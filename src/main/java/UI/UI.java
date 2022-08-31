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
 * athe JavaFX User Interface
 */
public class UI extends Application {
    private Duck duck;
    private VBox container;
    private final int UI_WIDTH = 400;
    private final int UI_HEIGHT = 640;
    private Image duckImage = new Image(Objects.requireNonNull(this.getClass().
            getResourceAsStream("/duck.png")));


    public UI(){
        this.duck = new Duck();
    }

    /**
     * The override start for the JavaFX UI
     * @param stage the stage to be passed in, required as an overriden function
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("Duck!");
        stage.setResizable(false);
        stage.setMinHeight(UI_HEIGHT);
        stage.setMinWidth(UI_WIDTH);

        container = new VBox();
        container.setPrefSize(UI_WIDTH - 20, UI_HEIGHT - 25);
        container.setBackground(new Background(new BackgroundImage(
                duckImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(0.45, 0.3,
                true, true, false, false))));


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(container);
        scrollPane.setPrefSize(UI_WIDTH, UI_HEIGHT - 25);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        TextField userInputField = new TextField();
        userInputField.setPrefWidth(UI_WIDTH - 50);

        Button button = new Button("SEND");
        button.setPrefWidth(50);


        AnchorPane layout = new AnchorPane();
        layout.setPrefSize(UI_WIDTH, UI_HEIGHT);
        layout.getChildren().addAll(scrollPane, userInputField, button);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(button, 0.0);
        AnchorPane.setRightAnchor(button, 0.0);

        AnchorPane.setLeftAnchor(userInputField , 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);

        //for send button
        button.setOnMouseClicked((event) -> {
            container.getChildren().add(new Label(""));
            container.getChildren().add(displayUserInput(userInputField.getText()));
            readCommands(userInputField.getText());
            userInputField.clear();
        });

        //for "enter" the in textbox
        userInputField.setOnAction((event) -> {
            container.getChildren().add(new Label(""));
            container.getChildren().add(displayUserInput(userInputField.getText()));
            readCommands(userInputField.getText());
            userInputField.clear();
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
}
