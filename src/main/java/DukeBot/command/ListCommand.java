package DukeBot.command;

import DukeBot.DukeException;
import DukeBot.TaskList;
import DukeBot.Ui;

public class ListCommand extends Command {

    private TaskList tasks;

    private String command;

    public ListCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        if (!command.equals("list")) {
            throw new DukeException("Sorry, I'm not sure what that means");
        }
        return Ui.showList(tasks, false);
    }
}
