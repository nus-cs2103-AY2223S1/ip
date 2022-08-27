package uwu.command;

import uwu.exception.NullTaskException;
import uwu.exception.UwuException;

import uwu.Storage;

import uwu.task.Task;
import uwu.task.TaskList;
import uwu.Ui;

public class DeleteCommand extends Command {
    int index;
    String taskType;
    String userCommand;

    public DeleteCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.taskType = taskData[0].trim();
        this.index = Integer.parseInt(taskData[1].trim()) - 1;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage) throws UwuException {
        if (index >= tasks.size()) {
            throw new NullTaskException("\thm...it seems that task " + String.valueOf(index + 1) + " does not exist ><" +
                                        "\n\tplease check that you have keyed in the right task number~ <:");
        }
        Task task = tasks.remove(index);
        storage.save(tasks.taskListToStorageString());
        ui.deleteTask(task, tasks.size());
    };

    public boolean isExit(){
        return false;
    };
}
