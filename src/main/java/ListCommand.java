public class ListCommand extends Command {
    public void execute(TaskList tasks) {
        UI.printList(tasks);
    }
}
