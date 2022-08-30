package Commands;

import DataStruct.TaskList;

public class EndCommand extends Command{

    private TaskList tasks;
    private String args;

    public EndCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.args = keyword;
    }

    @Override
    public String execute() {
            return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean getIsRunning() {
        return false;
    }
}
