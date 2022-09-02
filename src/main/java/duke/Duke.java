package duke;

import duke.command.Command;

/**
 * A chatbot that keeps track of a list of tasks the user inputs.
 * Three types of tasks are supported: to-dos, deadlines, and events.
 * Tasks are saved in the hard disk automatically.
 *
 * @author Sun Ruoxin
 */
public class Duke {
    /** A storage object which encapsulates reading from and writing into file. */
    private Storage storage;
    /** A task list object which encapsulates the list of tasks. */
    private TaskList tasks;
    /** A UI object which encapsulates interactions with the user. */
    private Ui ui;

    /**
     * Class constructor.
     */
    public Duke() {}

    public void initialise() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
//        c.execute(tasks, ui, storage);
        return c.execute(tasks, ui, storage);
    }
}
