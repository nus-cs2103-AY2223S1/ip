public class DeleteCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Noted. I've removed this task:");
        String userInput = ui.userString();
        int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
        System.out.printf("\t%s\n", taskList.get(index));
        taskList.remove(index);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
        storage.save(taskList);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
