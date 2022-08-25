package duke;

import duke.task.Task;
import duke.task.TimedTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.",
                    index, tasks.size());
        }
    }

    public TaskList getTasksByDate(String date) throws DukeException {
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(TimedTask.format));
        } catch (DateTimeParseException e) {
            throw new DukeException("\u2639 OOPS!!! Wrong date format. Please input date in the format %s.",
                    TimedTask.format);
        }
        return new TaskList(
            tasks.stream()
                .filter(x -> x instanceof TimedTask && ((TimedTask) x).getTime().toLocalDate().equals(convertedDate))
                .collect(Collectors.toList())
        );
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task mark(int index) throws DukeException {
        this.get(index).markAsDone();
        return this.get(index);
    }

    public Task unmark(int index) throws DukeException {
        this.get(index).markAsNotDone();
        return this.get(index);
    }

    public Task delete(int index) throws DukeException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.",
                    index, tasks.size());
        }
    }

    public void sort() {
        Collections.sort(tasks);
    }
}
