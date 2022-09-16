package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic of the Help Command when user input "--help".
 */
public class HelpCommand extends Command {
    /**
     * Executes the Help Command and list out all the available commands and
     * examples for the user.
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @return String that represents the response from ChatBot after executing the command
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "________________________________________\n"
                + "list -> list out all the tasks\n"
                + "todo -> creates a todo task\n"
                + "deadline -> creates a deadline task, add /by to specify deadline\n"
                + "event -> creates an event task, add /at to specify location/date\n"
                + "mark -> marks a task as done in the task list\n"
                + "unmark -> marks a task as not done in the task list\n"
                + "delete -> removes the task from the task list\n"
                + "find -> searches for task with the same keyword\n"
                + "undo -> returns to the state before execution of the previous command\n"
                + "bye -> end and close the ChatBot\n"
                + "________________________________________\n";
    };
}
