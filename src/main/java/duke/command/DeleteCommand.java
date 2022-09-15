package duke.command;

import java.util.ArrayList;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.util.TaskList;

/**
 * Represents a command to delete a task from the tasklist; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class DeleteCommand extends Command {
    private static final String DELETE_TASK = "Boo~ Don't be a quitter! I've removed %s for you. =3=\n";
    private static final String DELETE_ALL_TASKS = "Boo~ I've removed all tasks for you.\n"
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
        // For future features, run a for loop through words to check if
        // all words are numbers and delete all numbers. If not, throw an exception.
        StringBuilder output = new StringBuilder();
        if (tasklist.tasks.size() == 0) {
            throw new DukeException(String.format(Messages.EMPTY_TASK_ERROR, "delete"));
        }
        if (words.get(0).equals("all") && words.size() == 1) {
            output.append(DELETE_ALL_TASKS);
            tasklist.tasks.clear();
        } else {
            output.append(String.format(DELETE_TASK, (words.size() == 1 ? "this task" : "these tasks")));
            tasklist.deleteTask(parseToInt(words, tasklist, output));
        }
        return output.toString();
    }

    private ArrayList<Task> parseToInt(ArrayList<String> str,
                                       TaskList tasklist, StringBuilder output) throws DukeException {
        ArrayList<Task> subTaskList = new ArrayList<>();
        for (String s : str) {
            try {
                int taskNum = Integer.parseInt(s);
                output.append(taskNum)
                        .append(". ")
                        .append(tasklist.tasks.get(taskNum - 1))
                        .append("\n");
                subTaskList.add(tasklist.tasks.get(taskNum - 1));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(String.format(Messages.INVALID_TASK_NUMBER, "delete"));
            } catch (NumberFormatException e) {
                throw new DukeException("Please use 'delete all' or 'delete <task numbers>'. T^T");
            }
        }
        return subTaskList;
    }
}
