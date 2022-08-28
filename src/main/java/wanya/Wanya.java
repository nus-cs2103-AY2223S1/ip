package wanya;

import wanya.parser.Parser;
import wanya.task.TaskList;

/**
 * Represents a Wanya bot that act as a task checker.
 */
public class Wanya {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a Wanya object and initialise Ui, Storage and TaskList.
     *
     * @param filePath the String of the filepath that store previous data.
     */
    public Wanya(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ui.showLoading();
        try {
            tasks = new TaskList(storage.load());
        } catch (WanyaException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Wanya bot to greet and read commands from user.
     */
    public void run() {
        ui.greet();
        while(ui.isActive()) {
            String command = ui.getUserCommand();
            Parser.parseCommand(command, tasks, ui);
        }
        storage.save(tasks);
    }

    /**
     * Initialises Wanya bot and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {
        Wanya wanya = new Wanya("tasks.txt");
        wanya.run();
    }
}


