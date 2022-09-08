package duke;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DukeFormatCommandException;
import duke.exception.DukeIndexErrorException;
import duke.exception.DukeInvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main Duke class and runs MakiBot.
 *
 * @author Justin Peng
 */
public class Duke extends Application {
    /** Datetime formatter for user input which accepts dd/MM/yyyy or dd/MM/yyyy HH:mm */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy ")
            .optionalStart()
            .appendPattern("HH:mm ")
            .optionalEnd()
            .appendZoneText(TextStyle.SHORT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    /** Commands accepted by Duke */
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SAVE, FIND
    }
    /** User timezone */
    private ZoneId timeZone = ZoneId.of("GMT+00:00");

    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a new {@code Duke} object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().startBot();
    }

    /**
     * Starts MakiBot.
     */
    protected void startBot() {
        System.out.println("Hello! I'm MakiBot");
        timeZone = ui.getTimeZone(timeZone);
        storage.setSaveFilePath(ui.getSaveFile(storage.getSaveFilePath()));
        tasks = new TaskList(storage.loadTasks(timeZone));
        System.out.println("Welcome! What can I do for you?");
        eventLoop();
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
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Starts a conversation with MakiBot.
     */
    protected void eventLoop() {
        while (true) {
            try {
                // Event loop
                String input = ui.getInput();
                String[] fullCommand = parser.parseFullCommand(input);
                Command command = parser.parseCommand(input);

                // Handle commands
                switch (command) {
                // Exit command
                case BYE:
                    storage.updateSaveFile(tasks.getTasks());
                    ui.close();
                    return;
                case SAVE:
                    storage.updateSaveFile(tasks.getTasks());
                    break;
                // List all tasks
                case LIST:
                    ui.printAllTasks(tasks.getTasks());
                    break;
                // Mark task as done
                case MARK:
                    mark(fullCommand);
                    break;
                // Mark task as undone
                case UNMARK:
                    unmark(fullCommand);
                    break;
                case DELETE:
                    delete(fullCommand);
                    break;
                case FIND:
                    find(fullCommand);
                    break;
                case TODO:
                    newTodo(fullCommand);
                    break;
                case DEADLINE:
                    newDeadline(fullCommand);
                    break;
                case EVENT:
                    newEvent(fullCommand);
                    break;
                default:
                    throw new DukeInvalidCommandException();
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
    }

    /**
     * Generates an answer to the specified user input.
     *
     * @param input The user input.
     */
    protected void answer(String input) {
        try {
            String[] fullCommand = parser.parseFullCommand(input);
            Command command = parser.parseCommand(input);

            // Handle commands
            switch (command) {
            // Exit command
            case BYE:
                storage.updateSaveFile(tasks.getTasks());
                ui.close();
                // return "Tasks saved successfully!";
                return;
            case SAVE:
                storage.updateSaveFile(tasks.getTasks());
                // return "Tasks saved successfully!";
                break;
            // List all tasks
            case LIST:
                ui.printAllTasks(tasks.getTasks());
                break;
            // Mark task as done
            case MARK:
                mark(fullCommand);
                break;
            // Mark task as undone
            case UNMARK:
                unmark(fullCommand);
                break;
            case DELETE:
                delete(fullCommand);
                break;
            case FIND:
                find(fullCommand);
                break;
            case TODO:
                newTodo(fullCommand);
                break;
            case DEADLINE:
                newDeadline(fullCommand);
                break;
            case EVENT:
                newEvent(fullCommand);
                break;
            default:
                throw new DukeInvalidCommandException();
            }
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }

    /**
     * Marks a task from the task list as done.
     *
     * @param fullCommand The full input from the user containing the task's index.
     * @throws DukeException If the input is invalid.
     */
    protected void mark(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("mark");
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = tasks.get(taskNum);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + t);
        } catch (NumberFormatException e) {
            if (tasks.size() == 0) {
                throw new DukeIndexErrorException();
            } else {
                throw new DukeIndexErrorException(tasks.size());
            }
        }
    }

    /**
     * Marks a task from the task list as undone.
     *
     * @param fullCommand The full input from the user containing the task's index.
     * @throws DukeException If the input is invalid.
     */
    protected void unmark(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("unmark");
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = tasks.get(taskNum);
            t.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + t);
        } catch (NumberFormatException e) {
            if (tasks.size() == 0) {
                throw new DukeIndexErrorException();
            } else {
                throw new DukeIndexErrorException(tasks.size());
            }
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param fullCommand The full input from the user containing the task's index.
     * @throws DukeException If the input is invalid.
     */
    protected void delete(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("delete");
        }

        try {
            int taskNum = Integer.parseInt(fullCommand[1]) - 1;
            Task t = tasks.remove(taskNum);
            System.out.printf("Noted. I've removed this task:\n"
                            + "\t%s\n"
                            + "Now you have %d tasks in the list.%n",
                    t, tasks.size());
        } catch (NumberFormatException e) {
            if (tasks.size() == 0) {
                throw new DukeIndexErrorException();
            } else {
                throw new DukeIndexErrorException(tasks.size());
            }
        }
    }

    /**
    * Display tasks containing the keywords in the user input.
    *
    * @param fullCommand The input from the user containing keywords to search for.
    */
    protected void find(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("find");
        }

        ArrayList<Task> results = tasks.find(fullCommand[1].split(" "));
        ui.printAllTasks(results);
    }

    /**
     * Adds a new {@code Todo} task to the task list.
     *
     * @param fullCommand The full input from the user containing the task's description.
     * @throws DukeException If the input is invalid.
     */
    protected void newTodo(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("todo");
        }

        Todo td = new Todo(fullCommand[1]);
        tasks.add(td);
        ui.printNewTaskMessage(td, tasks.size());
    }

    /**
     * Adds a new {@code Deadline} task to the task list.
     *
     * @param fullCommand The full input from the user containing the task's description and deadline.
     * @throws DukeException If the input is invalid.
     */
    protected void newDeadline(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("deadline");
        }

        try {
            String[] newDeadline = fullCommand[1].split(" /by ", 2);
            System.out.println(newDeadline[1]);
            ZonedDateTime by = ZonedDateTime.parse(newDeadline[1] + " " + timeZone, DATE_TIME_FORMATTER);
            Deadline dl = new Deadline(newDeadline[0], by);
            tasks.add(dl);
            ui.printNewTaskMessage(dl, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            e.printStackTrace();
            throw new DukeFormatCommandException("deadline", "/by");
        }
    }

    /**
     * Adds a new {@code Event} task to the task list.
     *
     * @param fullCommand The full input from the user containing the task's description and datetime.
     * @throws DukeException If the input is invalid.
     */
    protected void newEvent(String[] fullCommand) throws DukeException {
        if (fullCommand.length < 2 || fullCommand[1].equals("")) {
            throw new DukeFormatCommandException("event");
        }

        try {
            String[] newEvent = fullCommand[1].split(" /at ", 2);
            ZonedDateTime at = ZonedDateTime.parse(newEvent[1] + " " + timeZone, DATE_TIME_FORMATTER);
            Event ev = new Event(newEvent[0], at);
            tasks.add(ev);
            ui.printNewTaskMessage(ev, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeFormatCommandException("event", "/at");
        }
    }
}
