package duke;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String description;
    private LocalDate at;

    public EventCommand(String description, LocalDate at) {
        super();
        this.description = description;
        this.at = at;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.at);
        tasks.addTask(event);
        storage.save(tasks);
        String addTaskMessage = ui.showAddTask(event, tasks.getSize());
        if (tasks.checkDuplicates(event)) {
            return addTaskMessage + "\n" + ui.showDuplicateMessage();
        } else {
            return addTaskMessage;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
