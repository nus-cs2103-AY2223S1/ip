package commands;

import duke.Ui;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public class DeadlineCommand extends Command {
    String descript;
    String doBy;

    public DeadlineCommand(String descript, String doBy) {
        this.descript = descript;
        this.doBy = doBy;
    }

    @Override
    public void run(TaskList taskList) {
        Task toAdd = new Deadlines(this.descript, this.doBy);
        taskList.addTask(toAdd);
        Ui.printAddStatement(toAdd.toString(), taskList.getSize());
    }
}
