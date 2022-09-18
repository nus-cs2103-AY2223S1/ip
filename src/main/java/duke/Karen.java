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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDateTime;

public class Karen extends Application{

    private static final String FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button getButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image karen = new Image(this.getClass().getResourceAsStream("/images/karen.png"));

    public Karen() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }

    /**
     * Adds a task to storage.
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
        storage.save(tasks);
    }
    
    public void snoozeTask(Snoozable s) {
        s.snooze();
        storage.save(tasks);
    }

    /**
     * Deletes task of the index from storage.
     * @param index
     */
    public void deleteTask(int index) {
        tasks.delete(index);
        storage.save(tasks);
    }

    /**
     * Marks a task as done and saves it.
     * @param index
     */
    public void mark(int index) {
        tasks.mark(index);
        storage.save(tasks);
    }

    /**
     * Unmark a task as undone and saves it.
     * @param index
     */
    public void unmark(int index) {
        tasks.unmark(index);
        storage.save(tasks);
    }
    
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        
        userInput = new TextField();
        getButton = new Button("Send");
        
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, getButton);
        
        scene = new Scene(mainLayout);
        scene.getRoot().setStyle("-fx-font-family: 'sans-serif'");

        stage.setTitle("Karen");
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
        getButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(getButton, 1.0);
        AnchorPane.setRightAnchor(getButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        getButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        getButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        
        stage.setScene(scene);
        stage.show();

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.getStartMessage()), new ImageView(karen))
        );
        
    }

    /**
     * Iteration 1:
     * Creates a label with the specified input and adds it to the dialog container.
     * @param input String containing input to add
     * @return a label with the specified input that has word wrap enabled.
     */
    private Label getDialogLabel(String input) {
        Label inputToAdd = new Label(input);
        inputToAdd.setWrapText(true);

        return inputToAdd;
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
                DialogBox.getDukeDialog(dukeText, new ImageView(karen))
        );
        userInput.clear();
    }
    
    private String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.getByeMessage();
        }
        if (input.equals("list")) {
            return ui.getList(tasks);
        }
        if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                return ui.getWrongIndexMessage();
            }
            mark(index);
            return ui.getMarkedMessage(tasks.get(index));
        }
        if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                return ui.getWrongIndexMessage();
            }
            unmark(index);
            return ui.getUnmarkedMessage(tasks.get(index));
        }
        if (input.startsWith("todo ")) {
            String desc = input.substring(5);
            if (desc.trim().length() == 0) {
                return ui.getEmptyTaskMessage();
            }
            Todo t = new Todo(desc);
            addTask(t);
            return ui.getAddedMessage(t, tasks.size());
        }
        if (input.startsWith("deadline ")) {
            String[] params = input.substring(9).split(" /by ");
            if (params.length < 2) {
                return ui.getFailureMessage();
            }
            if (params[0].trim().length() == 0) {
                return ui.getEmptyTaskMessage();
            }

            String[] temp = params[1].split(" ");
            assert temp.length == 2;
            assert temp[0].split("/").length == 3;
            LocalDateTime dt = Parser.parseDateTime(params[1]);
            Deadline t = new Deadline(params[0], dt);
            addTask(t);
            return ui.getAddedMessage(t, tasks.size());
        }
        if (input.startsWith("event ")) {
            String[] params = input.substring(6).split(" /at ");
            if (params[0].trim().length() == 0) {
                ui.getEmptyTaskMessage();
            }
            if (params.length < 2) {
                ui.getFailureMessage();
            }

            String[] temp = params[1].split(" ");
            assert temp.length == 2;
            assert temp[0].split("/").length == 3;
            LocalDateTime dt = Parser.parseDateTime(params[1]);
            Event t = new Event(params[0], dt);
            addTask(t);
            return ui.getAddedMessage(t, tasks.size());
        }
        if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                return ui.getWrongIndexMessage();
            }
            Task toRemove = tasks.get(index);
            deleteTask(index);
            return ui.getTaskDeletedMessage(toRemove, tasks.size());
        }
        if (input.startsWith("snooze ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                return ui.getWrongIndexMessage();
            }
            Task t = tasks.get(index);
            if (t instanceof Todo) {
                return ui.getCannotSnoozeMessage();
            }
            snoozeTask((Snoozable) t);
            return ui.getTaskSnoozedMessage(t);
        }
        if (input.startsWith("find ")) {
            String keyword = input.substring(5);
            return ui.getList(new TaskList(tasks.filter(keyword)));
        }
        return ui.getFailureMessage();
        
    }


    
}
