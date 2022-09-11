package Duke.Commands;

import Duke.Tasks.TaskList;

public class SortAllCommand extends UserCommand {

    public SortAllCommand(TaskList tasks){
        super(tasks);
    }

    @Override
    public String execute() {
        String output = "";
        output += tasks.sortAll();
        return output;
    }
}
