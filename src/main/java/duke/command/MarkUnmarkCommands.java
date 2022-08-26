package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidTaskNumberException;

/**
 * A class for the mark and unmark commands.
 */
public class MarkUnmarkCommands extends Command {

    /** The type of command. */
    private final String type;

    /** Index of the task to be deleted in string form. */
    private final String index;

    /**
     * Constructor for the MarkUnmarkCommands class.
     *
     * @param type  The type of command.
     * @param index Index of the task to be deleted in string form.
     */
    public MarkUnmarkCommands(String type, String index) {
        this.type = type;
        this.index = index;
    }

    /**
     * Executes the mark"/"unmark commands.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws InvalidTaskNumberException If the followup text after the command is not a valid integer.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException {
        if (index.equals("") || !isInteger(index) || (Integer.parseInt(index) - 1) < 0 ||
                (Integer.parseInt(index) - 1) >= taskList.size()) {
            throw new InvalidTaskNumberException(type, index);
        }
        boolean change;
        int i = Integer.parseInt(this.index) - 1;
        switch (type) {
        case "mark":
            change = taskList.setDone(i);
            if (change) {
                System.out.println("  Nice! Task " + (i + 1) + " done!\n\t" + taskList.get(i));
            } else {
                System.out.println("  Task " + (i + 1) + " is already done!\n\t" + taskList.get(i));
            }
            break;
        case "unmark":
            change = taskList.setUnDone(i);
            if (change) {
                System.out.println("  Ok! Task " + (i + 1) + " marked as not done!\n\t" + taskList.get(i));
            } else {
                System.out.println("  Task " + (i + 1) + " is already not done!\n\t" + taskList.get(i));
            }
            break;
        }
        ui.printListCount();
    }

    /**
     * Checks if the text after mark/unmark is an integer.
     *
     * @param input The text to be checked.
     * @return The boolean representing if the text is an integer.
     */
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
