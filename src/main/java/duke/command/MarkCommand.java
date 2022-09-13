package duke.command;

import duke.task.TaskList;

/**
 * Handles the marking or unmarking of tasks.
 */
public class MarkCommand extends Command {

    /**
     * Initialises a MarkCommand.
     *
     * @param commandArgs An array of Strings containing information
     *                    pertaining to this specific mark command.
     * @param tasks       An `ArrayList&lt;Task&gt;`, containing the
     *                    current existing tasks in the programme.
     */
    public MarkCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Marks or unmarks a task to denote its completion.
     *
     * @return Returns the message that MumBot should send to the GUI.
     */
    @Override
    public String performAction() {
        if (commandArgs[0].equals("mark")) {
            tasks.mark(Integer.parseInt(commandArgs[1]) - 1);
        } else if (this.commandArgs[0].equals("unmark")) {
            tasks.unmark(Integer.parseInt(commandArgs[1]) - 1);
        }
        return "Your " + commandArgs[0] + " command has been carried out! <3";
    }
}
