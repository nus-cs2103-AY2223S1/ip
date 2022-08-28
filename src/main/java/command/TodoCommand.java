package command;
import task.Task;
import tasklist.TaskList;
import task.Todo;
import utility.Storage;
import utility.Ui;

public class TodoCommand extends Command{
    
    public TodoCommand(String input) {
        super(input);
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        Task newTask = new Todo(super.getInput()); 
        String message = tasks.addTask(newTask);
        ui.add(tasks.numOfTasks(), message);
        storage.save(newTask);
    }

}
