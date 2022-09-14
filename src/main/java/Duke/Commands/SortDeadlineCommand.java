package Duke.Commands;

import Duke.Tasks.TaskList;

public class SortDeadlineCommand extends UserCommand {

    public SortDeadlineCommand(TaskList tasks){
        super(tasks);
    }

    @Override
    public String execute() {
        String output = "These are all deadlines after sorting based on emergency:\n";
        output += tasks.sortDeadline();
        return output;
    }
}
