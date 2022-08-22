public class ListCommand extends Command {
    @Override
    public void execute() throws DukeException {
        String[] tasks = Command.taskList.getAllTasksInDisplayFormat().toArray(new String[0]);
        if (tasks.length == 0) {
            Command.ui.printMessages(new String[]{"No tasks"});
        } else {
            Command.ui.printMessages(tasks);
        }
    }
}
