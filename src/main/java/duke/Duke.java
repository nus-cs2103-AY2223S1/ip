package duke;

import duke.command.Command;

public class Duke {

    /**
     * Stores task information in file.
     */
    private Storage storage;
    /**
     * List to track current tasks.
     */
    private TaskList tasks;
    /**
     * User interface for duke bot.
     */
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("./data/dukeInfo.txt").run();

    }
}
