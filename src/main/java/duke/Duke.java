package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Main class.
 *
 * @author Safwan A0235287X
 */
public class Duke {

    private Storage storage;
    private String filePath = "data/duke.txt";
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Spongebob.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Squidward.png"));
    

    /**
     * Constructor for the Duke main class.
     */
    public Duke () {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException, IOException {
        if (input.equals("bye")) {
            String response = "Bye. Hope to see you again soon.";
            return response;
        }

        String[] word = input.split(" ");

        if (input.equals("list")) {
            return taskList.list();

        } else if (word[0].equals("mark")) {
            Integer num = Integer.parseInt(word[1]);
            return taskList.mark(num);

        } else if (word[0].equals("unmark")) {
            Integer num = Integer.parseInt(word[1]);
            return taskList.unmark(num);

        } else if (word[0].equals("todo")) {
            if (input.endsWith("todo")) {
                throw new DukeException("Ooops, the description of todo cannot be empty!");
            }

            String substringtd = input.replaceAll("todo ", "");
            return taskList.todo(substringtd);

        } else if (word[0].equals("deadline")) {
            if (input.endsWith("deadline")) {
                throw new DukeException("Ooops, the description of deadline cannot be empty!");
            }

            String[] phrase = input.split("/by");
            String substringdl1 = phrase[0].replaceAll("deadline", "");
            String substringdl2 = phrase[1];
            return taskList.deadline(substringdl1, substringdl2);

        } else if (word[0].equals("event")) {
            if (input.endsWith("event")) {
                throw new DukeException("Ooops, the description of event cannot be empty!");
            }

            String[] phrase = input.split("/at");
            String substringdl1 = phrase[0].replaceAll("event", "");
            String substringdl2 = phrase[1];
            return taskList.event(substringdl1, substringdl2);

        } else if (word[0].equals("delete")) {
            Integer num = Integer.parseInt(word[1]);
            return taskList.delete(num);

        } else if (word[0].equals("find")) {
            String content = input.replace("find", "");
            return taskList.find(content);

        } else {
            throw new DukeException("Oooops, sorry I don't know what you are talking about :(");
        }

    }
}
