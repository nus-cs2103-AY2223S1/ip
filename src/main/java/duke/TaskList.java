package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task markTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("duke.task.Task number does not exist.");
        }
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("duke.task.Task number does not exist.");
        }
        Task task = this.tasks.get(index);
        task.unmark();
        return task;
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("duke.task.Task number does not exist.");
        }
        Task task = this.tasks.remove(index);
        return task;
    }

    public ToDo addToDo(String description) {
        ToDo todo = new ToDo(description);
        this.addToTasks(todo);
        return todo;
    }

    public Deadline addDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        this.addToTasks(deadline);
        return deadline;
    }

    public Event addEvent(String description, LocalDate at) {
        Event event = new Event(description, at);
        this.addToTasks(event);
        return event;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(String.format("%d. %s\n", 1 + i, this.tasks.get(i).toString()));
        }
        return result.toString();
    }

    private void addToTasks(Task task) {
        this.tasks.add(task);
    }
}
