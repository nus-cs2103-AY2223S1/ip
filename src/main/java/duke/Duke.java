package duke;

import duke.command.Command;
import duke.model.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main class for the chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * A constructor for the chatbot.
     *
     * @param filePath the file path for storage
     */
    public Duke (String filePath) {
        Scanner sc = new Scanner(System.in);
        this.ui = new Ui(sc);
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(storage.load());
    }

    public static void main(String[] args) {
//        new Duke("data/Duke.txt").run();
    }

//    /**
//     * Runs the chatbot program.
//     */
//    public void run() {
//        ui.greetUser();
//        boolean isExit = false;
//
//        ui.showDivider();
//        while (!isExit) {
//            String fullCommand = ui.readCommand();
//            ui.showDivider();
//            Command c = Parser.parse(fullCommand);
//            c.execute(tasks, storage, ui);
//            isExit = c.getIsExit();
//            if (!isExit) {
//                ui.showDivider();
//            }
//        }
//        ui.close();
//        ui.sayBye();
//        ui.showDivider();
//    }

    /**
     * Returns a string representation of a response based on the user's input
     * @param input
     * @return a string representation of the response
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        return command.execute(tasklist, storage);
    }
}
