public class ListCommand extends Command {
    @Override
    public void run(Duke duke) {
        duke.printTasks();
    }
}
