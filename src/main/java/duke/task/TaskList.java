package duke.task;

import java.util.ArrayList;

/** Contains the task list and operations to edit the list */
public class TaskList {

    /** ArrayList of type Task */
    private static ArrayList<Task> taskArray;

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    /**
     * Returns size of taskList
     *
     * @return int.
     */
    public int getSize() {
        return taskArray.size();
    }

    /**
     * Returns a task object.
     *
     * @param taskID ID of the task object.
     * @return Task.
     */
    public Task getTask(int taskID) {
        return taskArray.get(taskID - 1);
    }

    /**
     * Adds a new Task object to taskArray
     *
     * @param task Task object.
     */
    public void addToList(Task task) {
        taskArray.add(task);
    }

    /**
     * Deletes a Task object from taskArray
     *
     * @param taskID Task index.
     */
    public void deleteFromList(int taskID) {
        taskArray.remove(taskID - 1);
    }

    /**
     * Returns a string representation of the list.
     *
     * @return String.
     */
    public String enumerateList() {
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
    public void markTheTask(int taskID) {
        Task t = taskArray.get(taskID - 1);
        t.toggleIsDone(true);
    }

    /**
     * Mark the task as not done.
     *
     * @param taskID task index.
     */
    public void unmarkTheTask(int taskID) {
        Task t = taskArray.get(taskID - 1);
        t.toggleIsDone(false);
    }

    /**
     * Returns an ArrayList of TaskIDs integer.
     *
     * @param wanted String.
     * @return ArrayList with type Integer.
     */
    public ArrayList<Integer> findListOfTaskIDs(String wanted) {
        ArrayList<Integer> arrOfTaskID = new ArrayList<>();
        for (int i = 0; i < taskArray.size(); i++) {
            if (taskArray.get(i).description.contains(wanted)) {
                arrOfTaskID.add(i);
            }
        }
        return arrOfTaskID;
    }

    /**
     * Returns a String representation of the list containing the found tasks.
     *
     * @return String.
     */
    public String getSpecificListOfTasks(ArrayList<Integer> listOfTaskID) {
        //StringBuilder over string concat for better performance
        StringBuilder s = new StringBuilder("");
        for (int i = 1; i <= listOfTaskID.size(); i++) {
            s.append("\n " + i
                    + "." + taskArray.get(listOfTaskID.get(i - 1)).toString());
        }
        return s.toString();
    }
}
