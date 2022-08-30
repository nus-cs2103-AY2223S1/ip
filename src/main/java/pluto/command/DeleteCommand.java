package pluto.command;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;
import pluto.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        Task t = tasks.deleteTask(idx);
        try {
            storage.rewriteFile(tasks);
            StringBuilder deleteMessage = new StringBuilder();
            deleteMessage.append("\tNoted. I've removed this task:\n");
            deleteMessage.append(String.format("\t\t%s\n", t.toString()));
            deleteMessage.append(String.format("\tNow you have %d tasks in the list.", tasks.nTasks()));
            ui.print(deleteMessage);
        } catch (IOException e) {
            tasks.addTask(idx - 1, t);
            throw new PlutoException("\tOOPS!!! Couldn't delete task. Try again!");
        }

    }
}
