package chacha.commands;

import java.util.ArrayList;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }   

    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
                Task task = taskList.get(taskNumber);
                task.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
    }

}