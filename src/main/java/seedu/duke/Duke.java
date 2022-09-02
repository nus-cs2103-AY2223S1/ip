package seedu.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents the Duke Application.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isClose = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image duke = new Image(this.getClass().getResourceAsStream("/images/deku.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/kacchan.png"));

    /**
     * Initializes the Duke Application.
     * @param filePath Path to extract the current list of tasks Duke has kept track of.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Prints the initial tasks Duke has based on data/duke.txt.
     * @param filePath Path to duke.txt.
     * @throws FileNotFoundException
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

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

        // more code to be added here later

        stage.setTitle("Duke2");
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // listener for scrolling down
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Runs Duke.
     */
    public void run() {
        try {
            File file = new File("data/duke.txt");
            System.out.println("Current Tasks:");
            printFileContents("data/duke.txt");
            // no need to get tasks here, alr done in load at constructor
//            System.out.println(this.tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, added file");
        }

        // put as attributes in ui
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        ArrayList<Boolean> isOpen = new ArrayList<>();
        isOpen.add(true);

        try {
            while (scanner.hasNextLine() && isOpen.get(0)) {
                String input = scanner.nextLine();
                String command = this.ui.getUserCommand(input);
                Parser.parse(command, input, tasks, this);
//                System.out.println(isOpen.get(0));
            }
        } catch (IllegalStateException e) {
            // just catching error
        }
        scanner.close();
    }


    public void closeWindow() {
        this.isClose = true;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String command = this.ui.getUserCommand(input);
        String response = Parser.parse(command, input, tasks, this);
        return response;
    }

    public boolean isClosed() {
        return this.isClose;
    }

    // dealt with getting from file initially and saving, but need to call function for rewrite somewhere
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


