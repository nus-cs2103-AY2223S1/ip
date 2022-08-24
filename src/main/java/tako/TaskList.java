package tako;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tako.task.Task;

public class TaskList {
    private List<Task> tasks;
    private HashMap<String, List<Task>> descriptionToTaskMap;

    public TaskList() {
        tasks = new ArrayList<>();
        descriptionToTaskMap = new HashMap<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        descriptionToTaskMap = new HashMap<>();
        for (Task task: tasks) {
            add(task);
        }
    }

    public void add(Task task) {
        this.tasks.add(task);
        String description = task.getDescription();
        String[] splitDescription = description.split(" ");
        for (String s: splitDescription) {
            if (!descriptionToTaskMap.containsKey(s)) {
                descriptionToTaskMap.put(s, new ArrayList<>());
            }
            List<Task> tasks = descriptionToTaskMap.get(s);
            tasks.add(task);
        }
    }

    public void mark(int taskNumber) throws TakoException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to mark does not exist.");
        }
        Task task = tasks.get(taskNumber);
        task.markAsDone();
    }

    public Task remove(int taskNumber) throws TakoException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to delete does not exist.");
        }
        Task task = tasks.remove(taskNumber);
        String description = task.getDescription();
        String[] splitDescription = description.split(" ");
        for (String s: splitDescription) {
            List<Task> tasks = descriptionToTaskMap.get(s);
            tasks.remove(task);
        }
        return task;
    }

    public TaskList find(String keyword) {
        List<Task> tasks = descriptionToTaskMap.get(keyword);
        return tasks == null ? new TaskList() : new TaskList(tasks);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getSize() {
        return tasks.size();
    }
}
