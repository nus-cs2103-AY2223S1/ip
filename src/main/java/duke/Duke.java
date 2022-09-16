package duke;

import java.nio.file.Paths;
import java.nio.file.Path;

import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Main class of the Duke chat-bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath Provides the file location for loading and storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Default constructor for the Duke class.
     */
    public Duke() {
    }

    /**
     * Shows a welcome message in the chat.
     *
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void sendWelcomeMessage(VBox dialogContainer, Image dukeImage) {
        String greetings = "Good day to you! I'm Bob!\n"
                + "I will help you to keep track of your tasks!\n"
                + "The following are your saved tasks:";
        for (Task t : this.tasks.getTasks()) {
            greetings = greetings + "\n       " + t.toString();
        }
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetings, dukeImage));
    }

    /**
     * Parses the user input and shows a message with a suitable response in the chat.
     *
     * @param input The user input.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param userImage The image of the user.
     * @param dukeImage The image of Duke.
     */
    public void handleUserInput(String input, VBox dialogContainer, Image userImage, Image dukeImage) {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));

        Parser parser = new Parser();
        if (!(input.equals("bye"))) {
            try {
                parser.parse(input, this.tasks, dialogContainer, dukeImage);
            } catch (DukeException e) {
                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(e.getMessage(), dukeImage));
            }
        } else {
            storage.save(tasks);
            String byeMessage = "Sad to see you go! Visit me again soon!";
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(byeMessage, dukeImage));
        }
    }

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        Path pathToDuke = Paths.get(workingDir, "data", "duke.txt");
        new Duke(String.valueOf(pathToDuke));
    }
}
