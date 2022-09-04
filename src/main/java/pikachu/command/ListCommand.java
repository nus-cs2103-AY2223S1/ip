package pikachu.command;

import pikachu.Storage;
import pikachu.Ui;
import pikachu.task.Task;
import pikachu.TaskList;

public class ListCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        output.append("PikaPika (Nothing in the list)\n");
        for (Task task: tasks.taskList) {
            output.append(tasks.taskList.indexOf(task)+1).append('.').append(task).append('\n');
        }
        output.deleteCharAt(output.length() - 1);
        System.out.println(String.valueOf(output));
    }

    public boolean isExit() {
        return false;
    }
    
}
