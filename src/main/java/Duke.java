import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot that helps to keep track of tasks.
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList listOfTasks;

    private boolean isRunning = true;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            listOfTasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Please create a new folder 'data' in the 'iP' folder");
            listOfTasks = new TaskList(new ArrayList<Task>());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }


    private void run() {
        Parser parser = new Parser(listOfTasks, ui, storage);
        ui.printWelcomeMsg();
        Scanner scanner = new Scanner(System.in);

        while (isRunning && scanner.hasNextLine()) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                ui.printEndingMsg();
                isRunning = false;
            }
            try {
                parser.checkAndPerformOperations(userInput);
            } catch (DukeException e) {
                ui.showInvalidCommandError();
            }
        }
    }





}
