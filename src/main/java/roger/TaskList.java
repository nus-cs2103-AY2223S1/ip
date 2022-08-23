package roger;

import roger.StorageParser;
import roger.tasks.Deadline;
import roger.tasks.Event;
import roger.tasks.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int taskNum) {
        return this.tasks.remove(taskNum - 1);
    }

    public Task get(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void markAsDone(int taskNum) {
        this.get(taskNum).markAsDone();
    }

    public void unmarkAsDone(int taskNum) {
        this.get(taskNum).unmarkAsDone();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public List<String> toTaskStrings() {
        return this.tasks.stream()
                .map(StorageParser::toTaskString)
                .collect(Collectors.toList());
    }

    public List<String> toDisplayStrings() {
        return this.tasks.stream()
                .map(Task::toString)
                .collect(Collectors.toList());
    }

    public List<Task> filter(LocalDate date) {
        List<Task> filtered = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); ++i) {
            Task task = this.tasks.get(i - 1);
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isOnDate(date)) {
                    filtered.add(event);
                }
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isOnDate(date)) {
                    filtered.add(deadline);
                }
            }
        }

        return filtered;
    }

    public int getTaskNum(Task task) {
        int taskIndex = this.tasks.indexOf(task);
        return taskIndex == -1 ? -1 : taskIndex + 1;
    }
}
