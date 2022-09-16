package duke;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Duke extends Application {


    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasklist;

    private boolean isRunning = false;

    /**
     * Constructs Duke with a file path.
     *
     * @param filePath String representing save file location.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasklist = new TaskList();
    }

    /**
     * Constructs Duke (default)
     */
    public Duke() {
        //default constructor
    }

    /**
     * Starts the Duke Application.
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");
        HBox userInteraction = new HBox();
        userInteraction.getChildren().addAll(userInput, sendButton);

        VBox overallLayout = new VBox();
        overallLayout.getChildren().addAll(scrollPane, userInteraction);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().add(overallLayout);

        Scene scene = new Scene(mainLayout);

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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
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
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            String text = "";

            //startup function
            if (!isRunning) {
                text = startup();
            }

            //normal function
            try {
                Pair<Command, String> p = parser.parse(input);
                text = text + execute(p.x, p.y);
            } catch (DukeTaskException e) {
                text = text + ("Task can't be created:" + e);
            } catch (DukeException e) {
                text = text + ("Oops! " + e);
            } catch (NumberFormatException e) {
                text = text + ("Duke could not identify the index");
            }

            assert text != "";
            return text;

        } catch (DukeException e) {
            return ("Error occurred during loading: " + e);
        } catch (IOException e) {
            return ("Error occurred with saving/loading: " + e);
        }
    }

    /**
     * Starts up Duke.
     *
     * @return String Duke's greeting.
     * @throws IOException  if there's an error with file access
     * @throws DukeException  if there's an error with Duke.
     */
    private String startup() throws IOException, DukeException {
        isRunning = true;

        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        tasklist = new TaskList();

        String text;
        text = ui.greet();

        storage.load(tasklist);
        storage.save(tasklist); // just in case user exits without any list modifications

        return text;
    }

    /**
     * Executes Duke's actions based on parsed input.
     *
     * @param c enum representing command to be completed.
     * @param s string containing the specifics of a command.
     * @return  String text to be displayed by Duke.
     * @throws IOException  If something went wrong with saving/loading.
     * @throws DukeTaskException  If the specifics of a command is wrong.
     */
    private String execute(Command c, String s) throws IOException, DukeException {
        switch(c) {
        case BYE:
            stop();
            ui.end();
            isRunning = false;
            return ("Bye. Hope to see you again soon!");
            //fallthrough
        case LIST:
            return tasklist.list();
            //fallthrough
        case MARK:
            Task marked = tasklist.mark(Integer.parseInt(s) - 1);
            storage.save(tasklist);
            return ("This task is now marked: \n" + marked.getName());
            //fallthrough
        case UNMARK:
            Task unmarked = tasklist.unmark(Integer.parseInt(s) - 1);
            storage.save(tasklist);
            return ("This task is now unmarked: \n" + unmarked.getName());
            //fallthrough
        case DELETE:
            if (s.equals("all")) {
                tasklist.deleteAll();
                storage.save(tasklist);
                assert tasklist.size() == 0;
                return ("All tasks have been deleted! :D");
            } else {
                Task deleted = tasklist.delete(Integer.parseInt(s) - 1);
                storage.save(tasklist);
                return ("This task is now removed: \n" + deleted.getName());
            }
            //fallthrough
        case TODO:
            Task todo = tasklist.addTodo(s);
            storage.save(tasklist);
            return ("added todo: " + todo.getName());
            //fallthrough
        case DEADLINE:
            Task deadline = tasklist.addDeadline(s);
            storage.save(tasklist);
            return ("added deadline: " + deadline.getName());
            //fallthrough
        case EVENT:
            Task event = tasklist.addEvent(s);
            storage.save(tasklist);
            return ("added event: " + event.getName());
            //fallthrough
        case FIND:
            return tasklist.findList(s);
            //fallthrough
        }
        throw new DukeException("Command not recognised D:");
    }

    /**
     * Stops Duke from running.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        System.out.println("--------------------------------------");
    }

    /**
     * Enums for the range of commands Duke can complete.
     */
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }
}