package duke.command;

import duke.task.TaskList;
import duke.task.Task.Priority;

/**
 * Handles the assigning of a task priority.
 */
public class PriorityCommand extends Command {

    /**
     * Initialises a PriorityCommand.
     *
     * @param commandArgs An array of Strings containing information
     *                    pertaining to this specific mark command.
     * @param tasks       An `ArrayList&lt;Task&gt;`, containing the
     *                    current existing tasks in the programme.
     */
    public PriorityCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Assigns a given priority level to a task.
     *
     * @return Returns the message that MumBot should send to the GUI.
     */
    @Override
    public String performAction() {

        switch (commandArgs[2]) {
        case "high":
            tasks.setPriority(Integer.parseInt(commandArgs[1]) - 1, Priority.HIGH);
            break;
        case "med":
            tasks.setPriority(Integer.parseInt(commandArgs[1]) - 1, Priority.MED);
            break;
        case "low":
            tasks.setPriority(Integer.parseInt(commandArgs[1]) - 1, Priority.LOW);
            break;
        default:
            tasks.setPriority(Integer.parseInt(commandArgs[1]) - 1, Priority.NONE);
        }

        return "Your " + commandArgs[0] + " command has been carried out! <33";
    }
}
