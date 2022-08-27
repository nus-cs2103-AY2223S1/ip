import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static String DIRECTORY = "./data/";
    public static String FILENAME = "todo.txt";


    Duke(String directory, String fileName) {
        ui = new Ui();
        try {
            storage = new Storage(directory, fileName);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showPrompt();
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data", "todo.txt");
        duke.run();
    }
}