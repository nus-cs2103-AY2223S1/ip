package dukeProgram.facilities;

import dukeprogram.Task;
import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskList implements Serializable {
    private static HashMap<String, TaskList> taskListsMapping;

    private static TaskList current;

    private final ArrayList<Task> taskArrayList = new ArrayList<>(100);
    private String name;

    private TaskList(String name) {
        this.name = name;

        taskListsMapping.put(name, this);
    }

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

    public static void changeTaskList(String name) throws KeyNotFoundException{
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

    public static TaskList addNewTaskList(String name) {
        TaskList taskList = new TaskList(name);
        taskListsMapping.put(taskList.name, taskList);

        SaveManager.save("taskListsMapping", taskListsMapping);

        return taskList;
    }

    public static void removeTaskList(String name) throws KeyNotFoundException{
        if (!taskListsMapping.containsKey(name)) {
            throw new KeyNotFoundException(name, "taskListsMapping");
        }

        taskListsMapping.remove(name);
        SaveManager.save("taskListsMapping", taskListsMapping);
    }

    public String getName() {
        return name;
    }

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
