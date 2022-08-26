package commands;

import tasks.*;
import duke.Ui;

public class TodoCommand extends Command {
    String descript;
    boolean isDone;

    public TodoCommand(String descript) {
        this.descript = descript;
        this.isDone = false;
    }

    @Override
    public void run(TaskList taskList) {
        Task toAdd = new Todos(this.descript);
        taskList.addTask(toAdd);
        Ui.addStatement(toAdd.toString(), taskList.getSize());
    }
}
