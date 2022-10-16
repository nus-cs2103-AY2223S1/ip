package duke.main;

import java.util.ArrayList;

import duke.commandword.CommandWord;
import duke.exception.DukeException;
import duke.task.Task;

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
    public Task deleteTask(String indexString) throws DukeException {
        try {
            Task delTask = getTask(indexString); // Throws DE
            this.taskList.remove(delTask);
            return delTask;
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
    public Task getTask(String indexString) throws DukeException {
        try {
            int index = Integer.parseInt(indexString);
            Task currTask = this.taskList.get(index - 1); // Throws AIOOBE
            return currTask;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Purr... Seems like that's an invalid task number :(");
        }
    }

    /**
     * Prints the tasks in the task list.
     */
    public String printList() {
        String output = "";
        if (!this.taskList.isEmpty()) {
            output += "Meow! These are your current tasks! :)\n";
            int i = 1;
            for (Task t : taskList) {
                String s = String.format("%d. %s\n", i, t);
                output += s;
                i++;
            }
            return output;
        } else {
            return "No tasks have been added yet!";
        }
    }

    /**
     * Marks or unmarks the task.
     * @param command Input command word.
     * @param description String array of the command word with the task number
     */
    public void markUnmarkTask(CommandWord command, String description) throws DukeException {
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

    /**
     * Find for tasks that contain keywords s.
     * @param s Keyword to search the tasklist.
     * @throws DukeException If task list is empty or if there are no matches.
     */
    public String findTask(String s) {
        if (taskList.size() == 0) { // If task list is empty
            return "Purr... I don't think you have any tasks as of now!";
        } else { // if not empty, search
            return searchTaskList(s);
        }
    }

    /**
     * Adds a note to Task at given index.
     * @param index Index of Task to add a note to.
     * @param note Given note to add to the Task.
     * @throws DukeException If the given index is invalid.
     */
    public void noteTask(String index, String note) throws DukeException {
        try {
            Task taskToNote = getTask(index);
            taskToNote.addNote(note);
        } catch (DukeException de) {
            throw de;
        }
    }
    /**
     * Searches task list for tasks that contain the given input.
     * @param input Input string to search the task list with.
     * @return String of matching tasks.
     */
    public String searchTaskList(String input) {
        int count = 1;
        String output = String.format("Here are the tasks that have \"%s\"!\n", input);
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.toString().contains(input)) {
                output += String.format("%d. %s\n", count, currTask);
                count++;
            }
        }
        if (count > 1) {
            return output;
        } else {
            return "Purr... I don't think you have such tasks in the list!";
        }
    }
}
