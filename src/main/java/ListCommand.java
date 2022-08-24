public class ListCommand extends Command{

    @Override
    void execute(Storage storage, UI ui, TaskList taskList) {
        taskList.read();
    }
}
