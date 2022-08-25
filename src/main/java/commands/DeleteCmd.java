package commands;

public class DeleteCmd extends Command {
    private int taskIndex;

    public DeleteCmd(String body) throws NumberFormatException {
        taskIndex = Integer.parseInt(body);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {
        Task removedTask = tasks.deleteTask(taskIndex);
        if (removedTask != null) {
            ui.notifyUser("Alright, I have removed this task for you:\n\t\t" + removedTask);
            ui.notifyUser(String.format("You have %d task(s) in the list.", tasks.getListSize()));
        }

        saveUserTasks(storage, tasks);
    }
}
