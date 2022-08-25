package DukeBot.command;

import DukeBot.DukeException;
import DukeBot.TaskList;

public class ExitCommand extends Command {

    private String command;

    private TaskList tasks;

    public ExitCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() throws DukeException {
        if (!command.equals("bye")) {
            throw new DukeException("Not sure what that means.");
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
