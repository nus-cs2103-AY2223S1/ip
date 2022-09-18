package duke.task;

import duke.exceptions.DukeIndexRangeException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList.
     *
     * @param tasks Array of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Returns the String representation of this TaskList.
     *
     * @return String to be shown to the user.
     */
    public String list() {
        StringBuilder sb = new StringBuilder(tasks.size() + 1);
        sb.append("Here are your tasks! \n");
        for (int i = 1; i < tasks.size() + 1; i++) {
            sb.append(tasks.get(i - 1).toStringWithIndex(i)).append("\n");
        }
        if (tasks.size() == 0) {
            return "No tasks found! Come catch me hehe >:)";
        }
        return sb.toString();
    }

    /**
     * Marks a specific task in this Tasklist as complete.
     *
     * @param index Index of the Task to be marked.
     * @return Output message of a successful mark.
     * @throws DukeIndexRangeException Exception when target to mark does not exist.
     */
    public String mark(int index) throws DukeIndexRangeException {
        Task task;

        try {
            task = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexRangeException("mark", index, tasks.size());
        }

        task.mark();
        return "Meow task done. Good job!\n" +
                "  " + task;
    }

    /**
     * Marks a specific task in this Tasklist as incomplete.
     *
     * @param index Index of the Task to be unmarked.
     * @return Output message of a successful un-mark.\
     * @throws DukeIndexRangeException Exception when target to unmark does not exist.
     */
    public String unmark(int index) throws DukeIndexRangeException {
        Task task;

        try {
            task = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexRangeException("unmark", index, tasks.size());
        }

        task.unmark();
        return "Boooo. Task is marked as not done.\n" +
                "  " + task;
    }

    /**
     * Deletes a specific task in this Tasklist.
     *
     * @param index Index of the Task to be deleted.
     * @return Output message of a successful delete.
     * @throws DukeIndexRangeException Exception when target to delete does not exist.
     */
    public String delete(int index) throws DukeIndexRangeException {
        Task task;

        try {
            task = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexRangeException("delete", index, tasks.size());
        }

        this.tasks.remove(index);
        assert !this.tasks.contains(task);
        return "Yikes! I've removed this task:\n" +
                "  " + task.toString() + "\n" +
                String.format("Now you have %d tasks in the list.", this.tasks.size());
    }

    /**
     * Adds a new task to this TaskList.
     *
     * @param task Task to be added.
     * @return Output message after successfully adding the task.
     */
    public String add(Task task){
        this.tasks.add(task);
        assert this.tasks.contains(task);
        return "Hiss. I've added this task:\n" +
                "   " + task.toString() + "\n" +
                String.format("Now you have %d tasks in the list.", this.tasks.size());
    }

    /**
     * Adds a new tag to a task in this TaskList.
     *
     * @param index Index of task to be tagged.
     * @param tag Tag to be added to the task.
     * @return Output message after successfully adding the tag.
     * @throws DukeIndexRangeException Exception when target to tag does not exist.
     */
    public String addTag(int index, Tag tag) throws DukeIndexRangeException {
        Task task;

        try {
            task = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexRangeException("tag", index, tasks.size());
        }

        task.addTag(tag);
        return String.format("Chirp. I've add the tag (%s) to %s", tag, task);
    }

    /**
     * Returns the String representation to be stored in the save file (tasks.txt).
     *
     * @return String formatted according to the save file.
     */
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Finds all matching tasks containing a specific string.
     *
     * @param toFind String to find.
     * @return Print format of all the tasks found.
     */
    public String find(String toFind) {
        StringBuilder sb = new StringBuilder().append("Found these cheese in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            assert curr != null;
            if (curr.description.contains(toFind)) {
                sb.append(curr.toStringWithIndex(i + 1)).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Checks if this TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
}
