package meowmeow;
import meowmeow.commands.Command;

/**
 * <p>The Meowmeow class is the main class of the program.</p>
 * <p>This class is used to run the program.</p>
 */
public class Meowmeow {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Meowmeow class.
     * @param filePath is a String that specifies the filepath of the save file.
     */
    public Meowmeow(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(), storage);
    }

    /**
     * Method that runs the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } finally {
                ui.showLine();
            }
            storage.save(tasks.getTaskList());
        }
    }

    /**
     * Main method that runs the program.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        new Meowmeow("data/tasks.txt").run();
    }
}
