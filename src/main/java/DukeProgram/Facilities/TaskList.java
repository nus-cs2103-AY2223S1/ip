package DukeProgram.Facilities;

import DukeProgram.Storage.SaveManager;
import Exceptions.JobNameException;
import Exceptions.KeyNotFoundException;
import DukeProgram.Task;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TaskList is an object that represents a collection of tasks.
 */
public class TaskList implements List<Task>, Serializable {
    private static HashMap<String, TaskList> taskListsMapping;

    private static TaskList current;

    private final ArrayList<Task> taskArrayList = new ArrayList<>(100);
    private String name;

    private TaskList(String name) {
        this.name = name;

        taskListsMapping.put(name, this);
    }

    /**
     * Initialises the saved task lists from the saved data. If there is
     * no saved data, a new empty collection for task lists will be made.
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

    /**
     * Retrieves the task list currently in use
     * @return the current task list being used
     */
    public static TaskList current() {
        return current;
    }

    /**
     * Retrieves a task list by name, if it exists
     * @param name the name of the task list to retrieve
     * @return the task list with the provided name
     * @throws KeyNotFoundException if there is no such task list with the given name
     */
    public static TaskList getTaskList(String name) throws KeyNotFoundException {
        if (!taskListsMapping.containsKey(name)) {
            throw new KeyNotFoundException(name, "taskListMapping");
        }
        return taskListsMapping.get(name);
    }

    /**
     * Changes the currently active task list used by the program
     * @param name the name of the task list to switch to
     * @throws KeyNotFoundException if there is no such task list with the given name
     */
    public static void changeTaskList(String name) throws KeyNotFoundException{
        if (current != null) {
            SaveManager.save("taskListsMapping", taskListsMapping);
        }

        current = getTaskList(name);
    }


    /**
     * Gets the total number of task lists stored presently
     * @return the number of task lists stored
     */
    public static int getNumberOfTaskLists() {
        return taskListsMapping.size();
    }

    /**
     * Gets all the respective names of the task lists stored
     * @return a String array of the names of every task list in no particular order
     */
    public static String[] getAllTaskListNames() {
        return taskListsMapping.keySet().toArray(new String[0]);
    }

    /**
     * Adds a new task list with the given name
     * @param name the name of the new task list
     * @return the new task list that was created and stored
     */
    public static TaskList addNewTaskList(String name) {
        TaskList taskList = new TaskList(name);
        taskListsMapping.put(taskList.name, taskList);

        SaveManager.save("taskListsMapping", taskListsMapping);

        return taskList;
    }

    /**
     * Removes from the stored task lists the task list with the given name,
     * if it exists
     * @param name name of the task list to remove
     * @throws KeyNotFoundException if there is no such task list with that name
     */
    public static void removeTaskList(String name) throws KeyNotFoundException{
        if (!taskListsMapping.containsKey(name)) {
            throw new KeyNotFoundException(name, "taskListsMapping");
        }

        taskListsMapping.remove(name);
        SaveManager.save("taskListsMapping", taskListsMapping);
    }

    /**
     * Retrieves the name of this task list
     * @return the name of this task list
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this task list to the new name given
     * @param newName the new name that this task list will be renamed to
     */
    public void changeName(String newName) {
        taskListsMapping.remove(name);
        this.name = newName;
        taskListsMapping.put(name, this);

        SaveManager.save("taskListsMapping", taskListsMapping);
    }


    /**
     * A helper method to concat a string array given in the format from System.in
     * @param input The string array given in the format from System.in
     * @return the actual name of the job to be created
     * @throws JobNameException if there is no name provided
     */
    private static String concatName(String[] input) throws JobNameException {
        String name = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));
        if (name.equals("")) {
            throw new JobNameException(input[0]);
        }
        return name;
    }

    @Override
    public int size() {
        return taskArrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return taskArrayList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return taskArrayList.contains(o);
    }

    @Override
    public Iterator<Task> iterator() {
        return taskArrayList.iterator();
    }

    @Override
    public Object[] toArray() {
        return taskArrayList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return taskArrayList.toArray(a);
    }

    @Override
    public boolean add(Task task) {
        return taskArrayList.add(task);
    }

    @Override
    public boolean remove(Object o) {
        return taskArrayList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return taskArrayList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Task> c) {
        return taskArrayList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Task> c) {
        return taskArrayList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return taskArrayList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return taskArrayList.retainAll(c);
    }

    @Override
    public void clear() {
        taskArrayList.clear();
    }

    @Override
    public Task get(int index) {
        return taskArrayList.get(index);
    }

    @Override
    public Task set(int index, Task element) {
        return taskArrayList.set(index, element);
    }

    @Override
    public void add(int index, Task element) {
        taskArrayList.add(index, element);
    }

    @Override
    public Task remove(int index) {
        return taskArrayList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return taskArrayList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return taskArrayList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Task> listIterator() {
        return taskArrayList.listIterator();
    }

    @Override
    public ListIterator<Task> listIterator(int index) {
        return taskArrayList.listIterator(index);
    }

    @Override
    public List<Task> subList(int fromIndex, int toIndex) {
        return taskArrayList.subList(fromIndex, toIndex);
    }
}
