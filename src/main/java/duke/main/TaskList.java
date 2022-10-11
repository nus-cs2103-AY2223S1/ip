package duke.main;

import duke.exception.DukeException;
import duke.commandword.CommandWord;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Class encapsulating the task list and its methods.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList class.
     * @param taskList ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds Task to the task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes Task from the task list.
     * @param indexString String of the index of the task to be deleted.
     * @throws DukeException If the given string index is invalid.
     */
    public void deleteTask(String indexString) throws DukeException {
        try {
            Task delTask = getTask(indexString); // Throws DE
            this.taskList.remove(delTask);
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Returns Task from the given index in the task list.
     * @param indexString String of the index of the task to be returned.
     * @return Task at the given index in the task list.
     * @throws DukeException If the given string index is invalid.
     */
    public Task getTask(String indexString) throws DukeException{
        try {
            int index = Integer.parseInt(indexString);
            Task currTask = this.taskList.get(index - 1); // Throws AIOOBE
            return currTask;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Hmm... Seems like that's an invalid task number :(");
        }
    }

    /**
     * Prints the tasks in the task list.
     */
    public void printList() {
        if (!this.taskList.isEmpty()) {
            System.out.println("These are your current tasks! :)");
            int i = 1;
            for (Task t : taskList) {
                String s = String.format("%d.%s", i, t);
                System.out.println(s);
                i++;
                // End of list
                if (t == null) {
                    break;
                }
            }
        } else {
            System.out.println("No tasks have been added yet!\n");
        }
    }

    /**
     * Marks or unmarks the task.
     * @param command Input command word.
     * @param description String array of the command word with the task number
     */
    public void markUnmarkTask (CommandWord command, String description) throws DukeException {
        try {
            Task task = getTask(description);
            if (command == CommandWord.MARK) {
                task.mark();
            } else {
                task.unmark();
            }
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks in the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    public void findTask(String s) throws DukeException{
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.toString().contains(s)) {
                System.out.println(String.format("%d. %s", count, currTask));
                count++;
            }
        }

        // If the task list is empty
        if (taskList.size() == 0) {
            throw new DukeException("Hmm... I don't think you have any tasks as of now!");
        }

        // If there are no matches in the task list
        if (count <= 1) {
            throw new DukeException("Hmm... I don't think you have such tasks in the list!");
        }
    }

}
