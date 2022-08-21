public class UnmarkCommand extends Command{
    int taskIndex;
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task curr = taskList.getTaskList().get(taskIndex);
        curr.setIsDone(false);
        ui.printMessage("\tI have marked it as undone:"
                        + "\n\tJe l'ai marqué comme défait:"
                        + "\n\t" + curr.toString());
    }
}
