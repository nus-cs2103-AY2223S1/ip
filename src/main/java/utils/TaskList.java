package utils;

import java.util.ArrayList;
import java.util.List;

import duke.Duke;
import entities.Deadline;
import entities.Event;
import entities.Task;
import entities.Todo;
import exceptions.DukeException;

/**
 * Stores and manages the tasks entered by the user.
 */
public class TaskList {
    /**
     * The list of tasks entered by the user.
     */
    private List<Task> tasks;

    /**
     * Constructor for a TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList instance.
     *
     * @param taskList The List of tasks loaded from cache.
     * @throws DukeException If no cached tasks are available.
     */
    public TaskList(List<Task> taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("No cached tasks.");
        } else {
            this.tasks = taskList;
            Ui.sendMessage("Tasks loaded from cache.");
        }
    }

    /**
     * Getter for length of task List.
     * @return
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Utility function with logic for adding tasks to the user's task list.
     * @param description The description of the task to be added.
     * @param type The type of task to be added.
     * @param remarks The remarks to be added for events or deadlines.
     */
    public void add(String description, Duke.TaskType type, String remarks) {
        String s = "Got it. I've added this task:\n\t";
        switch (type) {
        case TODO:
            Todo t = new Todo(description);
            this.tasks.add(t);
            s = s + "  " + t;
            break;
        case DEADLINE:
            Deadline d = new Deadline(description, remarks);
            this.tasks.add(d);
            s = s + "  " + d;
            break;
        case EVENT:
            Event e = new Event(description, remarks);
            this.tasks.add(e);
            s = s + "  " + e;
            break;
        default:
            break;
        }
        int size = tasks.size();
        s = s + "\n\tNow you have " + (size) + (size == 1 ? " task" : " tasks") + " in the list.";
        Ui.sendMessage(s);
    }

    /**
     * Utility function for deleting an item in the user's task list.
     * @param index The position of the task to be deleted in the ArrayList.
     */
    public void delete(int index) {
        String s = "Got it. I've removed this task:\n\t";
        s = s + this.tasks.get(index);
        this.tasks.remove(index);
        int size = tasks.size();
        s = s + "\n\tNow you have " + (size) + (size == 1 ? " task" : " tasks") + " in the list.";
        Ui.sendMessage(s);
    }

    /**
     * Prints all tasks in the list.
     */
    public void listTasks() {
        Ui.printTasks(this.tasks);
    }

    /**
     * Marks a particular task as completed.
     * @param index The position of the task in the List.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a particular task as not completed.
     * @param index The position of the task in the List.
     */
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    /**
     * Returns the String representation of a particular task.
     * @param index The position of the task in the LIst.
     * @return A String representing the task.
     */
    public String getTaskAsString(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Search for a matching task descriptions based on a given keyword/phrase/
     * @param s A keyword or phrase to search for in the task list.
     */
    public void findAndPrintAllOccurrencesOf(String s) {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).toString().toLowerCase().contains(s)) {
                result = result + "\n\t" + this.tasks.get(i);
            }
        }
        Ui.sendMessage(result);
    }

    /**
     * Returns a modified String of all the tasks in the current TaskList.
     * @return A String representing all the tasks to be written to the hard disk.
     */
    public String formatAllTasksForFileStorage() {
        String r = "";
        for (Task t : this.tasks) {
            String s = "";
            String taskType = t.getClass().toString().split(" ")[1].substring(9); // remove entities prefix
            String status = t.getStatus() ? "d" : "nd";
            String desc = t.getDescription();
            String remarks = "";
            int index;
            if (taskType.equals("Event")) {
                index = t.toString().indexOf("at: ") + 4;
                remarks = t.toString().substring(index, t.toString().length() - 1);
            } else if (taskType.equals("Deadline")) {
                remarks = ((Deadline) t).getRemarks();
            }
            s = s + taskType.toLowerCase() + " | " + status + " | " + desc;
            if (!remarks.equals("")) {
                s = s + " | " + remarks;
            }
            r = r + s + "\n";
        }
        return r;
    }
}
