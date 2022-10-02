package cwq.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import cwq.exception.NoSuchTaskException;

/**
 * Logic controller of Duke tasks
 */
public class TasksController {
    private final ArrayList<Task> tasks;

    public TasksController(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks should not be null!";
        this.tasks = tasks;
    }

    /**
     * Return the target task.
     * @param taskIndex task index
     * @return the target task.
     * @throws NoSuchTaskException if the task doesn't exist
     */
    public Task getTask(int taskIndex) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        return tasks.get(taskIndex);
    }

    /**
     * Changes the status of a task
     * @param taskIndex index of the task in the list
     * @param status status to be changed
     * @throws NoSuchTaskException if the task doesn't exist
     */
    public void changeTaskStatus(int taskIndex, boolean status) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        tasks.get(taskIndex).changeStatus(status);
    }

    /**
     * Add a new task to the list
     * @param task task to be added
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param taskIndex index of the task
     * @throws NoSuchTaskException if the task doesn't exist
     */
    public void deleteFromList(int taskIndex) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        tasks.remove(taskIndex);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds relevant tasks by keyword
     * @param keywords user's input
     * @return a list of relevant tasks
     */
    public ArrayList<Task> findByKeyword(String ...keywords) {
        System.out.println(Arrays.toString(keywords));
        ArrayList<Task> result = new ArrayList<>();
        for (String keyword: keywords) {
            for (Task task: tasks) {
                String content = task.getTaskDescription();
                if (content.contains(keyword)) {
                    result.add(task);
                }
            }

        }
        return result;
    }

    public String getTasksString(ArrayList<Task> tasks) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            s.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Sort events and deadlines by time
     * @return sorted task list
     */
    public ArrayList<Task> sortTasksByTime() {
        ArrayList<Task> sortedTasks = new ArrayList<>();
        for(Task task: tasks) {
            if (task instanceof Event || task instanceof Deadline) {
                sortedTasks.add(task);
            }
        }
        sortedTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getTime().isBefore(o2.getTime())) {
                    return -1;
                } else if (o1.getTime().isEqual(o2.getTime())) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return sortedTasks;
    }

    /**
     * Get all upcoming deadlines.
     * @return deadline list
     */
    public ArrayList<Task> getDeadlines() {
        LocalDateTime now = LocalDateTime.now();
        ArrayList<Task> deadlines = new ArrayList<>();
        for(Task task: tasks) {
            if (task instanceof Deadline) {
                if (task.getTime().isAfter(now)) {
                    deadlines.add(task);
                }
            }
        }
        return deadlines;
    }

}
