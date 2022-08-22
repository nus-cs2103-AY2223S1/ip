package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.Parser;
import duke.exception.DukeException;
import duke.exception.TaskIndexOutOfBoundsException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public enum TaskType {
        DEADLINE, EVENT, TODO
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> taskStrings) {
        this();
        for (String taskStr : taskStrings) {
            this.tasks.add(Parser.fromStorage(taskStr));
        }
    }

    public List<String> toStorage() {
        return this.tasks.stream().map(Task::toStorage).collect(Collectors.toList());
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task deleteTask(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.tasks.size()) {
            throw new TaskIndexOutOfBoundsException(taskIndex);
        }
        return this.tasks.remove(taskIndex - 1);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }
    }

    public void markTaskAsDone(int taskIndex) {
        this.getTask(taskIndex).markAsDone();
    }

    public void markTaskAsNotDone(int taskIndex) {
        this.getTask(taskIndex).markAsNotDone();
    }

    public Task getTask(int i) {
        Task t;
        try {
            t = this.tasks.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(i);
        }
        return t;
    }

    public int size() {
        return this.tasks.size();
    }
}
