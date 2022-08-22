import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //private static ArrayList<Task> tasks = new ArrayList<>();
    //private static final Scanner sc = new Scanner(System.in);

    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private boolean byeActivated;

    public Duke(String folderPath, String filePath) {
        this.taskList = new TaskList();
        this.ui = new UI();
        this.storage = new Storage(folderPath, filePath);
        this.byeActivated = false;
    }

    public enum Instructions {
        bye, deadline, delete, event, list, mark, todo, unmark
    }

    public void run() {
        ui.showWelcome();
        storage.startUpPullStorage(ui, taskList);
        while (!byeActivated && ui.hasInput()) {
            try {
                String input = ui.takeInput();
                ui.showDividerLine();
                Command c = Parser.parseInput(input);
                c.execute(taskList, ui, storage);
                byeActivated = c.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data", "./data/Duke.txt").run();
    }
}