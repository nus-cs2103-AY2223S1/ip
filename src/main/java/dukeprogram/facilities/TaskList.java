package dukeprogram.facilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import dukeprogram.Task;
import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

/**
 * TaskList is a class that encapsulates the required functionalities
 * of each task list. It also organises all the collected task lists.
 */
public class TaskList implements Serializable {
    private static HashMap<String, TaskList> taskListsMapping;

    private static TaskList current;

    private final ArrayList<Task> taskArrayList = new ArrayList<>(100);
    private String name;

    private TaskList(String name) {
        this.name = name;

        taskListsMapping.put(name, this);
    }

    /**
     * Initialises a new task list from the saved objects
     */
    public static void initialise() {
        if (taskListsMapping == null) {
            try {
                taskListsMapping = SaveManager.load("taskListsMapping");
            } catch (KeyNotFoundException e) {
                taskListsMapping = new HashMap<>();
            }
        } else {
            System.out.println("TRIED TO INITIALISE TASKLIST WHEN IT ALREADY EXISTS");
        }
    }

    public static TaskList current() {
        return current;
    }

    public static TaskList getTaskList(String name) throws KeyNotFoundException {
        if (!taskListsMapping.containsKey(name)) {
            throw new KeyNotFoundException(name, "taskListMapping");
        }
        return taskListsMapping.get(name);
    }

    /**
     * Changes the current task list
     * @param name the new name to change to
     * @throws KeyNotFoundException if the name of the task list cannot be found
     */
    public static void changeTaskList(String name) throws KeyNotFoundException {
        if (current != null) {
            SaveManager.save("taskListsMapping", taskListsMapping);
        }

        current = getTaskList(name);
    }


    public static int getNumberOfTaskLists() {
        return taskListsMapping.size();
    }

    public static String[] getAllTaskListNames() {
        return taskListsMapping.keySet().toArray(new String[0]);
    }

    /**
     * Adds a new task list to the collection of task lists
     * @param name the name to associate this task list with
     * @return the new task list that was added
     */
    public static TaskList addNewTaskList(String name) {
        TaskList taskList = new TaskList(name);
        taskListsMapping.put(taskList.name, taskList);

        SaveManager.save("taskListsMapping", taskListsMapping);

        return taskList;
    }

    /**
     * Removes a task list from the collection of task lists
     * @param name the name to associate this task list with
     */
    public static void removeTaskList(String name) throws KeyNotFoundException {
        if (!taskListsMapping.containsKey(name)) {
            throw new KeyNotFoundException(name, "taskListsMapping");
        }

        taskListsMapping.remove(name);
        SaveManager.save("taskListsMapping", taskListsMapping);
    }

    public String getName() {
        return name;
    }

    /**
     * Changes the name of this current task list
     * @param newName the new name to rename this task list to
     */
    public void changeName(String newName) {
        taskListsMapping.remove(name);
        this.name = newName;
        taskListsMapping.put(name, this);

        SaveManager.save("taskListsMapping", taskListsMapping);
    }

    public Task[] getAllTasks() {
        return taskArrayList.toArray(new Task[0]);
    }


    /**
     * Retrieves the size of all the stored task lists
     * @return the size of all task lists
     */
    public int getSize() {
        return taskArrayList.size();
    }

    public boolean add(Task task) {
        return taskArrayList.add(task);
    }

    public void clear() {
        taskArrayList.clear();
    }

    public Task get(int index) {
        return taskArrayList.get(index);
    }

    public Task remove(int index) {
        return taskArrayList.remove(index);
    }
}
