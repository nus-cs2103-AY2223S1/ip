package duke.command;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Note;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Executes the command to find a task or note.
 *
 * @author Lim Ai Lin
 */
public class FindCommand extends Command {

    private final String[] FIND_MATCH;

    public FindCommand(String[] str) {
        this.FIND_MATCH = str;
    }

    /**
     * Executes the find command the user inputs.
     *
     * @param tasks The list containing all the tasks and notes.
     * @param ui The ui to deal with user interactions.
     * @param storage The storage containing all tasks and notes the user has previously input.
     * @throws DukeException
     *          Thrown when description is not given.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String match;
        try {
            match = FIND_MATCH[1];
        } catch (Exception e) {
            throw new DukeEmptyDescriptionException();
        }
        ArrayList<Task> matchT = tasks.findTask(match);
        ArrayList<Note> matchN = tasks.findNote(match);

        return ui.matchTask(matchT) + "\n" + ui.matchNote(matchN);
    }
}
