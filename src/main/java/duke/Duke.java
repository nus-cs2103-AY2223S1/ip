package duke;

/**
 * Duke is a task list manager for tasks, deadlines and events!
 * 
 * @author Kiyan Ang Ping Young (@kynapy)
 * @version v0.1
 * @since 2022-09-10
 */


public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    
    /**
     * Constructor for Duke.
     * @param filepath This is the filepath where the data file duke.txt would be stored.
     */

    public Duke(String filepath) throws Exception {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } finally {
            System.out.println();
        }
        //} catch (DukeException e) {
        //    tasks = new TaskList();
        //}
    }

    /**
     * This method sets up the classes that Duke is reliant on.
     * @throws Exception
     */
    public void run() throws Exception {
        ui.greet();
        Parser parser = new Parser(tasks);
        boolean terminate = false;
        while (!terminate) {
            String input = ui.getInput();
            ui.printFrontSpacing();
            terminate = parser.parse(input);
            storage.store(tasks.getList());
            if (terminate == true) {
                ui.sayBye();
            }
            ui.printBackSpacing();
        }
    }

    /**
     * Main method to run Duke.
     * @param args No arguments required in the CLI
     */
    public static void main(String[] args) throws Exception {
        new Duke("./data/duke.txt").run();
    }
}