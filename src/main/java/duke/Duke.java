package duke;

import duke.command.Command;

public class Duke {
    static String path2 = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(path2);
        tasks = new TaskList(storage.load());
    }

    /**
     * Initialises key objects and prompts user input.
     */
    public void run() {
        Command c = Parser.parseCommand(ui.getInput());
        c.execute(tasks, ui, storage);
    }

    /**
     * Feeds user input from JavaFX GUI to Duke.
     * @param input The user input.
     * @return The output from Duke as a string.
     */
    public String getResponse(String input) {
        ui.nextInput(input);
        run();
        return ui.getOutput();
    }

    public static void main(String[] args) {
    }
}
