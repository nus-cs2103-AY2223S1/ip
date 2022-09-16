package duke;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public void start(Stage stage) {

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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
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
        switch (Parser.parse(input)) {
        case TODO: {
            Todo todo = new Todo(input.replace("todo", "").trim(), false);
            tasks.add(todo);
            storage.writeToFile(tasks);
            return Ui.printAddedMessage(todo, tasks.getSize());
        }
        case DEADLINE: {
            String[] splitStr = input.trim().split("/by");
            String date = splitStr[1].replace("by", "").trim();
            Deadline deadline = new Deadline(splitStr[0].replace("deadline", "").trim(),
                    false, date);
            tasks.add(deadline);
            storage.writeToFile(tasks);
            return Ui.printAddedMessage(deadline, tasks.getSize());
        }
        case EVENT: {
            String[] splitStr = input.trim().split("/at");
            String date = splitStr[1].replace("at", "").trim();
            Event event = new Event(splitStr[0].replace("event", "").trim(), false, date);
            tasks.add(event);
            storage.writeToFile(tasks);
            return Ui.printAddedMessage(event, tasks.getSize());
        }
        case DELETE: {
            String[] splitStr = input.trim().split("\\s+");
            int deleteItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
            Task deletedTask = tasks.remove(deleteItem);
            storage.writeToFile(tasks);
            return Ui.printDeletedMessage(deletedTask, tasks.getSize());
        }
        case MARK: {
            int taskIndex = Integer.parseInt(input.replace("mark ", "").trim());
            tasks.markDone(taskIndex);
            storage.writeToFile(tasks);
            return Ui.printMarkMessage(tasks.getTaskDescription(taskIndex));
        }
        case UNMARK: {
            int taskIndex = Integer.parseInt(input.replace("unmark ", "").trim());
            tasks.unmark(taskIndex);
            storage.writeToFile(tasks);
            return Ui.printUnmarkMessage(tasks.getTaskDescription(taskIndex));
        }
        case QUIT:
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished( event -> Platform.exit() );
            delay.play();
            return Ui.printGoodbyeMessage();
        case LIST:
            return Ui.printTasks(tasks);
        case INVALID:
            return Ui.printErrorMessage(new DukeException(
                    "OOPS!!! I'm sorry, but I don't know what that means :-("));
        case FIND:
            TaskList filtered = tasks.find(input.replace("find", "").trim());
            return Ui.printTasks(filtered);
        case UPDATE:
            Pattern pattern = Pattern.compile("update (?<index>\\d+) (?<newTask>.*)");
            Matcher matcher = pattern.matcher(input);
            matcher.find();
            int index = Integer.parseInt(matcher.group("index"));
            String newTask = matcher.group("newTask");
            switch (Parser.parse(newTask)) {
            case TODO:
                Task todo = new Todo(newTask.replace("todo", "").trim(), false);
                tasks.update(index, todo);
                storage.writeToFile(tasks);
                return Ui.printAddedMessage(todo, tasks.getSize());
            case DEADLINE: {
                String[] splitStr = newTask.trim().split("/by");
                String date = splitStr[1].replace("by", "").trim();
                Deadline deadline = new Deadline(splitStr[0].replace("deadline", "").trim(),
                        false, date);
                tasks.update(index, deadline);
                storage.writeToFile(tasks);
                return Ui.printAddedMessage(deadline, tasks.getSize());
            }
            case EVENT: {
                String[] splitStr = newTask.trim().split("/at");
                String date = splitStr[1].replace("at", "").trim();
                Event event = new Event(splitStr[0].replace("event", "").trim(), false, date);
                tasks.update(index, event);
                storage.writeToFile(tasks);
                return Ui.printAddedMessage(event, tasks.getSize());
            }
            }
        }
        return "";
    }

}
