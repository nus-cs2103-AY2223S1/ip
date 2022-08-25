package DukeBot.command;

import DukeBot.DukeException;
import DukeBot.Task;
import DukeBot.TaskList;

public class ListCommand extends Command {

    private TaskList tasks;

    private String command;

    public ListCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public void execute() throws DukeException {
        if (!command.equals("list")) {
            throw new DukeException("Sorry, I'm not sure what that means");
        }
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println(String.format("      %d. %s", i + 1, tasks.get(i)));
        }
    }
}
