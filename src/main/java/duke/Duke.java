package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor that creates an instance of duke.Duke linked to the specified file path.
     *
     * @param filePath The file path name on a computer.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    /**
     * Method runs duke.Duke list making program.
     */
    public void run() {
        this.ui.greet();
        this.storage.readData(this.tasks);
        while (true) {
            try {
                String fullCommand = this.ui.readInput();
                this.ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
            } catch (DukeException e) {
                System.out.println("That command was not valid. Try again!");
            } finally {
                this.ui.printLine();
            }
        }
    }

}
