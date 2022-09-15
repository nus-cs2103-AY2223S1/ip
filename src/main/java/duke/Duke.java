package duke;

import java.util.Scanner;

import exceptions.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

/**
 * A basic CLI application for managing tasks.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaEnlistee.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Ditto.png"));

    /**
     * Enum for task type.
     */
    public enum TaskType {
        EVENT, DEADLINE, TODO
    }

    /**
     * The TaskList instance which manages the user's tasks.
     */
    private TaskList taskList;

    /**
     * The Storage instance which allows for the caching of
     * a user's tasks between sessions.
     */
    private Storage storage;

    /**
     * Constructor for a Duke instance.
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.displayErrorMessage(e);
            this.taskList = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }


    /**
     * Starts the application.
     * @param args Optional command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Parses the user's input and decides the appropriate method to
     * call based on the internal decision tree implemented in Parser.
     *
     * Terminates if the user enters 'bye'.
     */
    public void run() {
        Ui.welcomeUser();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            if (s.equals("bye")) {
                Ui.sayGoodbye();
            } else if (s.equals("list")) {
                this.taskList.listTasks();
            } else {
                try {
                    Parser.decide(s, arr, this.taskList, this.storage);
                } catch (DukeException e) {
                    Ui.displayErrorMessage(e);
                }
            }
        }
    }
}
