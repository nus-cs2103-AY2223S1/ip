public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < taskList.size(); ++i) {
            System.out.printf(" %d. %s\n", i + 1, taskList.get(i));
        }

        storage.save(taskList);

    }


    @Override
    public boolean isEnd() {
        return false;
    }
}
