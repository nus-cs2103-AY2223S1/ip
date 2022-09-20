package duke;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The main application.
 */
public class Duke {

    public final String FILE_NAME;
    private final Storage FO;
    private final TaskList TASK_LIST;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Returns a Duke instance.
     *
     * @param fileName The name of the file to store the tasks.
     * @param fo       The FileOperator to perform file manipulation.
     * @param taskList The TaskList instance to manage all tasks.
     */
    public Duke(String fileName, Storage fo, TaskList taskList) {
        FILE_NAME = fileName;
        this.FO = fo;
        this.TASK_LIST = taskList;
        startUp();
    }

    /**
     * Loads tasks from data.txt when Duke first starts up.
     */
    public void startUp() {
        Ui ui = new Ui(FILE_NAME);
        ui.printWelcomeMessage();

        // Read from file and load into tasks
        File file;

        try {
            file = new File(FILE_NAME);
            if (file.createNewFile()) {
                ui.printCreateNewFile();
            } else {
                FO.loadAllTasksFromFile(TASK_LIST);
                ui.printLoadTasksFromFile();
                ui.printLineBreak();
            }
        } catch (IOException e) {
            ui.printErrorCreatingFile();
        }
    }

    /**
     * A default constructor for Duke with the default values of its attributes.
     */
    public Duke() {
        FILE_NAME = "data.txt";
        FO = new Storage("data.txt");
        TASK_LIST = new TaskList();
        startUp();
    }

    /**
     * Serves as the entry point for the main Duke application.
     *
     * @param args The user input to the application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data.txt", new Storage("data.txt"), new TaskList());
        Ui ui = new Ui(duke.FILE_NAME);
        ui.printWelcomeMessage();

        // Read from file and load into tasks
        File file;

        try {
            file = new File(duke.FILE_NAME);
            if (file.createNewFile()) {
                ui.printCreateNewFile();
            } else {
                duke.FO.loadAllTasksFromFile(duke.TASK_LIST);
                ui.printLoadTasksFromFile();
                ui.printLineBreak();
            }
        } catch (IOException e) {
            ui.printErrorCreatingFile();
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while (!input.equals(Commands.BYE)) {
            duke.run(input);
            input = sc.nextLine();
        }

        // cleaning up and shutting down Duke
        ui.printByeMessage();
        sc.close();
    }

    /**
     * Runs the Duke application by processing the user input and executing necessary commands.
     *
     * @param input The input entered by the user.
     */
    public String run(String input) {
        Ui ui = new Ui(FILE_NAME);
        StringBuilder sb = new StringBuilder();
        try {
            String[] inputString = input.split(" ", 2);
            Parser parser = new Parser(inputString);
            String command = inputString[0];
            ui.printLineBreak();
            switch (command) {
            case Commands.LIST:
                sb.append(TASK_LIST.listTasks());
                System.out.println(sb.toString());
                break;
            case Commands.MARK:
                int taskNumber = parser.parseMark();
                sb.append(TASK_LIST.markAsDone(taskNumber - 1, FO)); // since display is 1-indexed
                break;
            case Commands.UNMARK:
                taskNumber = parser.parseUnmark();
                sb.append(TASK_LIST.markAsNotDone(taskNumber - 1, FO)); // since display is 1-indexed
                break;
            case Commands.TODO:
                Todo task = parser.parseTodo();
                sb.append(TASK_LIST.add(task, FO));
                break;
            case Commands.DEADLINE:
                Deadline deadline = parser.parseDeadline();
                sb.append(TASK_LIST.add(deadline, FO));
                break;
            case Commands.EVENT:
                Event event = parser.parseEvent();
                sb.append(TASK_LIST.add(event, FO));
                break;
            case Commands.DELETE:
                taskNumber = parser.parseDelete();
                sb.append(TASK_LIST.delete(taskNumber - 1, FO)); // since we store tasks 0-indexed in ArrayList
                break;
            case Commands.FIND:
                String keyword = parser.parseFind();
                sb.append(TASK_LIST.find(keyword));
                break;
            case Commands.BYE:
                sb.append("Program exiting in 3 seconds...");
                ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                service.schedule(() -> Platform.exit(), 3, TimeUnit.SECONDS);
                break;
            default:
                sb.append(ui.printCommandNotRecognized());
            }
        } catch (DukeException e) {
            return String.format("%s\n", e.getMessage());
        } catch (NumberFormatException e) {
            sb.append(ui.printErrorTaskNumber());
        } catch (java.time.format.DateTimeParseException e) {
            sb.append(ui.printErrorDatetimeFormat());
        } finally {
            ui.printLineBreak();
        }
        if (sb.toString().equals("")) {
            return "Sorry! I don't know how to respond to that";
        }
        return sb.toString();
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return run(input);
    }
}

