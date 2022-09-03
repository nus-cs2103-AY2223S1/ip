package chacha.commands;

import java.util.ArrayList;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }   

    @Override
    public void execute(TaskList taskList, Ui ui) {
                Task task = taskList.get(taskIndex);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}