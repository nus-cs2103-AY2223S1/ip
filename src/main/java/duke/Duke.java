package duke;

import java.util.Scanner;
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
import javafx.scene.image.ImageView;

/**
 * Main class of the program
 * Stores a taskList of tasks
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //images to represent user/duke
    private Image user = new Image(this.getClass().getResourceAsStream("/images/finalUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ajitpai.png"));

    private static TaskList tasks;





/*    @Override
    public void start(Stage stage) {
        //Initialization
        tasks = Storage.load();
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        styleControls(stage, mainLayout);

        //Added functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


    }*/

/*    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }*/

/*    private void styleControls(Stage stage, AnchorPane mainLayout) {
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Display Welcome Message
        Label welcomeLabel = new Label(Ui.welcomeMessage());
        dialogContainer.getChildren().add(
                new DialogueBox(welcomeLabel,new ImageView(user))
        );
    }*/

    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogueBox.getUserDialog(userInput.getText(), user),
                DialogueBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }


    /**
     * returns the appropriate response as a string
     * @param input
     * @return
     */
    String getResponse(String input) {
        return Parser.parseData(input, tasks);
    }
    public Duke() {
        tasks = Storage.load();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * DEPRECATED
     * Displays the welcome message.
     * Initializes the scanner to scan for inputs
     * Lets the parser parse the correct input
     * Terminate the program if the user requests for it
     */
    public static void run() {
        Ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            //how does the user end this//
            if (input.equals("bye")) {
                Ui.displayMessage(Ui.ENDING_MESSAGE);
                break;
            }
            //if not, parser can parse data
            Parser.parseData(input, tasks);
        }
    }


}

