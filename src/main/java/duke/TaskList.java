package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(ArrayList<Task> dukeList) {
        this.tasks = new ArrayList<>();
        int len = dukeList.size();
        for (int i = 0; i < len; i++) {
            tasks.add(dukeList.get(i));
        }
        this.size = tasks.size();
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task markTask(int index) {
        Task markedTask = tasks.get(index);
        markedTask.markAsDone();
        return markedTask;
    }

    public Task unmarkTask(int index) {
        Task unmarkedTask = tasks.get(index);
        unmarkedTask.unmarkAsDone();
        return unmarkedTask;
    }

    public Task addTodo(String description) {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        size++;
        return todo;
    }

    public Task addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        size++;
        return deadline;
    }

    public Task addEvent(String description, String at) {
        Event event = new Event(description, at);
        tasks.add(event);
        size++;
        return event;
    }

    public Task deleteTask(int index) {
        Task deletedTask = tasks.remove(index);
        size--;
        return deletedTask;
    }

    public ArrayList<Task> saveTasks() {
        ArrayList<Task> dukeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            dukeList.add(tasks.get(i));
        }
        return dukeList;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "Hmm, there doesn't seem to be any tasks in your list.";
        } else {
            String message = "Alright, here are the tasks in your list:\n";
            for (int i = 0; i < size; i++) {
                int orderList = i + 1;
                message += Integer.toString(orderList) + ". " + tasks.get(i).toString() + "\n";
            }
            return message;
        }
    }
}
