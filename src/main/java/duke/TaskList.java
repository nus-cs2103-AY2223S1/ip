package duke;

import duke.task.*;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(ArrayList<String> list) {
        this.tasks = new ArrayList<>();
        for (String data : list) {
            tasks.add(Task.loadTask(data));
        }
        size = tasks.size();
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<String> saveTasks() {
        ArrayList<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(task.saveTask());
        }
        return data;
    }

    public String markTask(int idx) {
        return this.tasks.get(idx - 1).mark();
    }

    public String unmarkTask(int idx) {
        return this.tasks.get(idx - 1).unmark();
    }

    public String addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        size++;
        return todo.toString();
    }

    public String addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        size++;
        return deadline.toString();
    }

    public String addEvent(String description, String at) {
        Event event = new Event(description, at);
        tasks.add(event);
        size++;
        return event.toString();
    }

    public String deleteTask(int idx) {
        Task deleted = tasks.remove(idx - 1);
        size--;
        return deleted.toString();
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "There are no tasks in your list.";
        } else {
            StringBuilder sb = new StringBuilder(String.format("Here %s the task%s in your list:",
                    size > 1 ? "are" : "is", size > 1 ? "s" : ""));
            for (int i = 1; i <= size; i++) {
                sb.append(String.format("%n%d.%s", i, tasks.get(i - 1)));
            }
            return sb.toString();
        }
    }
}