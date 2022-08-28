package chatbot.tasks;

import chatbot.exceptions.DukeException;

import java.time.LocalDate;
import java.util.*;

public class TaskList {
    private List<Task> todos = new ArrayList<>();
    private HashMap<LocalDate, TaskBucket> taskByDates = new HashMap<>();

    public int getNumberOfTasks() {
        return todos.size();
    }

    public Task addTodo(String taskName) {
        Task newTask = new Todo(taskName);
        todos.add(newTask);
        return newTask;
    }

    public Task addDeadline(String taskName, LocalDate date) {
        Task newTask = new Deadline(taskName, date);
        todos.add(newTask);
        bucketTasks(date, newTask);
        return newTask;
    }

    public Task addEvent(String taskName, LocalDate date) {
        Task newTask = new Event(taskName, date);
        todos.add(newTask);
        bucketTasks(date, newTask);
        return newTask;
    }

    public List<Task> listTasks() {
        return this.todos;
    }

    public List<Task> getTaskOn(LocalDate date) {
        if (taskByDates.containsKey(date)) {
            return taskByDates.get(date).getTasks();
        } else {
            return null;
        }
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            return todos.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    public Task markTask(int index) throws DukeException {
        try {
            Task target = todos.get(index - 1);
            target.mark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    public Task unmarkTask(int index) throws DukeException {
        try {
            Task target = todos.get(index - 1);
            target.unmark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    public List<Task> find(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : todos) {
            if (task.getTaskName().contains(keyword)) {
                matches.add(task);
            }
        }

        return matches;
    }

    private void bucketTasks(LocalDate date, Task task) {
        if (taskByDates.containsKey(date)) {
            taskByDates.get(date).addTask(task);
        } else {
            TaskBucket newBucket = new TaskBucket();
            newBucket.addTask(task);
            taskByDates.put(date, newBucket);
        }
    }
}
