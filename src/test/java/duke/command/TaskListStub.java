package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

/**
 * Represents a stub for TaskList class used in testing.
 */
public class TaskListStub extends TaskList {
    private ArrayList<Task> data;
    
    public TaskListStub() throws DukeException {
        super(new Storage("././././data/duke.txt"), new UI());
        this.data = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addTask(Task task) {
        data.add(task);
        return " ";
    }

    /**
     * Checks that a task in the list has the same string representation as a specified string.
     *
     * @param pos The position of the task in the list.
     * @param string The string to be compared against.
     * @return
     */
    public boolean checkTask(int pos, String string) {
        return data.get(pos).toString().equals(string);
    }
}
