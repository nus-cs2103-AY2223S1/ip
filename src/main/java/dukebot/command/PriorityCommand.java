package dukebot.command;

import dukebot.*;

public class PriorityCommand extends Command {

    private String command;

    private TaskList tasks;

    public PriorityCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        String[] s = command.split(" ");
        if (s.length != 3) {
            throw new DukeException("set priority in this format: priority <index> <high/medium/low>");
        }
        int indexOfTask;
        try {
            indexOfTask = Integer.parseInt(s[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("set priority in this format: priority <index> <high/medium/low>");
        }
        if (indexOfTask > tasks.size()) {
            throw new DukeException("task does not exist");
        }
        String priority = s[2];
        Task task = tasks.get(indexOfTask - 1);
        switch (priority) {
        case "low":
            task.setPriority(Priority.LOW);
            break;
        case "medium":
            task.setPriority(Priority.MEDIUM);
            break;
        case "high":
            task.setPriority(Priority.HIGH);
            break;
        default:
            throw new DukeException("Only low/medium/high priority allowed");
        }
        return Ui.showPriorityChange(task);
    }
}
