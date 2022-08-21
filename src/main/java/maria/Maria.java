package maria;

import maria.command.Command;
import maria.task.TaskList;
import maria.util.StorageConverter;

/**
 * Represents the entry point of the program.
 */
public class Maria {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Creates a new chatbot called Maria.
     */
    public Maria() {

        this.ui = new Ui();
        this.storage = new Storage("tasks.mariadata");
        this.taskList = StorageConverter.stringToTasks(this.storage, this.ui);

    }

    /**
     * Represents the main event loop of the program.
     */
    public void run() {

        this.ui.showInstructions();

        while (true) {

            String commandStr = this.ui.readCommand();
            Command command = Parser.parse(commandStr);
            command.execute(this.taskList, this.ui, this.storage);

        }

    }

    public static void main(String[] args) {

        Maria maria = new Maria();
        maria.run();

    }

}
