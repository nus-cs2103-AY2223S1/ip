package Duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

/**
 * Duke.Duke is a basic chat-bot stores tasks into a list.
 *
 * @author Chi Song Yi Amadeus
 * @version GUI
 * @since 17-08-2022
 */
public class Duke extends Application {

    /**
     * storage object deals with read/write interactions with file
     */
    private static Storage storage = null;

    /**
     * tasks manages a list of Task objects
     */
    private static TaskList tasks;

    /**
     * ui handles user inputs and outputs
     */
    private static Ui ui = null;

    /**
     * parser interpretes user commands
     */
    private final Parser parser;


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));


    /**
     * possible user commands.
     */
    enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        CREATETASK,
        FIND
    }

    /**
     * Constructor for Duke with no parameters.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showErrorReadingMessage();
        }
    }

    @Override
    public void start(Stage stage) {
        // step 1
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // step 3
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        Commands command = parser.parseCommand(userText.trim());
        if (command == Commands.BYE) {
            // end program
            ui.showGoodbyeMessage();
        } else {
            assert user != null;
            assert duke != null;
            String dukeText = executeCommand(command, userInput.getText());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getDukeDialog(dukeText, duke)
            );
        }

        userInput.clear();
    }


    /**
     * Processes and runs user commands.
     *
     * @param command user command
     * @param input   input string
     */
    static String executeCommand(Commands command, String input) {
        if (command == Commands.LIST) {
            StringBuilder list = new StringBuilder();
            for (int i = 1; i <= tasks.getSize(); i++) {
                String index = String.format("%d.", i);
                String entry = index + tasks.getItem(i).toString() + "\n";
                //ui.showEntry(entry);
                list.append(entry);
            }
            return list.toString();

        } else if (command == Commands.MARK) {
            String number = input.split(" ", 2)[1];
            try {
                int num = Integer.parseInt(number);
                String str = tasks.markTasks("mark", num);
                storage.rewriteFile(tasks.getTaskArray());
                return str;

            } catch (NumberFormatException e) {
                ui.showInvalidTaskMessage();
            } catch (IOException e) {
                ui.showErrorWritingMessage();
            }
        } else if (command == Commands.UNMARK) {
            try {
                String number = input.split(" ", 2)[1];
                int num = Integer.parseInt(number);
                String str = tasks.markTasks("unmark", num);
                storage.rewriteFile(tasks.getTaskArray());
                return str;

            } catch (NumberFormatException e) {
                ui.showInvalidTaskMessage();
            } catch (IOException e) {
                ui.showErrorWritingMessage();
            }
        } else if (command == Commands.DELETE) {
            String number = input.split(" ", 2)[1];
            try {
                int num = Integer.parseInt(number);
                Task toDelete = tasks.getTaskArray().get(num - 1);
                tasks.delete(toDelete);
                String str = ui.showDeleteMessage(toDelete, tasks.getSize());
                storage.writeToFile(toDelete);
                return str;

            } catch (NumberFormatException e) {
                return ui.showInvalidTaskMessage();
            } catch (IndexOutOfBoundsException e) {
                return ui.showInvalidIndexMessage();
            } catch (IOException e) {
                return ui.showErrorWritingMessage();
            }
        } else if (command == Commands.CREATETASK) {
            try {
                Task newTask = tasks.createTask(input, false);
                if (newTask != null) {
                    storage.writeToFile(newTask);
                    return newTask.addMessage(tasks.getSize());
                }

            } catch (DukeException.EmptyTaskException | DukeException.UnkownCommandException | DukeException.InvalidParameterException error) {
                return error.getMessage();
            } catch (IOException e) {
                return ui.showErrorWritingMessage();
            }
        } else if (command == Commands.FIND) {
            try {
                String word = input.split(" ", 2)[1];
                StringBuilder list = new StringBuilder();
                for (int i = 1; i <= tasks.getSize(); i++) {
                    String index = String.format("%d.", i);
                    String entry = index + tasks.getItem(i).toString();
                    if (entry.contains(word)) {
                        //ui.showEntry(entry);
                        list.append(entry);
                    }
                }
                return list.toString();
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showInvalidFindFiledMessage();
            }
        }
        return "";
    }
}
