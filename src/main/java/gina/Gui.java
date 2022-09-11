package gina;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Gui extends Application {
    private Gina gina = new Gina("./data2/tasks.txt");
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image ginaImg = new Image(this.getClass().getResourceAsStream("/images/gina-knows-best.png"));

    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = new AnchorPane();
        //Step 1. Setting up required components
        setUpComponents(stage, mainLayout);
        //Step 2. Formatting the window to look as expected
        formatWindow(stage, mainLayout);
        //Step 3. Add functionality to handle user input.
        startGina();
    }

    public void setUpComponents(Stage stage, AnchorPane mainLayout) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    public void formatWindow(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Gina");
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
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    public void startGina() {
        showGreeting();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label ginaText = new Label(getResponse(userInput.getText()));
        userText.setPadding(new Insets(10));
        ginaText.setPadding(new Insets(10));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImg)),
                DialogBox.getGinaDialog(ginaText, new ImageView(ginaImg))
        );
        userInput.clear();
    }

    private void showGreeting() {
        Label greeting = new Label(gina.getGreeting());
        greeting.setPadding(new Insets(10));
        dialogContainer.getChildren().addAll(
                DialogBox.getGinaDialog(greeting, new ImageView(ginaImg))
        );
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return gina.getResponse(input);
    }
}
