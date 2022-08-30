package duke.command;

import duke.task.TaskList;
public class unMarkCommand extends Command{
    private int taskNo;

    public unMarkCommand (int taskNo){
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.unMarkTask(taskNo);;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

