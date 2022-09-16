package duke;

import duke.exceptions.DukeException;
import duke.data.LocalStorage;
import duke.handlers.*;
import duke.models.*;
import duke.services.Parser;
import duke.utils.Commands;

import javafx.application.Application;
import javafx.scene.Node;
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
import java.util.Scanner;
import static duke.services.Ui.dukePrint;

public class Duke{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private final LocalStorage storage;
    private final Parser parser;
    private TaskList list;

    /**
     * Constructor for Duke instance.
     */
    public Duke() {
        parser = new Parser();
        storage = new LocalStorage();
        list = new TaskList();
    }

    /**
     * Gets the next Response for the current Duke instance.
     *
     * @return Duke's next response.
     * @param input: User input for Duke.
     */
    public String getResponse(String input) {
        list = storage.load();

        String response;
        Commands command = parser.parseCommand(input);
        String[] fullCommand = parser.parseFullCommand(input);

        try {
            switch (command) {
            case BYE:
                response = ByeHandler.getResponse();
                break;
            case LIST:
                response = ListHandler.getResponse(list);
                break;
            case TODO:
                response = TodoHandler.getResponse(list, fullCommand[1]);
                break;
            case DEADLINE:
                response = DeadlineHandler.getResponse(list, fullCommand[1]);
                break;
            case EVENT:
                response = EventHandler.getResponse(list, fullCommand[1]);
                break;
            case MARK:
                response = MarkHandler.getResponse(list, fullCommand[1]);
                break;
            case UNMARK:
                response = UnmarkHandler.getResponse(list, fullCommand[1]);
                break;
            case FIND:
                response = FindHandler.getResponse(list, fullCommand[1]);
                break;
            case DELETE:
                response = DeleteHandler.getResponse(list, fullCommand[1]);
                break;
            default:
                response = "Unknown Command!";
                break;
            }
            System.out.print(list);
            storage.write(list);
        } catch (DukeException dukeException) {
            response = (dukeException.getMessage());
        }
        return response;
    }
}
