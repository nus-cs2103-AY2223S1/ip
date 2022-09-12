package stashy.data.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import stashy.data.exception.StashyException;
import stashy.parser.Parser;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Represents the constructor method.
     *
     * @param loadResult The load result from Storage
     */
    public TaskList(ArrayList<String> loadResult) {
        this.taskList = new ArrayList<Task>();
        try {
            for (String rawTaskLine : loadResult) {
                this.taskList.add(Parser.parseTask(rawTaskLine));
            }
        } catch (StashyException se) {
            this.taskList = new ArrayList<Task>();
        }
    }

    /**
     * Overloads the constructor method.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Overloads the constructor method.
     * Only used to filter by a given query string.
     *
     * @param query The query string of interest
     */
    public TaskList(ArrayList<Task> taskArrayList, String query) {
        assert query.length() > 0 : "Query string cannot be empty";
        this.taskList = taskArrayList
            .stream()
            .filter(t -> t.containsText(query))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Getter method of the task list.
     *
     * @return The task list in form of arraylist of tasks
     */
    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    /**
     * Works the same way as ArrayList.add but for the list of tasks.
     *
     * @param task The task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Works the same way as ArrayList.get but for the list of tasks.
     *
     * @param index The index of the element of interest
     * @return The task referenced by its index
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Works the same way as ArrayList.remove but for the list of tasks.
     *
     * @param index The index of the element of interest
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Works the same way as ArrayList.size but for the list of tasks.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Converts the task list into a string.
     * If there are no tasks, a placeholder string is returned instead.
     *
     * @return The final string based on the number of tasks currently in the list.
     */
    @Override
    public String toString() {
        String message = IntStream
            .rangeClosed(1, this.taskList.size())
            .mapToObj(i -> i + ". " + this.taskList.get(i - 1))
            .collect(Collectors.joining("\n"));
        return message.isEmpty() ? "Nothing to see here..." : message;
    }
}
