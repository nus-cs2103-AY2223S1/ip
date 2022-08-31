package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke  extends Application {

    private Storage storage;
    private TaskList taskList;
    private UserInterface userInterface;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for Duke.
     *
     * @param path Relative path in local storage to save data.
     */
    public Duke(String path) {
        try {
            this.taskList = new TaskList();
            storage = new Storage(path, taskList);
            userInterface = new UserInterface(taskList);
            storage.load();
        } catch (IOException e) {
            UserInterface.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public Duke() {};

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    private void run() throws DukeException {
        ArrayList<Task> myList = new ArrayList<>();
        boolean bye = false;
        Scanner scanner = new Scanner(System.in);
        EventHandler handler = new EventHandler(this.taskList, this.userInterface);
        System.out.println("Hello I am Duke!");

        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;

                userInterface.sayBye();
            } else if (input.equals("list")) {
                userInterface.printList();
            } else if (input.startsWith("mark")) {
                handler.markTask(input);
            } else if (input.startsWith("unmark")) {
                handler.unmarkTask(input);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                handler.deleteTask(taskIndex);
            } else if (input.startsWith("todo")) {
                handler.addTodo(input);
            } else if (input.startsWith("deadline")) {
                handler.addDeadline(input);
            } else if (input.startsWith("event")) {
                handler.addEvent(input);
            } else if (input.startsWith("find")) {
                handler.find(input);
            } else {
                throw new DukeUnknownCommandException();
            }
            storage.save();

        }

        scanner.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        primaryStage.setScene(scene);
        primaryStage.show();

        // more code to be added here later

        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

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
    }
}
