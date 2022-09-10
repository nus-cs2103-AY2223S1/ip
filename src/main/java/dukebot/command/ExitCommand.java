package dukebot.command;

import dukebot.DukeException;
import dukebot.TaskList;
import dukebot.Ui;

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
    public String execute() throws DukeException {
        if (!command.equals("bye")) {
            throw new DukeException("Not sure what that means.");
        }
        return Ui.showBye();
    }
}
