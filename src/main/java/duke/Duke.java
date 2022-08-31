package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

public class Duke extends Application {
    public static String taskDataPath = "data";
    public static String taskDataFileName = "duke.txt";

    public static void execute() {
        try {
            Scanner sc = new Scanner(System.in);
            UI UI = new UI();
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            Storage storage = new Storage(taskDataPath, taskDataFileName);

            boolean stopLish = false;
            UI.printResponse(UI.greeting);

            storage.readTaskData(taskList);

            while (!stopLish) {
                String input = sc.nextLine();

                switch (input) {
                    case "":
                        break;
                    case "bye":
                        UI.printResponse(UI.goodBye);
                        stopLish = true;
                        break;
                    case "list":
                        taskList.printTaskList();
                        break;
                    default:
                        try {
                            String[] commands = input.split(" ");

                            if (commands.length < 2) {
                                throw new DukeException("I do not understand that command. Please elaborate more!\n");
                            }

                            if (commands[0].equals("mark") || commands[0].equals("unmark") || commands[0].equals("delete")) {
                                int index = Integer.parseInt(commands[1]) - 1;

                                if (commands[0].equals("mark")) {
                                    taskList.markTaskAsDone(index);
                                } else if (commands[0].equals("unmark")) {
                                    taskList.markTaskAsNotDone(index);
                                } else if (commands[0].equals("delete")) {
                                    taskList.deleteTask(index);
                                } else {
                                    throw new DukeException("I do not understand that command :(\n");
                                }
                            } else if (commands[0].equals("find")) {
                                String description = commands[1];
                                taskList.find(description);
                            } else {
                                Task newTask;
                                if (commands[0].equals("todo")) {
                                    newTask = parser.generateToDoFromInput(input);
                                } else if (commands[0].equals("deadline")) {
                                    newTask = parser.generateDeadlineFromInput(input);
                                } else if (commands[0].equals("event")) {
                                    newTask = parser.generateEventFromInput(input);
                                } else {
                                    throw new DukeException("I do not understand that command :(\n");
                                }
                                taskList.add(newTask);
                            }
                        } catch (DukeException e) {
                            UI.printResponse(e.toString());
                        }
                }
            }
            storage.updateTaskData(taskList);
        } catch (DukeException e) {
            UI.printResponse(e.toString());
        }
    }

    public static void main(String[] args) throws DukeException {
        execute();
    }

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
