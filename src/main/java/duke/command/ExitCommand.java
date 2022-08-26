package duke.command;

import duke.TaskList;

public class ExitCommand extends Command {
    public void execute(TaskList tasks) {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
