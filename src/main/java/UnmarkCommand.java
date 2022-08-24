public class UnmarkCommand extends Command {
    private int num;

    UnmarkCommand(int num) {
        this.num = num;
    }
    @Override
    void execute(Storage storage, UI ui, TaskList taskList) throws MarkException {
        if (num > taskList.size() || num < 0) {
            throw new MarkException();
        }
        taskList.getTask(num - 1).setDone(false);
        System.out.println("Ok, I've marked this task as not done yet:\n" + taskList.getTask(num - 1));
        storage.save(taskList.list());
    }
}