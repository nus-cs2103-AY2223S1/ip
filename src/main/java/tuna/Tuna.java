package tuna;


import javafx.application.Platform;
import tuna.command.Command;
import tuna.command.CommandType;
import tuna.command.LoadDataCommand;
import tuna.command.SaveDataCommand;
import tuna.command.WelcomeCommand;

/**
 * Represents a Duke bot. A Duke object contains its storage, task list, ui, parser, folder path and file path.
 */
public class Tuna {

    /** Storage to handle file loading and saving */
    private Storage storage;
    /** Task list to handle task related functionalities */
    private TaskList tasks;
    /** Ui to handle interactions with the user */
    private Ui ui;
    /** Parser to parse the user inputs */
    private Parser parser;
    /** Folder path of the data file */
    private String folderPath;
    /** File path of the data file */
    private String filePath;

    /**
     * Creates a Duke object.
     *
     * @param folderPath folder path of the data file.
     * @param filePath file path of the data file.
     */
    public Tuna(String folderPath, String filePath) {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.folderPath = folderPath;
        this.filePath = filePath;
        new WelcomeCommand().execute(tasks, ui, storage);
    }

    /**
     * Initialises data and sends welcome message when user first runs the GUI.
     *
     * @return welcome message.
     * @throws TunaException exception thrown during execution.
     */
    public String initialise() throws TunaException {
        new LoadDataCommand(folderPath, filePath).execute(tasks, ui, storage);
        return new WelcomeCommand().execute(tasks, ui, storage);
    }

    /**
     * Runs the functionalities of the Duke bot in terminal.
     */
    public void run() {
        try {
            new LoadDataCommand(folderPath, filePath).execute(tasks, ui, storage);
            System.out.println(new WelcomeCommand().execute(tasks, ui, storage));
            boolean isByeCommand = false;
            while (!isByeCommand) {
                try {
                    String inputs = ui.readCommand();
                    Command command = parser.parse(inputs);
                    System.out.println(command.execute(tasks, ui, storage));
                    isByeCommand = command.getType() == CommandType.BYE;
                } catch (TunaException e) {
                    ui.showError(e.getMessage());
                }
            }
            new SaveDataCommand(filePath).execute(tasks, ui, storage);
        } catch (TunaException e) {
            ui.showError(e.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            if (command.getType() == CommandType.BYE) {
                new SaveDataCommand(filePath).execute(tasks, ui, storage);
                Platform.exit();
            }
            return command.execute(tasks, ui, storage);
        } catch (TunaException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Main function to run Duke.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Tuna tuna = new Tuna("./data", "./data/tuna.txt");
        tuna.run();
    }
}
