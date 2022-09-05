import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Doemon {

    /**
     * The file path of the file where tasks will be saved to.
     */
    private static final String TASK_FILE_PATH = "./data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Doemon(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (TaskDataException tde){
            ui.showError(tde);
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Doemon chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String inputString = ui.readCommand();
                Command c = Parser.parse(inputString);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DoemonException de) {
                ui.showError(de);
            }
        }
    }

    public static void main(String[] args) {
        new Doemon(TASK_FILE_PATH).run();
    }
}
