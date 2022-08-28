package unc;

import unc.command.Command;

/**
 * Main class where program is run.
 */
public class Unc {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor
     *
     * @param filePath Directory to save data.
     */
    public Unc(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Continuously reads in user input, translates them into commands, and execute them.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (UncException e) {
                //ui.showError(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
        storage.save(taskList);
    }


    /**
     * Starts the program running.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Unc("data/tasks.txt").run();
    }
}
