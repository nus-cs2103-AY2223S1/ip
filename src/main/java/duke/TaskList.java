package duke;

import duke.exception.TaskNotFoundException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList implements Savable<TaskList> {
    private ArrayList<Task> tasks = new ArrayList<>();

    public boolean add(Task task) {
        return tasks.add(task);
    }

    protected ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public Task get(int i) throws TaskNotFoundException {
        throwIfNotValidIndex(i);
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task remove(int i) throws TaskNotFoundException {
        throwIfNotValidIndex(i);
        return this.tasks.remove(i);
    }

    private boolean isValidIndex(int i) {
        return i >= 0 && i < this.size();
    }

    private void throwIfNotValidIndex(int i) throws TaskNotFoundException {
        if (!isValidIndex(i)) {
            throw new TaskNotFoundException(i + 1);
        }
    }


    @Override
    public String toString() {
        String message = Stream.iterate(0, x -> x + 1)
                .limit(tasks.size())
                .map(x -> x + 1 + ". " + tasks.get(x).toString())
                .reduce("", (x, y) -> x + y + "\n");
        return message;
    }

    @Override
    public String toFormattedString() {
        String s;
        s = Stream.iterate(0, x -> x + 1)
                .limit(tasks.size())
                .map(x -> this.tasks.get(x).toFormattedString())
                .reduce("", (x, y) -> x + y + "\n");
        return s;
    }

    @Override
    public TaskList parse(String formattedString) {
        return Parser.parseTaskList(formattedString);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TaskList) {
            TaskList tl = (TaskList) obj;
            if (this.tasks == tl.tasks) {
                return true;
            }
            if (this.tasks == null || tl.getTaskList() == null) {
                return false;
            }
            return this.tasks.equals(tl.getTaskList());
        }
        return false;
    }
}
