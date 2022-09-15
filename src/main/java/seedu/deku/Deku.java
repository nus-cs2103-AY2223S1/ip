package seedu.deku;

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

import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * Represents the Deku Application.
 */
public class Deku extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isClose = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // link to deku: https://www.facebook.com/izuku.gemsouls/photos/a.110536514587209/110537464587114/
    // link to kacchan: https://twinfinite.net/wp-content/uploads/2022/06/Matching-Pfp-My-hero-1.jpg
    private Image deku = new Image(this.getClass().getResourceAsStream("/images/deku.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/kacchan.png"));

    /**
     * Initializes the Deku Application.
     * @param filePath Path to extract the current list of tasks Deku has kept track of.
     */
    public Deku(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        File theDir = new File(filePath);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Prints the initial tasks Deku has based on data/deku.txt.
     * @param filePath Path to deku.txt.
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

        stage.setTitle("Deku");
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
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Runs Deku.
     */
    public void run() throws IOException, DekuException {
        try {
            System.out.println("Current Tasks:");
            printFileContents("data/deku.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, added file");
        }

        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        ArrayList<Boolean> isOpen = new ArrayList<>();
        isOpen.add(true);

        try {
            while (scanner.hasNextLine() && isOpen.get(0)) {
                String input = scanner.nextLine();
                String command = this.ui.getUserCommand(input);
                Parser.parse(command, input, tasks, this);
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
    String getResponse(String input) throws IOException, DekuException {
        String command = this.ui.getUserCommand(input);
        String response = Parser.parse(command, input, tasks, this);
        return response;
    }

    public boolean isClosed() {
        return this.isClose;
    }

    public static void main(String[] args) throws IOException, DekuException {
        new Deku("data/deku.txt").run();
    }
}


