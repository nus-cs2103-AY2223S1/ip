package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class represents the Duke chatbot
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new Duke chatbot
     */
    public Duke() {
        Storage storage = new Storage("./data/duke.txt");
        this.ui = new Ui();
        this.storage = storage;
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = this.ui.input();
                Command command = Parser.parse(input);
                command.execute(this.ui, this.storage, this.tasks);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.printException(e);
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(ui, storage, tasks);
            return response;
        } catch (DukeException e) {
            return ui.print(e.getMessage());
        }
    }

    /**
     * Starts the program
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
