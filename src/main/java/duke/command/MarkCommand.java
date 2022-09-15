package duke.command;

import java.util.ArrayList;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Represents a command to mark or unmark a task; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class MarkCommand extends Command {
    private static final String MARK_TASK = "Great Job on completing %s! ^.^ :\n";
    private static final String UNMARK_TASK = "Grrr, remember to finish %s! =3=:\n";
    private final ArrayList<String> words;
    private final String firstWord;

    /**
     * Constructor for MarkCommand.
     *
     * @param words the remaining user input after the first keyword
     * @param firstWord the first word in the user input
     */
    public MarkCommand(ArrayList<String> words, String firstWord) {
        this.words = words;
        this.firstWord = firstWord;
    }

    /**
     * Executes command for "mark" and "unmark" keywords.
     * This is the main way for outputting bot replies
     *
     * @param storage        the storage object
     * @param tasklist       the task list object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    @Override
    public String execute(Storage storage, TaskList tasklist) throws DukeException {
        StringBuilder output = new StringBuilder();
        switch (firstWord) {
        case "mark":
            markTaskNumber(tasklist, output);
            break;
        case "unmark":
            unmarkTaskNumber(tasklist, output);
            break;
        default:
            // Defensive coding for default statement.
            output.append(Messages.UNKNOWN_COMMAND);
        }
        return output.toString();
    }

    private void markTaskNumber(TaskList tasklist, StringBuilder output) throws DukeException {
        if (tasklist.tasks.size() == 0) {
            throw new DukeException(String.format(Messages.EMPTY_TASK_ERROR, "mark"));
        }
        if (words.get(0).equals("all") && words.size() == 1) {
            output.append(String.format(MARK_TASK, "all your tasks"));
            for (int i = 0; i < tasklist.tasks.size(); ++i) {
                tasklist.tasks.get(i).markDone();
                output.append(i + 1)
                        .append(". ")
                        .append(tasklist.tasks.get(i))
                        .append("\n");
            }
        } else {
            output.append(String.format(MARK_TASK, (words.size() == 1 ? "this task" : "all these tasks")));
            for (String str : words) {
                try {
                    int taskNum = Integer.parseInt(str);
                    tasklist.tasks.get(taskNum - 1).markDone();
                    output.append(taskNum)
                            .append(". ")
                            .append(tasklist.tasks.get(taskNum - 1))
                            .append("\n");
                } catch (NumberFormatException e) {
                    throw new DukeException("Please use 'mark all' or 'mark <task numbers>'. T^T");
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(String.format(Messages.INVALID_TASK_NUMBER, "mark"));
                }
            }
        }
    }

    private void unmarkTaskNumber(TaskList tasklist, StringBuilder output) throws DukeException {
        if (tasklist.tasks.size() == 0) {
            throw new DukeException(String.format(Messages.EMPTY_TASK_ERROR, "unmark"));
        }
        if (words.get(0).equals("all") && words.size() == 1) {
            output.append(String.format(UNMARK_TASK, "all your tasks"));
            for (int i = 0; i < tasklist.tasks.size(); ++i) {
                tasklist.tasks.get(i).markUndone();
                output.append(i + 1)
                        .append(". ")
                        .append(tasklist.tasks.get(i))
                        .append("\n");
            }
        } else {
            output.append(String.format(UNMARK_TASK, (words.size() == 1 ? "this task" : "all these tasks")));
            for (String str : words) {
                try {
                    int taskNum = Integer.parseInt(str);
                    tasklist.tasks.get(taskNum - 1).markUndone();
                    output.append(taskNum)
                            .append(". ")
                            .append(tasklist.tasks.get(taskNum - 1))
                            .append("\n");
                } catch (NumberFormatException e) {
                    throw new DukeException("Please use 'unmark all' or 'unmark <task numbers>'. T^T");
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(String.format(Messages.INVALID_TASK_NUMBER, "unmark"));
                }
            }
        }
    }
}
