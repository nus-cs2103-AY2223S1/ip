package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles the creating of tasks.
 */
public class TaskCommand extends Command {

    /**
     * Initialises a TaskCommand.
     *
     * @param commandArgs An array of Strings containing information
     *                    pertaining to this specific mark command.
     * @param tasks       An `ArrayList&lt;Task&gt;`, containing the
     *                    current existing tasks in the programme.
     */
    public TaskCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Adds this task to the current list of tasks.
     *
     * @return Returns the message that MumBot should send to the GUI.
     */
    @Override
    public String performAction() {

        if (this.commandArgs[0].equals("todo")) {
            tasks.addTask(new Task(this.commandArgs[1]), true);

        } else if (this.commandArgs[0].equals("deadline")) {
            tasks.addTask(new Task(this.commandArgs[1], this.commandArgs[2], Task.Type.DEADLINE), true);

        } else if (this.commandArgs[0].equals("event")) {
            tasks.addTask(new Task(this.commandArgs[1], this.commandArgs[2], Task.Type.EVENT), true);
        }
        return "Your " + commandArgs[0] + " command has been carried out! <3";
    }
}
