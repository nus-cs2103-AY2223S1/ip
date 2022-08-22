package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {

    final private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor.
     */
    public Duke() {
        Storage storage = new Storage("./data/duke.txt");
        this.ui = new Ui();
        this.storage = storage;
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Echoes user's input.
     * @param message A message to echo.
     * @return The message user's provided.
     */
    public String echo(String message) {
        return "\t" + message;
    }

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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}