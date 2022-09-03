package chacha.commands;

import java.util.ArrayList;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }   

    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        taskList.add(task);  
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString()); 
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

}