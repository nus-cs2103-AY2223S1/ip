package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;
import bro.task.Deadline;
import bro.task.Event;
import bro.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        String type = this.task.getTaskType();
        switch (type) {
            case ("bro.task.Todo"):
                tasklist.todoTask(this.task, storage);
                break;
            case ("bro.task.Deadline"):
                tasklist.deadlineTask(this.task, storage);
                break;
            case ("bro.task.Event"):
                tasklist.eventTask(this.task, storage);
                break;
        }
    }
}