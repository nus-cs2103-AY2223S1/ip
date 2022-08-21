package duke.task;

import duke.exception.DukeOutOfBoundException;
import duke.Ui;

import java.util.List;

public class TaskList {

    private List<Task> taskList;
    private Ui ui;

    public TaskList(List<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void add(Task task) {
        taskList.add(task);
        ui.addMessage(this.taskList.size(), task);
    }

    public void delete(int index) throws DukeOutOfBoundException {
        if (index >= taskList.size()) {
            throw new DukeOutOfBoundException();
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.deleteMessage(this.taskList.size(), task);
    }

    public void mark(int index) throws DukeOutOfBoundException {
        if (index >= taskList.size()) {
            throw new DukeOutOfBoundException();
        }
        Task task = taskList.get(index);
        task.markAsDone();
        ui.markMessage(task);
    }

    public void unmark(int index) throws DukeOutOfBoundException {
        if (index >= taskList.size()) {
            throw new DukeOutOfBoundException();
        }
        Task task = taskList.get(index);
        task.markAsUndone();
        ui.unmarkMessage(task);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}
