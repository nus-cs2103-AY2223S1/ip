package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int idx) {
        Task deleted = tasks.get(idx);
        tasks.remove(idx);
        return deleted;
    }

    public Task markTask(int idx) {
        Task task = tasks.get(idx);
        task.mark();
        return task;
    }

    public Task unmarkTask(int idx) {
        Task task = tasks.get(idx);
        task.unmark();
        return task;
    }

    public void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i=0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            // System.out.println(curr.toDataEntry());
            fw.write(curr.toDataEntry());
        }
        fw.close();
    }
}
