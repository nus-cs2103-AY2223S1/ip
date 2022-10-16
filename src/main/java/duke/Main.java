package duke;

import command.Command;
import exception.EmptyDescriptionException;
import exception.InvalidCommandException;
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
import javafx.scene.image.Image;

import java.time.format.DateTimeParseException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The main class and entry point for executing the GUI and Duke.
 * @author Chen Guanzhou
 * @version v2
 */
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
    private void handleUserInput(){
        String input = userInput.getText();
        String dukeText = getResponse(input, p);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        if (dukeText.equals(Ui.endingMessage())) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            },1300*2,1300*2);
        }
        Storage taskSaver = new Storage(currList);
        taskSaver.writeToFile();
        userInput.clear();
    }

    /**
     * Gets the response from Duke by parsing the input and executing the command.
     * @param input User input command for Duke.
     * @param p The parser object that parses the user input.
     * @return Duke's response as a string
     */
    private String getResponse(String input, Parser p) {
        try {
            Command c = p.parseUserInput(input);
            return c.execute();
        } catch (EmptyDescriptionException e) {
            return "Description cannot be empty, try again!";
        } catch (InvalidCommandException | IndexOutOfBoundsException e) {
            return "Invalid input, try again!";
        } catch (DateTimeParseException e) {
            return "Cannot parse this date!";
        }

    }


}
