package Duke.Commands;

import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

public class SaveCommand extends UserCommand{
    public SaveCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String execute(){
        return "We will save your data in the end :)";
    }
}
