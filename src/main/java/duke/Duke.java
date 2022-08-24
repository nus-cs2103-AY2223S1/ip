package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a chatbot by the name of Duke.
 *
 * This chatbot acts as a task tracker for users to track the tasks they
 * wish to complete.
 *
 * Three types of tasks can be added.
 * Tasks without any associated date and time can be added as a Todo.
 * Tasks that need to be completed by a certain date and time can be added as a Deadline.
 * Tasks that have a starting date and time and an ending date or time can be added as an Event.
 *
 * Tasks can be added and deleted from the task list using their index. Each task can be
 * marked as done when the user has completed it, or marked as not done. The entire task
 * list can be sent to the user upon request. Users can even search for the tasks occurring
 * on a specified date. Duke will inform the user if it receives a command that it does not
 * understand, or if the input format is unable to be understood by Duke's parser.
 *
 * Duke will stop running upon receiving the "bye" command.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a new instance of Duke.
     *
     * Duke will load information saved from previous instances, if any,
     * upon START-up.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
            storage.load();
        } catch (IOException e) {
            ui.fileLoadError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        boolean isExit = false;
        ui.welcome();


        while (!isExit) {
            String input = ui.requestInput();
            Command command = Parser.parse(input, ui);
            if (command == null) {
                continue;
            }
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
