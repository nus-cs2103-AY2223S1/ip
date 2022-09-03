package chacha.commands;


import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }   

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.get(taskIndex);
        taskList.remove(taskIndex); 
        int size = taskList.getSize();
        ui.printDelete(task, size);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}