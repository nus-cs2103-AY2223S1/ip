package DukeBot.command;

import DukeBot.DukeException;
import DukeBot.TaskList;
import DukeBot.Ui;

public class DeleteCommand extends Command {

    private TaskList tasks;

    private String command;

    public DeleteCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        String[] s = command.split(" ");
        if (s.length != 2) {
            throw new DukeException("delete command in this format: delete <index>");
        }
        int indexOfTask;
        try {
            indexOfTask = Integer.parseInt(s[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("delete command in this format: delete <index>");
        }
        return Ui.showDelete(tasks.deleteTask(indexOfTask - 1));
    }
}
