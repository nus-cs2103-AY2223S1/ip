public class ListCommand extends Command{
    public ListCommand() {
        super();
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 0;
        while (i < tasks.getsize()) {
            Task task = tasks.get(i);
            System.out.println(i + ". " + task.toString());
        }
    }
}
