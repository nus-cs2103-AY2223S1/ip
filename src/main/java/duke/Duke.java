package duke;

import java.time.LocalDateTime;

import javafx.application.Application;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import javax.swing.*;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("./src/main/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * for purpose of testing StorageTest
     */
    TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * Executes the programme with data from storage if it exists
     * If the data does not exist, create a new file to store our data
     * Exit the programme only when the bye command is given
     */
    /*
    public void run() {
        ui.showWelcome();
        tasks.printTasks();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();

                //we get the commandType to know how to process the command
                String commandType = Parser.getCommandType(fullCommand);

                if (commandType.equals("EXIT")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    isExit = true;
                } else if (commandType.equals("PRINT")) {
                    tasks.printTasks();
                } else if (commandType.equals("UPDATE")) {
                    int[] arr = Parser.parseUpdateCommand(fullCommand);

                    //mark/unmark the task depending on the integer in the first entry of our array
                    if (arr[0] == 1) {
                        tasks.mark(arr[1]);
                    } else {
                        tasks.unmark(arr[1]);
                    }
                } else if (commandType.equals("DELETE")) {
                    int taskIndex = Parser.getDeleteNum(fullCommand);
                    tasks.delete(taskIndex);
                } else if (commandType.equals("ADD")){
                    if (fullCommand.startsWith("todo")) {
                        String task = fullCommand.substring(5);
                        tasks.add(new Todo(task));
                    } else {
                        LocalDateTime dateTime = Parser.parseDateTime(fullCommand);
                        if (fullCommand.startsWith("deadline")) {
                            tasks.add(new Deadline(fullCommand.substring(9, fullCommand.indexOf("/"))
                                    , dateTime));
                        } else {
                            tasks.add(new Event(fullCommand.substring(6, fullCommand.indexOf("/"))
                                    , dateTime));
                        }
                    }
                } else if (commandType.equals("FIND")) {
                    Parser.parseFindCommand(fullCommand, tasks);
                } else {
                    Parser.printUpcomingTasks(tasks);
                }
                storage.save(tasks);
            } catch (DukeException e){
                ui.showError(e);
            } finally {
                ui.printLine();
            }
        }
        System.exit(0);
    }
    */
    @Override
    public void start(Stage stage) {

        Label welcomeMessage = new Label("Welcome to Duke"); // Creating a new Label
        Scene scene = new Scene(welcomeMessage); // Setting the scene to be our Label

        JTextField welcomeText = new JTextField(16);
        welcomeText.setText("some text");

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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

        //Step 2. Formatting the window to look as expected
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        /*
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        */

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        boolean isExit = false;
        try {
            //ui.printLine();

            //we get the commandType to know how to process the command
            String commandType = Parser.getCommandType(input);

            if (commandType.equals("EXIT")) {
                response += "Bye. Hope to see you again soon!";
                isExit = true;
            } else if (commandType.equals("PRINT")) {
                response += tasks.printTasks();
            } else if (commandType.equals("UPDATE")) {
                int[] arr = Parser.parseUpdateCommand(input);

                //mark/unmark the task depending on the integer in the first entry of our array
                if (arr[0] == 1) {
                    response += tasks.mark(arr[1]);
                } else {
                    response += tasks.unmark(arr[1]);
                }
            } else if (commandType.equals("DELETE")) {
                int taskIndex = Parser.getDeleteNum(input);
                response += tasks.delete(taskIndex);
            } else if (commandType.equals("ADD")) {
                if (input.startsWith("todo")) {
                    String task = input.substring(5);
                    response += tasks.add(new Todo(task));
                } else {
                    LocalDateTime dateTime = Parser.parseDateTime(input);
                    if (input.startsWith("deadline")) {
                        response += tasks.add(new Deadline(input.substring(9, input.indexOf("/"))
                                , dateTime));
                    } else {
                        response += tasks.add(new Event(input.substring(6, input.indexOf("/"))
                                , dateTime));
                    }
                }
            } else if (commandType.equals("FIND")) {
                response += Parser.parseFindCommand(input, tasks);
            } else {
                response += Parser.printUpcomingTasks(tasks);
            }
            storage.save(tasks);
            if (isExit) {
                System.out.println(response);
                System.exit(0);
            }
            return response;
        } catch (DukeException e) {
            return ui.showError(e);
        } finally {
            //return ui.printLine();
        }
    }
}

    /*
    public static void main(String[] args)  {
        new Duke("./src/main/data/duke.txt").run();
    }
     */