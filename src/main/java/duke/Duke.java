package duke;

import duke.command.Command;

/**
 * A chatbot that keeps track of a list of tasks the user inputs.
 * Three types of tasks are supported: to-dos, deadlines, and events.
 * Tasks are saved in the hard disk automatically.
 *
 * @author Sun Ruoxin
 */
public class Duke {
    /** A storage object which encapsulates reading from and writing into file. */
    private Storage storage;
    /** A task list object which encapsulates the list of tasks. */
    private TaskList tasks;
    /** A UI object which encapsulates interactions with the user. */
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param filePath The path of the file that stores the tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Encapsulates the main logic of the chatbot.
     */
    public void run() {
        ui.greet();
        storage.load(ui);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts up the chatbot and runs its main logic.
     * @param args the command line arguments
     * @throws DukeException when error encountered
     */
    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

}
