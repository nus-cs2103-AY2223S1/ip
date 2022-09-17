package duke;

import duke.command.Command;

/**
 * A UI which deals with user interactions.
 */
public class Ui {

    private Parser parser;

    /**
     * Constructor method for a Ui.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Interprets the input of the user.
     *
     * @param tasks list of tasks of the user
     */
    public String giveInput(TaskList tasks, String input) throws DukeException {
        try {
            Command command = this.parser.parseUserInput(input, tasks);
            assert command != null;
            return command.executeWithMessage(tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
