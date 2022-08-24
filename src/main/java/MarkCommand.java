public class MarkCommand extends Command {
    private int num;

    MarkCommand(int num) {
        this.num = num;
    }

    @Override
    void execute(Storage storage, UI ui, TaskList taskList) throws MarkException {
        if (num > taskList.size() || num < 0) {
            throw new MarkException();
        }
        taskList.getTask(num - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + taskList.getTask(num - 1));
        storage.save(taskList.list());
    }
}
