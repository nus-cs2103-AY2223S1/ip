package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.DeadlineTask;
import duke.task.Task;

import java.io.IOException;

public class AddDeadlineCommand extends Command {

    private final Task deadlineTask;

    public AddDeadlineCommand(String taskName, String date, String time) {
        deadlineTask = new DeadlineTask(taskName, date, time);
    }

    public AddDeadlineCommand(String taskName, String date) {
        deadlineTask = new DeadlineTask(taskName, date);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(deadlineTask);
            storage.writeToFile(taskList.list());
            ui.display(String.format("Added new deadline task:%n%s%n", deadlineTask));
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            taskList.add(deadlineTask);
            storage.writeToFile(taskList.list());
            return String.format("I've added a new deadline task:%n%s%n", deadlineTask);
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

}