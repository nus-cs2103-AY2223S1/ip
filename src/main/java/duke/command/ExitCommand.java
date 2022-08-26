package duke.command;

import duke.TaskList;

public class ExitCommand extends Command {

    /**
     * Executes the command by exiting the program.
     *
     * @param tasks The user's current list of tasks.
     */
    public void execute(TaskList tasks) {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
