public class UnmarkCommand extends Command {
    private int num;

    public UnmarkCommand(int num) {
        super();
        this.num = num;
    }

    @Override
    public void execCommand(TaskList list, Save save) {
        list.getTask(this.num).markAsUndone();
        System.out.println("Task incomplete.\n" + list.getTask(this.num));
        save.saveList(list.save());
    }
}
