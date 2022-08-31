package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.DukeListOobException;
import duke.exceptions.DukeWrongInputException;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object from a reference input Tasks ArrayList
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a Task item to the Tasks ArrayList accounting for the Type and Item
     * @param currTask to be added
     * @throws DukeException if inputs are missing or dates are incorrect
     */
    public void listAdd(Task currTask) throws DukeException {
        tasks.add(currTask);
    }

    /**
     * Deletes the Task item from the Tasks ArrayList at specified index (1-indexed)
     * @param indexString String representation of 1-indexed index
     * @throws DukeException if argument is of wrong format or OOB error
     */
    public Task listDelete(String indexString) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("delete");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOobException(index + 1);
        }
        Task currTask = tasks.remove(index);

        return currTask;
    }

    /**
     * Toggles the Task item completion from Tasks ArrayList at specified index (1-indexed)
     * @param indexString String representation of 1-indexed index
     * @throws DukeException if argument is of wrong format or OOB error
     */
    public Task listToggle(String indexString) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("mark");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOobException(index + 1);
        }
        Task currTask = tasks.get(index);
        currTask.completeToggle();

        return currTask;
    }

    /**
     * Returns size of Tasks ArrayList
     * @return int size
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Tasks ArrayList
     * @return Task ArrayList
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
