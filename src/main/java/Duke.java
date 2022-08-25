import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * The Duke is a personalized chatbot.
 */
public class Duke {

    private static Storage myStorage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        myStorage = new Storage(filePath);
        try {
            tasks = new TaskList(myStorage.load());
        } catch (FileNotFoundException e){
            ui.showLoadingError();
            File path = new File(filePath);
            tasks = new TaskList();
            try {
                path.createNewFile();
            } catch (IOException e2) {
                path.getParentFile().mkdirs();
            }
        }
    }






    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command cmd = Parser.parse(fullCommand);
                cmd.execute(tasks, ui, myStorage);
                isExit = cmd.isExit();
            } catch (IllegalArgumentException e) {
                ui.showError("I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e2) {
                ui.showError(e2.getMessage());
            }

        }
    }

    /**
     * The main program for Duke.
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        new Duke("./data/duke.txt").run();
    }
}
