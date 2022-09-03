package chacha.commands;

import java.util.ArrayList;

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
    public void execute(ArrayList<Task> taskList, Ui ui) {
        Task task = taskList.get(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        taskList.remove(taskIndex); 
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }

}