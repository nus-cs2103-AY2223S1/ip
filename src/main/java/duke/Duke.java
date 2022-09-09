package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        tasks = storage.load();
    }

    public Duke() {}

    public void run() {
        Ui.printInitialMessage();
        while (true) {
            String command = ui.readCommand();
            switch (Parser.parse(command)) {
            case TODO: {
                Todo todo = new Todo(command.replace("todo", "").trim(), false);
                tasks.add(todo);
                Ui.printAddedMessage(todo, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case DEADLINE: {
                String[] splitStr = command.trim().split("/by");
                String date = splitStr[1].replace("by", "").trim();
                Deadline deadline = new Deadline(splitStr[0].replace("deadline", "").trim(),
                        false, date);
                tasks.add(deadline);
                Ui.printAddedMessage(deadline, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case EVENT: {
                String[] splitStr = command.trim().split("/at");
                String date = splitStr[1].replace("at", "").trim();
                Event event = new Event(splitStr[0].replace("event", "").trim(), false, date);
                tasks.add(event);
                Ui.printAddedMessage(event, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case DELETE: {
                String[] splitStr = command.trim().split("\\s+");
                int deleteItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
                Task deletedTask = tasks.remove(deleteItem);
                Ui.printDeletedMessage(deletedTask, tasks.getSize());
                storage.writeToFile(tasks);
                continue;
            }
            case MARK:
                tasks.markDone(Integer.parseInt(command.replace("mark ", "").trim()));
                storage.writeToFile(tasks);
                continue;
            case UNMARK:
                tasks.unmark(Integer.parseInt(command.replace("unmark ", "").trim()));
                storage.writeToFile(tasks);
                continue;
            case QUIT:
                Ui.printGoodbyeMessage();
                return;
            case LIST:
                Ui.printTasks(tasks);
                continue;
            case INVALID:
                Ui.printErrorMessage(new DukeException(
                        "OOPS!!! I'm sorry, but I don't know what that means :-("));
                continue;
            case FIND:
                TaskList filtered = tasks.find(command.replace("find", "").trim());
                Ui.printTasks(filtered);
                continue;
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
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
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
