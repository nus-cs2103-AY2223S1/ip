package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }
}
