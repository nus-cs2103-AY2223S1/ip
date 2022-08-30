package cs2103t.ip.duke;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Duke extends Application {

    public static final String FOLDER_PATH = "../data";
    public static final String FILE_PATH = "../data/duke.txt";

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        Text text = new Text();
        text.setText("Hello !! Welcome to JavaTPoint");
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(ui.showWelcome()), new ImageView(duke)));
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        if (userInput.getText().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            //Platform.exit();
        }
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) throws IOException {
        int index = tasks.size();
            if (parser.isBye(input)) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTasks().get(i);
                    String toSave = task.saveString();
                    if (i == 0) {
                        storage.writeToFile(FILE_PATH, toSave);
                    } else {
                        storage.appendToFile(FILE_PATH, toSave);
                    }
                }
                return ui.showBye();
            } else if (parser.isMark(input)){
                if (input.length() > 5) {
                    int taskNum = Integer.parseInt(input.substring(5));
                    tasks.markDone(taskNum);
                    return ui.showMark(tasks, taskNum);
                } else {
                    return "☹ OOPS!!! Please include the index of the task you'd like to mark as completed!";
                }
            } else if (parser.isUnmark(input)){
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    tasks.markUndone(taskNum);
                    return ui.showUnmark(tasks, taskNum);
                } else {
                    return "☹ OOPS!!! Please include the index of the task you'd like to mark as incomplete!";
                }
            } else if (parser.isList(input)) {
                return ui.showList(tasks, tasks.size());
            } else if (parser.isTodo(input)){
                if (input.length() > 5) {
                    Todo todo = new Todo(input.substring(5));
                    tasks.addTasks(todo);
                    index++;
                    return ui.showTodo(todo, index);
                } else {
                    return "☹ OOPS!!! The description of a todo cannot be empty.";
                }
            } else if (parser.isDeadline(input)){
                if (input.length() > 9) {
                    String[] dead = input.split(" /by ");
                    Deadlines deadlines = new Deadlines(dead[0].substring(9), LocalDate.parse(dead[1]));
                    tasks.addTasks(deadlines);
                    index++;
                    return ui.showDeadline(deadlines, index);
                } else {
                    return "☹ OOPS!!! The description of a deadline cannot be empty.";
                }
            } else if (parser.isEvent(input)){
                if (input.length() > 6) {
                    String[] time = input.split(" /at ");
                    Event event = new Event(time[0].substring(6), LocalDate.parse(time[1]));
                    tasks.addTasks(event);
                    index++;
                    return ui.showEvent(event, index);
                } else {
                    return "☹ OOPS!!! The description of a event cannot be empty.";
                }
            } else if (parser.isDelete(input)) {
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    Task toDelete = tasks.getTasks().get(taskNum - 1);
                    tasks.deleteTasks(taskNum - 1);
                    index--;
                    return ui.showDelete(toDelete, index);
                } else {
                    return "☹ OOPS!!! Please include the index of the task you'd like to delete!";
                }
            } else if (parser.isFind(input)) {
                if (input.length() > 5) {
                    ArrayList<Task> arr = new ArrayList<>();
                    Tasklist filteredTasks = new Tasklist(arr);
                    for (Task t : tasks.getTasks()) {
                        if (t.toString().contains(input.substring(5))) {
                            filteredTasks.addTasks(t);
                        }
                    }
                    return ui.showFilteredList(filteredTasks, filteredTasks.size());
                }
            } else {
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        return "☹ OOPS!!! I'm sorry, but I don't know what that means!!!";
    }

    public Duke() {
        storage = new Storage(FOLDER_PATH, FILE_PATH);
        ui = new Ui();
        parser = new Parser();
        try {
            tasks = new Tasklist(storage.loadFileData());
        } catch (DukeException e) {
            ui.showLoadingError();
            ArrayList<Task> array = new ArrayList<>();
            tasks = new Tasklist(array);
        }
    }

    public void dukeRun() throws DukeException, IOException {
        ui.showWelcome();
        String input = "";
        int index = tasks.size();
        while (!parser.isBye(input)) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (parser.isBye(input)) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTasks().get(i);
                    String toSave = task.saveString();
                    if (i == 0) {
                        storage.writeToFile(FILE_PATH, toSave);
                    } else {
                        storage.appendToFile(FILE_PATH, toSave);
                    }
                }
                ui.showBye();
            } else if (parser.isMark(input)){
                if (input.length() > 5) {
                    int taskNum = Integer.parseInt(input.substring(5));
                    tasks.markDone(taskNum);
                    ui.showMark(tasks, taskNum);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as completed!");
                }
            } else if (parser.isUnmark(input)){
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    tasks.markUndone(taskNum);
                    ui.showUnmark(tasks, taskNum);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as incomplete!");
                }
            } else if (parser.isList(input)) {
                ui.showList(tasks, tasks.size());
            } else if (parser.isTodo(input)){
                if (input.length() > 5) {
                    Todo todo = new Todo(input.substring(5));
                    tasks.addTasks(todo);
                    index++;
                    ui.showTodo(todo, index);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (parser.isDeadline(input)){
                if (input.length() > 9) {
                    String[] dead = input.split(" /by ");
                    Deadlines deadlines = new Deadlines(dead[0].substring(9), LocalDate.parse(dead[1]));
                    tasks.addTasks(deadlines);
                    index++;
                    ui.showDeadline(deadlines, index);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (parser.isEvent(input)){
                if (input.length() > 6) {
                    String[] time = input.split(" /at ");
                    Event event = new Event(time[0].substring(6), LocalDate.parse(time[1]));
                    tasks.addTasks(event);
                    index++;
                    ui.showEvent(event, index);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else if (parser.isDelete(input)) {
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    Task toDelete = tasks.getTasks().get(taskNum - 1);
                    tasks.deleteTasks(taskNum - 1);
                    index--;
                    ui.showDelete(toDelete, index);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to delete!");
                }
            } else if (parser.isFind(input)) {
                if (input.length() > 5) {
                    ArrayList<Task> arr = new ArrayList<>();
                    Tasklist filteredTasks = new Tasklist(arr);
                    for (Task t : tasks.getTasks()) {
                        if (t.toString().contains(input.substring(5))) {
                            filteredTasks.addTasks(t);
                        }
                    }
                    ui.showFilteredList(filteredTasks, filteredTasks.size());
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.dukeRun();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}