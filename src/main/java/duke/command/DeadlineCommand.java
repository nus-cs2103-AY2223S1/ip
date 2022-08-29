package command;

import command.Command;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.addDeadline(description, by);
        storage.save(tasks.saveTasks());
        return ui.displayAdd(task, tasks.getSize());
    }
}
