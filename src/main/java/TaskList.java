import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list

public class TaskList {

    /** ArrayList of type Task */
    private static ArrayList<Task> taskArray;

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    public int getSize() {
        return taskArray.size();
    }

    public Task getTask(int taskID) {
        return taskArray.get(taskID - 1);
    }

    /**
     * Adds a new Task object to taskArray
     *
     * @param task Task object.
     */
    public static void addToList(Task task) {
        taskArray.add(task);
    }

    /**
     * Deletes a Task object from taskArray
     *
     * @param taskID Task index.
     */
    public static void deleteFromList(int taskID) {
        taskArray.remove(taskID - 1);
    }

    /**
     * Returns a string representation of the list.
     *
     * @return String.
     */
    public static String enumerateList() {
        //StringBuilder over string concat for better performance
        StringBuilder s = new StringBuilder("");
        for (int i = 1; i <= taskArray.size(); i++) {
            s.append("\n " + i
                    + "." + taskArray.get(i - 1).toString());
        }
        return s.toString();
    }

    /**
     * Mark the task done.
     *
     * @param taskID task index.
     */
    public static void markTheTask(int taskID) {
        Task t = taskArray.get(taskID - 1);
        t.toggleIsDone(true);
    }

    /**
     * Mark the task as not done.
     *
     * @param taskID task index.
     */
    public static void unmarkTheTask(int taskID) {
        Task t = taskArray.get(taskID - 1);
        t.toggleIsDone(false);
    }
}
