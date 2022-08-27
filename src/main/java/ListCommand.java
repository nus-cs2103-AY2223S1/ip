public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        System.out.printf("Here are the tasks in your list:\n");
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.printf("  %d.%s\n", i + 1, taskList.getTask(i).toString());
        }
    }
}
