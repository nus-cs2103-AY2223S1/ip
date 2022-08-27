package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * Stores a list of tasks entered by the user.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of Tasks in this TaskList.
     *
     * @return The number of Tasks in this TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds the given Task to this TaskList.
     *
     * @param task The Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Changes the isDone status of the Task at the specific position of this TaskList,
     * according to the given boolean.
     *
     * @param pos The position of the Task in this TaskList.
     * @param isDone The new isDone status of the Task.
     * @return The Task with its new status.
     */
    public Task setDone(int pos, boolean isDone) {
        Task task = this.tasks.get(pos);
        task.setDone(isDone);
        return task;
    }

    /**
     * Removes the Task at the specific position of this TaskList.
     *
     * @param pos The position of the Task in this TaskList.
     * @return The removed Task.
     */
    public Task remove(int pos) {
        return this.tasks.remove(pos);
    }

    /**
     * Finds all Tasks in this TaskList that contain the given keyword.
     *
     * @param keyword The keyword to search.
     * @return A TaskList containing all matching Tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the encoded String representation of this TaskList.
     *
     * @return An encoded String representing this TaskList.
     */
    public List<String> encode() {
        List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(task.encode());
        }
        return encodedTasks;
    }

    /**
     * Returns the decoded TaskList from the given list of Strings.
     *
     * @param encodedTasks the list of Strings to be decoded.
     * @return A TaskList decoded from the given list.
     * @throws DukeException If the text(s) in the list is empty, or the format of the date(s) in the list is wrong.
     */
    public static TaskList decode(List<String> encodedTasks) throws DukeException {
        TaskList decodedTasks = new TaskList();
        for (String s : encodedTasks) {
            char taskType = s.charAt(0);
            String content = s.substring(2);

            switch (taskType) {
            case Todo.SYMBOL:
                decodedTasks.add(Todo.decode(content));
                break;
            case Deadline.SYMBOL:
                decodedTasks.add(Deadline.decode(content));
                break;
            case Event.SYMBOL:
                decodedTasks.add(Event.decode(content));
                break;
            default:
                break;
            }
        }
        return decodedTasks;
    }

    /**
     * Returns the String representation of this TaskList.
     *
     * @return A String representing this TaskList.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            int taskNum = i + 1;
            result += taskNum + ". " + task;

            if (i != this.tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
