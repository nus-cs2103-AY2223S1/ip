package duke.command;

public class ListCommand extends Command {
    /**
     * A command to list all current tasks.
     */
    public ListCommand() {
        super((tasks, ui, storage) -> {
            if (tasks.size() == 0) {
                ui.showNothingToDoMessage();
            } else {
                ui.listAllTasks(tasks);
            }
        });
    }
}
