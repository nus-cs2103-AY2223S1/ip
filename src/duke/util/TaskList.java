package duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.OutOfBoundException;
import duke.task.Task;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addEntry(Task task) {
        tasks.add(task);
    }

    public Task deleteEntry(int index) throws OutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }
        return tasks.remove(index);
    }

    public Task get(int index) throws OutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }
        return tasks.get(index);
    }

    public List<Task> filterBefore(LocalDateTime dt) {
        List<Task> cpy = new ArrayList<>(tasks);
        cpy.sort(null);
        List<Task> ret = new ArrayList<>();
        for (Task t : cpy) {
            if (t.compareTo(dt) > 0) {
                break;
            }
            ret.add(t);
        }

        return ret;
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    };

    public ParsedData[] getParsedData() {
        ParsedData[] ret = new ParsedData[tasks.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = tasks.get(i).convertToParseData();
        }
        return ret;
    }

}
