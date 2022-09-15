public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.toString() == null) {
            System.out.println("Nice! You have no tasks as of now.");
        } else {
            ui.print(taskList.toString());
        }
    }
}
