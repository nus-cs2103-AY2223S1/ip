package commands;

import duke.Ui;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

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
        Ui.printAddStatement(toAdd.toString(), taskList.getSize());
    }
}
