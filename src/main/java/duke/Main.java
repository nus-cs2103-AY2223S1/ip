package duke;

import javafx.application.Application;
import javafx.fxml.FXML;
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


public class Main extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private static VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private static Image user = new Image(Main.class.getResourceAsStream("/images/DaUser.png"));
    private static Image duke = new Image(Main.class.getResourceAsStream("/images/DaDuke.png"));
    private static TaskList currList;
    private static Parser p;

    /**
     * Sets up the stage components for the GUI.
     * @param stage The stage where the dimensions of the different components are specified.
     */
    public void settingUpGui(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.greetingMessage(), duke));
    }

    /**
     * The method to initialize all variables and behaviour when the app launches.
     * @param stage The stage for the initialization.
     * @return The initial greeting message before any user commands.
     */
    public String initialize(Stage stage) {
        this.settingUpGui(stage);
        currList = new TaskList();
        Storage fileHandler = new Storage(currList);
        fileHandler.readAndProcessFile();
        p = new Parser(currList);
        return Ui.greetingMessage();
    }

    /**
     * Starting the GUI and the chatbot application.
     * @param stage The stage for the JavaFX GUI.
     */
    @Override
    public void start(Stage stage) {
        this.initialize(stage);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
    }

    /**
     * Handles input from user and passes it to the parser to process the commands.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String dukeText = getResponse(input, p);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        Storage taskSaver = new Storage(currList);
        taskSaver.writeToFile();
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input, Parser p) {
        return p.parseUserInput(input);
    }


}
