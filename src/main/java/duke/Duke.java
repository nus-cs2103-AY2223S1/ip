package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.gui.Main;
import duke.note.NoteList;
import javafx.application.Application;

/**
 * Duke is a chatbot that helps you keep track of your tasks.
 * This is the main application class for Duke.
 */
public class Duke {
    private static final String TASKS_SAVE_FILE_PATH = "data/tasks.txt";
    private static final String NOTES_SAVE_FILE_PATH = "data/notes.txt";

    private Storage storage;
    private TaskList tasks;
    private NoteList notes;
    private Ui ui;

    /**
     * Creates a new Duke application.
     *
     * @param filePath path to the save file
     * @param notesFilePath path to the notes save file
     */
    public Duke(String tasksFilePath, String notesFilePath) {
        storage = new Storage(tasksFilePath, notesFilePath);
        tasks = new TaskList();
        notes = new NoteList();
        try {
            storage.load(tasks, notes);
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        ui = new Ui();
    }

    /**
     * Creates a new Duke application with the default save file path.
     */
    public Duke() {
        this(TASKS_SAVE_FILE_PATH, NOTES_SAVE_FILE_PATH);
    }

    /**
     * Runs the Duke application in CLI mode.
     */
    public void runCli() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.wrapPrint(c.execute(tasks, notes, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.wrapPrint(ui.showError(e));
            }
        }
    }

    /**
     * Executes a single command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, notes, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Executes the entry point for the Duke application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-cli")) {
            new Duke().runCli();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
