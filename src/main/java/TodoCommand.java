public class TodoCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String userInput = ui.userString();
        String[] splitInput = userInput.split(" ");

        System.out.println("Got it. I've added this task");
        StringBuilder todo = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            todo.append(' ');
            todo.append(splitInput[i]);
        }

        ToDo todoTask = new ToDo(todo.toString());
        taskList.add(todoTask);
        System.out.printf("\t %s\n", todoTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
        storage.save(taskList);

    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
