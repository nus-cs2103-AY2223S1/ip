package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Callable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Duke class that handles user inputs
 */
public class Duke extends Application {
    private ArrayList<Task> all = new ArrayList<>();
    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes Duke instance. Initializes the ui, taskList, parser and Storage instance.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        storage = new Storage("data/Duke.txt");
    }

    /**
     * Start the launcher
     */
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
        stage.setResizable(true);
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

        // more code to be added here later
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
        //Part 3. Add functionality to handle user input.

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
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
    private void handleUserInput() {
        Label userText = new Label("Your input:    " + userInput.getText());
        Label dukeText = getResponse(userInput.getText());
        userText.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Get the response from the user.
     */
    private Label getResponse(String input) {
        boolean isExit = false;
        String res = "";
        Label response;
        try {
            String fullCommand = input;
            Command c = parser.parse(fullCommand);
            response = new Label(c.execute());
            response.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            isExit = c.isExit();
            storage.saveData();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            response = new Label(e.getMessage());
            response.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }
        return response;
    }

    // abstractions

    /**
     * Encapsulates all UI.
     * <p>
     * This class contain the read and write of ui components
     */
    private class Ui {
        private Scanner sc;

        public Ui() {
            sc = new Scanner(System.in);
        }

        /**
         * Display welcome message
         */
        public void welcome() {
            System.out.println("Hello! I am Duke");
            System.out.println("What can I do for you?");
        }

        /**
         * Read command from prompt
         */
        public String readCommand() {
            if (sc.hasNext()) {
                return sc.nextLine();
            } else {
                return "";
            }
        }

        public void invalidInput() {
            System.out.println("I'm sorry, but I don't know what that means :-(");
        }

        public void showError(String s) {
            System.out.println(s);
        }

        /**
         * Display result after sort.
         * @return a string representing the return message
         */
        public String sorted() {
            System.out.println("sorted!");
            return "";
        }

        /**
         * Display result after mark.
         * @return a string representing the return message
         */
        public String marked(Task task) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.toString());
            String str = "Nice! I've marked this task as done:\n" + task.toString();
            return str;
        }

        /**
         * Display result after unmark.
         * @return a string representing the return message
         */
        public String unmarked(Task task) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.toString());
            String str = "OK, I've marked this task as not done yet:\n" + task.toString();
            return str;
        }

        /**
         * Display result after add.
         * @return a string representing the return message
         */
        public String added(Task task) {
            System.out.println("Got it. I've added this task:");
            System.out.printf("  added: %s\n", task.toString());
            System.out.printf("Now you have %s tasks in the list.\n", taskList.count());
            String str = "Got it. I've added this task:\n" + "  added: %s\n" + task.toString() + "\n"
                    + "Now you have %s tasks in the list.\n" + taskList.count();
            return str;
        }

        /**
         * Display result after delete.
         * @return a string representing the return message
         */
        public String deleted(Task task) {
            System.out.println("Got it. I've deleted this task:");
            System.out.printf("  added: %s\n", task.toString());
            System.out.printf("Now you have %s tasks in the list.\n", taskList.count());
            String str = "Got it. I've deleted this task:\n" + "  added: %s\n" + task.toString()
                    + "Now you have %s tasks in the list.\n" + taskList.count();
            return str;
        }

        public void exit() {
            System.out.println("Bye!");
        }

        public void showLine() {
            System.out.println("_______________________________");
        }
    }

    /**
     * Encapsulates the task list.
     * <p>
     * This class handles the add and delete of tasks in the task list
     */
    private class TaskList {
        private ArrayList<Task> all;

        public TaskList() {
            all = new ArrayList<>();
        }

        /**
         * Displays the list
         */
        public String displayList() {
            String res = "Here are the tasks in your list:\n";
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count(); i++) {
                System.out.printf("%s. %s\n", i + 1, all.get(i).toString());
                res += (i + 1) + ". " + all.get(i).toString() + "\n";
            }
            return res;
        }

        /**
         * Find the target task in the list.
         */
        public void findList(String target) {
            System.out.println("Here are the tasks in your list with the keyword:");
            for (int i = 0; i < count(); i++) {
                if (all.get(i).getDescription().indexOf(target) != -1) {
                    System.out.printf("%s. %s\n", i + 1, all.get(i).toString());
                }
            }
        }

        public void addTask(Task task) {
            all.add(task);
            ui.added(task);
        }

        public void deleteTask(int index) {
            Task deleted = all.get(index - 1);
            all.remove(index - 1);
            ui.deleted(deleted);
        }

        public Task getTask(int index) {
            return all.get(index);
        }

        public int count() {
            return all.size();
        }
    }

    private class Storage {
        private String filePath;

        public Storage(String path) {
            filePath = path;
        }

        /**
         * Loads data from the text file "Duke.txt". Store the data in the taskList instance
         *
         * @throws FileNotFoundException
         */
        public void loadData() throws FileNotFoundException {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String s = sc.nextLine();
                char type = s.charAt(1);
                boolean isDone = s.charAt(4) == 'X';
                String des = s.substring(7);
                Task newTask;

                if (type == 'T') {
                    newTask = new Todo(des);
                } else if (type == 'D') {
                    int i = des.indexOf('(');
                    newTask = new Deadline(des.substring(0, i - 1),
                            parser.parseStringToDate(des.substring(i + 5, i + 15)));
                } else if (type == 'E') {
                    int i = des.indexOf('(');
                    newTask = new Event(des.substring(0, i - 1),
                            parser.parseStringToDate(des.substring(i + 5, i + 15)));
                } else {
                    newTask = null;
                }

                taskList.addTask(newTask);
            }
        }

        /**
         * Write data to the text file "Duke.txt".
         */
        public void saveData() {
            try {
                FileWriter fw = new FileWriter(filePath);
                String s = "";
                for (int i = 0; i < all.size(); i++) {
                    s += all.get(i).toString() + "\n";
                }
                fw.write(s);
                fw.close();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * Abstract class for command.
     */
    private abstract class Command {
        /**
         * Execute the command based on its type.
         */
        public abstract String execute();

        /**
         * Returns whether it is an exit command.
         *
         * @return a boolean of whether it is an exit command.
         */
        public abstract boolean isExit();
    }

    /**
     * Encapsulates AddCommand.
     */
    private class AddCommand extends Command {
        private TaskType type;
        private String description;
        private LocalDate date;

        public AddCommand(TaskType type, String description ) {
            super();
            this.type = type;
            this.description = description;
        }


        /**
         * Execute the add command. Determine the type of task added then add it to taskList.
         */
        @Override
        public String execute() {
            String res = "";
            if (type == TaskType.TODO) {
                taskList.addTask(new Todo(description));
            } else if (type == TaskType.DEADLINE) {
                try {
                    taskList.addTask(new Deadline(parser.extractDescription(description),
                                                  parser.extractDate(description)));
                } catch (DukeException e) {
                    ui.showError("format incorrect!");
                }
            } else if (type == TaskType.EVENT) {
                try {
                    taskList.addTask(new Event(parser.extractDescription(description),
                            parser.extractDate(description)));
                } catch (DukeException e) {
                    ui.showError("format incorrect!");
                }
            } else {
                ui.showError("type not supported");
            }
            res = "Got it. I've added this task:\n" + "Now you have" + taskList.count() + " tasks in the list.\n";
            return res;
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    /**
     * Encapsulates ListCommand.
     */
    private class ListCommand extends Command {
        public ListCommand() { super(); }

        /**
         * Execute the List command. List all tasks in taskList.
         */
        @Override
        public String execute() {
            return taskList.displayList();
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    /**
     * Encapsulates FindCommand.
     */
    private class FindCommand extends Command {
        private String target;

        public FindCommand(String target) {
            super();
            this.target = target;
        }

        /**
         * Execute the find command. Display all tasks containing the input text.
         */
        @Override
        public String execute() {
            taskList.findList(target);
            return "";
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    /**
     * Encapsulates MarkCommand.
     */
    private class MarkCommand extends Command {
        private boolean isMark;
        private int index;

        public MarkCommand(boolean isMark, int index) {
            super();
            this.isMark = isMark;
            this.index = index;
        }

        /**
         * Execute the mark command.
         * Mark the task as done or undone.
         */
        @Override
        public String execute() {
            Task task = taskList.getTask(index - 1);

            if (isMark) {
                task.markAsDone();
                ui.marked(task);
                return "Nice! I've marked this task as done\n type list to check the tasks";
            } else {
                task.markAsUndone();
                ui.unmarked(task);
                return "Nice! I've marked this task as undone\n type list to check the tasks";
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    /**
     * Encapsulates DeleteCommand.
     */
    private class DeleteCommand extends Command {
        private int index;

        public DeleteCommand(int index) {
            super();
            this.index = index;
        }

        /**
         * Execute the delete command.
         * Delete the specified task in taskList.
         * */
        @Override
        public String execute() {
            if (index < 1 || index > taskList.count()) {
                ui.showError("index out of range");
            } else {
                taskList.deleteTask(index);
            }
            return "OK. I have deleted this task";
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    /**
     * Encapsulates ExitCommand.
     */
    private class ExitCommand extends Command {
        public ExitCommand() { super(); }

        /**
         * Execute the command.
         * @return a string representing the return message
         */
        @Override
        public String execute() {
            ui.exit();
            return "Bye!";
        }

        @Override
        public boolean isExit() {
            return true;
        }
    }

    /**
     * Encapsulates SortCommand.
     */
    private class SortCommand extends Command {
        public SortCommand() { super(); }

        /**
         * Execute the command.
         * @return a string representing the return message
         */
        @Override
        public String execute() {

            ArrayList<Todo> todos = new ArrayList<>();
            ArrayList<Deadline> deadlines = new ArrayList<>();
            ArrayList<Event> events = new ArrayList<>();

            for (int i = 0; i < all.size(); i++) {
                Task currentTask = all.get(i);
                if (currentTask.getTaskType() == TaskType.TODO) {
                    todos.add((Todo) currentTask);
                } else if (currentTask.getTaskType() == TaskType.EVENT) {
                    events.add((Event) currentTask);
                } else {
                    deadlines.add((Deadline) currentTask);
                }
            }
            all = new ArrayList<>();
            for (int i = 0; i < todos.size(); i++) {
                all.add(todos.get(i));
            }
            for (int i = 0; i < events.size(); i++) {
                all.add(events.get(i));
            }
            for (int i = 0; i < deadlines.size(); i++) {
                all.add(deadlines.get(i));
            }
            ui.sorted();
            return "Sorted!";
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    /**
     * Encapsulates Parser.
     *
     * This class handles the parsing of user inputs.
     */
    private class Parser {
        private String list;
        private String todo;
        private String ddl;
        private String event;
        private String mark;
        private String unmark;
        private String exit;
        private String delete;
        private String find;
        private String sort;

        public Parser() {
            list = "list";
            todo = "todo";
            event = "event";
            ddl = "deadline";
            unmark = "unmark";
            mark = "mark";
            exit = "bye";
            delete = "delete";
            find = "find";
            sort = "sort";
        }

        /**
         * Parses the command.
         *
         * @param s the String of command
         * @return the respective command
         */
        public Command parse(String s) throws DukeException {
            int space = s.indexOf(" ");
            if (s.equals(list)) {
                return new ListCommand();
            } else if (s.equals(sort)) {
                return new SortCommand();
            } else if (s.equals(exit)) {
                return new ExitCommand();
            } else if (space == -1 || s.substring(0, space) == "") {
                throw new DukeException("Sorry I cannot understand! Retry another command.");
            } else {
                String identifier = s.substring(0, space);
                String description = s.substring(space + 1);

                if (identifier.equals(todo)) {
                    return new AddCommand(TaskType.TODO, description);
                } else if (identifier.equals(event)) {
                    return new AddCommand(TaskType.EVENT, description);
                } else if (identifier.equals(ddl)) {
                    return new AddCommand(TaskType.DEADLINE, description);
                } else if (identifier.equals(delete)) {
                    return new DeleteCommand(extractIndex(s));
                } else if (identifier.equals(mark)) {
                    return new MarkCommand(true, extractIndex(s));
                } else if (identifier.equals(unmark)) {
                    return new MarkCommand(false, extractIndex(s));
                } else if (identifier.equals(find)) {
                    return new FindCommand(description);
                } else {
                    throw new DukeException("Cannot find matching key word!");
                }
            }
        }

        /**
         * Extracts the index from a command.
         *
         * @param s the String of command
         * @return an int representing  the index
         */
        public int extractIndex(String s) throws DukeException {
            int pos = s.indexOf(" ");
            if (pos == -1) {
                throw new DukeException("Invalid structure, include the index after mark/unmark");
            }

            int index;
            try {
                index = Integer.parseInt(s.substring(pos + 1));
            } catch (Exception e) {
                throw new DukeException("Invalid argument. only integers are accepted!");
            }
            return index;
        }

        /**
         * Extracts the date from a command.
         *
         * @param s the String of command
         * @return a string representing  the date
         */
        public LocalDate extractDate(String s) throws DukeException {
            int pos = s.indexOf("/by");
            if (pos == -1) {
                pos = s.indexOf("/at");
            }
            if (pos == -1) {
                throw new DukeException("Invalid structure, include /by or /at before the date");
            }
            return parseStringToDate(s.substring(pos + 4));
        }

        /**
         * Extracts the description from a command.
         *
         * @param s the String of command
         * @return a string representing  the description
         */
        public String extractDescription(String s) throws DukeException {
            int pos = s.indexOf("/by");
            if (pos == -1) {
                pos = s.indexOf("/at");
            }
            if (pos == -1) {
                throw new DukeException("Invalid structure, include /by or /at before the date");
            }
            return s.substring(0, pos - 1);
        }

        /**
         * Parses the string to a date.
         *
         * @param s the String to be converted
         * @return a LocalDate represented by the String
         */
        public LocalDate parseStringToDate(String s) {
            return LocalDate.parse(s.replace('/', '-'));
        }

        /**
         * Parses the date to a string.
         *
         * @param date the LocalDate to be converted
         * @return a string representation of the date
         */
        public String parseDateToString(LocalDate date) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));

        }

    }

    /**
     * Run the program.
     */
    public void run() {

        try {
            storage.loadData();
        } catch (FileNotFoundException e) {
            ui.showError("file duke.txt not found under the directory!");
        }
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute();
                isExit = c.isExit();
                storage.saveData();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        assert isExit == false : "isExit should be false";
    }

    /**
     * Encapsulates all types of tasks.
     * <p>
     * This class contain the description and type of the task
     */
    public class Task {
        protected String description;
        protected boolean isDone;
        private String todo = "todo";
        private String ddl = "deadline";
        private String event = "event";
        /**
         * Constructor for Task class.
         *
         * @param description description string of the task
         */
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        /**
         * Returns the status icon of a task, representing whether it is done.
         *
         * @return a string containing the status icon
         */
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", getStatusIcon(), description);
        }

        public void markAsDone() {
            isDone = true;
        }

        public void markAsUndone() {
            isDone = false;
        }

        public String getDescription() {
            return description;
        }

        /**
         * Returns the type of task.
         *
         * @return a TaskType representing the task.
         */
        public TaskType getTaskType() {
            if (this.toString().indexOf(1) == 'T') {
                return TaskType.TODO;
            } else if (this.toString().indexOf(1) == 'E') {
                return TaskType.EVENT;
            } else if (this.toString().indexOf(1) == 'D') {
                return TaskType.DEADLINE;
            } else {
                return null;
            }
        }
    }

    /**
     * Encapsulates deadline tasks.
     * <p>
     * This class contain the description and the due date of the deadline instance.
     */
    public class Deadline extends Task {
        protected LocalDate by;

        /**
         * Constructor for Deadline class.
         *
         * @param description description string of the task
         * @param by a LocalDate instance representing the due date
         */
        public Deadline(String description, LocalDate by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + parser.parseDateToString(by) + ")";
        }
    }

    /**
     * Encapsulates todo tasks.
     * <p>
     * This class contain the description of a todo instance.
     */
    public class Todo extends Task {
        /**
         * Constructor for Todo class.
         *
         * @param description description string of the task
         */
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    /**
     * Encapsulates event tasks.
     * <p>
     * This class contain the description and the date of the event instance.
     */
    public class Event extends Task {
        private LocalDate at;

        /**
         * Constructor for Event class.
         *
         * @param description description string of the task
         * @parama at a LocalDate instance representing the date of the event
         */
        public Event(String description, LocalDate at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + parser.parseDateToString(at) + ")";
        }
    }

    /**
     * Encapsulates all types of Exceptions for Duke.
     */
    private class DukeException extends Exception {
        public DukeException(String message) {
            super("OOPS!!" + message);
        }
    }

}
