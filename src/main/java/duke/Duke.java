package duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

// ACTUAL DUKE FILE IS IN main/dukelauncher/DukePro
// THIS IS FOR IDE INTERFACE

public class Duke extends Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Parser parser;

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Creates a Duke object with the specified file path.
     * @param filePath The file path of the Duke.txt file where the last saved task list will be retrieved from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks, storage);
    }

    /**
     * Runs Duke, which starts the Duke chatbot.
     */
    public void run() {
        this.ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (parser.getIsOpen()) {
            String input = scanner.nextLine();
            try {
                parser.read(input).execute();
            } catch (DukeException error) {
                ui.showError(error);
            }
        }
        ui.showSaved();
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }




    public static void main(String[] args) {
        new Duke("src/dukesave/Duke.txt").run();
    }
}
