public class CommandListAllTasks extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the list of tasks you have:\n");

        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }

        ui.showText(sb.toString());

    }
}
