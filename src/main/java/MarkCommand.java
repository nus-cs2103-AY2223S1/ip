public class MarkCommand extends Command {
    private int num;

    public MarkCommand(int num) {
        super();
        this.num = num;
    }

    @Override
    public void execCommand(TaskList list) {
        list.getTask(this.num).markAsDone();
        System.out.println("Task successfully completed!\n" + list.getTask(this.num));
    }
}
