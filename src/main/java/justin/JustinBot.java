package justin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import justin.command.Command;

/**
 * Represents the main class in which
 * the program is run.
 * @author Justin Cheng.
 */
public class JustinBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private MainWindow mw;

    /**
     * Constructor for the Duke class.
     * @param filePath The path of the data file
     *                 in String.
     */
    public JustinBot(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            storage.setMw(mw);
            tasks = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }

    /**
     * Runs the program by iterating inputs and
     * parsing commands, which leads to actions
     * being triggered in the program.
     */
//    public void run() {
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showText(e.toString());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }
    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setMw(MainWindow mw) {
        this.mw = mw;
    }

}
