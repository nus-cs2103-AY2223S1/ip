package candice;

import candice.command.Command;
import candice.exception.*;
import candice.task.TaskList;

import java.io.IOException;

import java.lang.IllegalArgumentException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a program that helps to track tasks using a task list.
 * The program allows for different types of tasks to be added, tasks to be deleted or marked as finished/unfinished
 * as well as viewing the current list of tasks.
 *
 * Commands:
 * "todo {task name}" to add a task with no date associated to it.
 *
 * "deadline {task name} /by {deadline date} {optional: deadline time}" to add tasks that have a deadline associated to
 * it. Deadline dates are written in the form of DD/MM/YYYY (1/1/2022 or 31/12/2022). Deadline times are written in the
 * form of a 24-hour time (0000 to 2359).
 *
 * "event {task name} /at {event date} {optional: event start time - end time}" to add events with a date and
 * potentially a start plus end time associated to them. Deadline dates are written in the form of DD/MM/YYYY (1/1/2022
 * or 31/12/2022). Event start time - end time is written in the form of two 24-hour times (0000-0200 or 1800-2000).
 *
 * "list" to view the tasks added to the task list.
 *
 * "mark {task number}" to mark the task corresponding to the task number as finished.
 *
 * "unmark {task number}" to mark the task corresponding to the task number as unfinished.
 *
 * "delete {task number}" to remove the task corresponding to the task number from the task list.
 *
 * "find {key word}" to find any tasks with a task name containing the keyword.
 *
 * "bye" to exit the program.
 */
public class Candice extends Application {
    /** A task list to store all the tasks */
    TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for a Candice object encapsulating a TaskList object by using a Path object to instantiate a Storage
     * object which will be used by the TaskList to parse any tasks added from previous sessions.
     *
     * @param storagePath The path to the storage file, specifically the path to the task list text file.
     * @throws IOException If there was an error in creating the storage file.
     */
    public Candice(Path storagePath) throws IOException {
        Storage storage = new Storage(storagePath);
        this.taskList = new TaskList(storage);
        this.taskList.parseTaskListText();
    }

    /**
     * Default constructor for a Candice object, using the default storage.
     *
     * @throws IOException If there was an error in creating the storage file.
     */
    public Candice() throws IOException {
        Path storagePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
        Storage storage = new Storage(storagePath);
        this.taskList = new TaskList(storage);
        this.taskList.parseTaskListText();
    }

    /**
     * Runs the programme, allowing users to input a command to add, edit and delete tasks to and from a task list.
     */
    public void run() {
        Ui.printMessageForStartingUp();

        Scanner scanner = new Scanner(System.in); // Scans input
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                Command command = Parser.parse(input);
                command.resolve(taskList);
            } catch (EmptyCommandDescriptionException | EmptyTimingException | IllegalArgumentException | InvalidDateException
                    | InvalidFormattingException | InvalidTimeException | UnknownCommandException e) {
                System.out.println(e);
            }

            input = scanner.nextLine();
        }

        Ui.printMessageForShuttingDown();
    }

    public static void main(String[] args) {
        try {
            new Candice().run();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage stage) {
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
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(395, 565);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(335.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.show();
    }
}
