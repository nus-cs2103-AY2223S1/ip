public class DeleteCommand extends Command{
    private int num;

    DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    void execute(Storage storage, UI ui, TaskList taskList) throws MarkException {
        if (num > taskList.size() || num < 0) {
            throw new MarkException();
        }
        System.out.println("Noted, I've removed this task:\n" + taskList.getTask(num - 1));
        taskList.delete(num - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        storage.save(taskList.list());
    }
}
