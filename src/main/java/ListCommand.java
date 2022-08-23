public class ListCommand extends Command {

    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws Duke.DukeException {
        int numOfTasks = taskList.getSize();
        if (numOfTasks == 0) {
            throw new Duke.DukeException("Unfortunately, you do not have any tasks at hand." +
                    " Try creating some first.");
        }
        ui.line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= numOfTasks; i++) {
            Task t = taskList.getTask(i);
            System.out.println(i + 1 + "." + t);
        }
        ui.line();
    }
}
