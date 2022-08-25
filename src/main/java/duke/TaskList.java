package duke;

import duke.task.Task;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Method that finds tasks that are similar to a given keyword.
     *
     * @param keyword The String representing the matching keyword.
     * @return The TaskList containing the matching words.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            String desc = curr.toString();
            if (desc.contains(keyword)) {
                matchingTasks.addTask(curr);
            }
        }
        return matchingTasks;
    }
}
