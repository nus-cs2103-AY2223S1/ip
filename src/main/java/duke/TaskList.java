package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(ArrayList<String> dataList) {
        this.tasks = new ArrayList<>();
        for (String data : dataList) {
            tasks.add(Task.loadTask(data));
        }
        this.size = tasks.size();
    }

    public int getSize() {
        return size;
    }

    public ArrayList<String> saveTasks() {
        ArrayList<String> dataList = new ArrayList<>();
        for (Task task : tasks) {
            dataList.add(task.saveTask());
        }
        return dataList;
    }

    public String markTask(int idx) {
        return tasks.get(idx).mark();
    }

    public String unmarkTask(int idx) {
        return tasks.get(idx).unmark();
    }

    public String addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        size++;
        return todo.toString();
    }

    public String addDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        size++;
        return deadline.toString();
    }

    public String addEvent(String description, LocalDate at) {
        Event event = new Event(description, at);
        tasks.add(event);
        size++;
        return event.toString();
    }

    public String deleteTask(int idx) {
        Task deleted = tasks.remove(idx);
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
