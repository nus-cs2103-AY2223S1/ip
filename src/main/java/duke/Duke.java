package duke;

/**
 * Main java class to create a new Duke object
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Consturctor to create new Duke object
     * @param filePath the specified filepath to store Tasks after the program exits
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(storage, ui, tasks);
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String command = ui.getCommand();
            try {
                if (command.equals("bye")) {
                    break;
                } else {
                    parser.parse(command);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();

    }

}
