package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Represents a command to delete a task from the tasklist; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class DeleteCommand extends Command {
    private static final String REMOVED_TASK = "Boo~ Don't be a quitter! I've removed this task for you. =3=\n";
    private static final String REMOVE_ALL_TASKS = "Boo~ I've removed all tasks for you.\n"
            + "Start setting new goals for yourself! =3=";
    private final ArrayList<String> words;

    /**
     * Constructor for DeleteCommand.
     *
     * @param words the remaining user input after the first keyword
     */
    public DeleteCommand(ArrayList<String> words) {
        this.words = words;
    }

    /**
     * Executes the command for "delete" keyword.
     * This is the main way for outputting bot replies.
     *
     * @param storage        the storage object
     * @param tasklist       the task list object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    @Override
    public String execute(Storage storage, TaskList tasklist) throws DukeException {
        String input = String.join(" ", words);
        StringBuilder output = new StringBuilder();
        if (tasklist.tasks.size() == 0) {
            throw new DukeException("There's nothing in your list to delete! T^T");
        }
        if (input.equals("all")) {
            tasklist.tasks.clear();
            output.append(REMOVE_ALL_TASKS);
        } else {
            try {
                int taskNum = Integer.parseInt(input);
                deleteTaskNumber(output, tasklist, taskNum);
            } catch (NumberFormatException e) {
                throw new DukeException("Please either use 'delete all' or 'delete <task number>'. T^T");
            }
        }
        return output.toString();
    }

    private void deleteTaskNumber(StringBuilder output, TaskList tasklist, int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
            String deletedTask = tasklist.tasks.get(taskNum - 1).toString();
            tasklist.deleteTask(taskNum);
            output.append(REMOVED_TASK)
                    .append(deletedTask).append("\n")
                    .append("You have ").append(tasklist.tasks.size())
                    .append((tasklist.tasks.size() == 1 ? " task! :D" : " tasks! :D"));
        } else {
            throw new DukeException("Please enter a valid task number to delete. T^T");
        }
    }
}
