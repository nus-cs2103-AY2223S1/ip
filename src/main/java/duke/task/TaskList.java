package duke.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TaskList class which contains and operates on the task list
 */
public class TaskList {

    private final List<Task> taskList;

    /**
     * Initialises an empty list of tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialises a preset list of tasks
     *
     * @param taskList Preset list of tasks
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public static void markAsDone(Task task) {
        task.isDone = true;
    }

    /**
     * Marks or unmarks task at specified index
     *
     * @param action    Either mark or unmark
     * @param taskIndex Index of task to mark
     */
    public String mark(String action, int taskIndex) {
        Task task = taskList.get(taskIndex - 1);
        task.isDone = action.equals("mark");
        return ((action.equals("mark")
                ? "OK, I've marked this task as not done yet: \n"
                : "Nice! I've marked this task as done: \n")
                + task);
    }

    /**
     * Prints out the contents of the task list
     */
    public String printList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            builder.append((i + 1) + "." + taskList.get(i));
        }
        return builder.toString();
    }

    /**
     * Adds new task to task list
     *
     * @param task Task to add
     */
    public void addNewTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task at index of task list
     *
     * @param taskIndex Index of task to delete
     */
    public Task deleteTask(int taskIndex) {
        Task task = taskList.remove(taskIndex - 1);
        return task;
    }

    /**
     * Displays all tasks that contain keyword
     *
     * @param keyword Word to look for
     */
    public String findTasks(String keyword) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks in your list:");
        int i = 1;
        for (Task task : taskList) {
            if (task.description.contains(keyword)) {
                builder.append(i).append(".").append(task);
                i++;
            }
        }
        return builder.toString();

    }

    /**
     * Returns task list as data in disk storage
     *
     * @return Data representation of task list
     */
    public String getDataList() {
        StringBuilder buffer = new StringBuilder();

        for (Task task : taskList) {
            buffer.append(task.toDataString()).append("\n");
        }

        return buffer.toString();
    }


    public String getScheduleView() {
        Map<String, List<String>> sortedSchedule = getSortedSchedule();
        List<String> scheduleView = new ArrayList<>();

        for (String date : sortedSchedule.keySet()) {
            List<String> tasksAtDate = sortedSchedule.get(date);
            scheduleView.add(date);
            scheduleView.addAll(tasksAtDate);
        }

        return sortedSchedule.toString();
    }

    private Map<String, List<String>> getSortedSchedule() {
        Map<String, List<String>> map = new HashMap<>();

        for (Task task : taskList) {
            String dateKey = task.getDateString();
            if (!map.containsKey(dateKey)) {
                map.put(dateKey, new ArrayList<>());
            }
            List<String> tasksAtDate = map.get(dateKey);
            tasksAtDate.add(task.toString());
            map.put(dateKey, tasksAtDate);
        }

        return map;
    }

}
