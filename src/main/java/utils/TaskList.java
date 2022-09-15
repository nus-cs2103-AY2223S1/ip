package utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * Adds tasks to the user's task list.
     * @param description The description of the task to be added.
     * @param type The type of task to be added.
     * @param remarks The remarks to be added for events or deadlines.
     */
    public void add(String description, Duke.TaskType type, String remarks) throws DukeException {
        int size;
        switch (type) {
        case TODO:
            Todo t = new Todo(description);

            if (this.tasks.contains(t)) {
                throw new DukeException("Task already exists! Duplicates are not allowed.");
            }

            size = this.tasks.size();
            this.tasks.add(t);
            assert(this.tasks.size() > size);
            break;
        case DEADLINE:
            Deadline d = new Deadline(description, remarks);

            if (this.tasks.contains(d)) {
                throw new DukeException("Task already exists! Duplicates are not allowed.");
            }

            size = this.tasks.size();
            this.tasks.add(d);
            assert(this.tasks.size() > size);
            break;
        case EVENT:
            Event e = new Event(description, remarks);

            if (this.tasks.contains(e)) {
                throw new DukeException("Task already exists! Duplicates are not allowed.");
            }

            size = this.tasks.size();
            this.tasks.add(e);
            assert(this.tasks.size() > size);
            break;
        default:
            break;
        }
    }

    /**
     * Deletes item in the user's task list.
     * @param index The position of the task to be deleted in the ArrayList.
     */
    public void delete(int index) {
        String s = "Got it. I've removed this task:\n\t";
        s = s + this.tasks.get(index);

        int tempSize = this.tasks.size();
        this.tasks.remove(index);
        int size = this.tasks.size();

         assert(size < tempSize);

        s = s + "\n\tNow you have " + (size) + (size == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Prints all tasks in the list.
     */
    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "No items stored";
        } else {
            String s = "Here are the tasks in your list:\n";
            for (int i = 0; i < this.tasks.size(); i++) {
                Task t = this.tasks.get(i);
                s = s + "\t" + (i + 1) + ". " + t + "\n";
            }
            return s.trim();
        }
    }

    /**
     * Marks a particular task as completed.
     * @param index The position of the task in the List.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
        assert(this.tasks.get(index).getStatus());
    }

    /**
     * Marks a particular task as not completed.
     * @param index The position of the task in the List.
     */
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
        assert(!this.tasks.get(index).getStatus());
    }

    /**
     * Checks if the task at the specified is a deadline instance.
     * @param index The index of the task in the task list.
     * @return True if the task is a deadline instance, and false otherwise.
     * @throws DukeException If the index entered is out of bounds.
     */
    public boolean checkIfTaskIsDeadline(int index) throws DukeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("Please enter a valid task ID.");
        }
        return this.tasks.get(index) instanceof Deadline;
    }

    /**
     * Checks if the date to postpone the task to is later than the current date
     * of the task at the specified index.
     * @param index The index of the task in the task list.
     * @param to The new date to update the task deadline to.
     * @return True if the date entered is valid, and false otherwise.
     */
    public boolean checkIfInvalidDate(int index, String to) {
        String date = to.split(" ")[0].trim();
        String time = to.split(" ")[1].trim();
        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time.substring(0, 2) + ":" + time.substring(2));
        if (this.tasks.get(index) instanceof Deadline) {
            return ((Deadline) this.tasks.get(index)).hasDeadlineAfter(dateTime);
        }
        return false;
    }

    /**
     * Modifies the due date of the deadline task to the new date specified
     * in the string 'to'.
     * @param index The index of the deadline task to be modified.
     * @param to The new due date of the deadline.
     */
    public void updateDeadlineDueDate(int index, String to) {
        String date = to.split(" ")[0].trim();
        String time = to.split(" ")[1].trim();
        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time.substring(0, 2) + ":" + time.substring(2));
        if (this.tasks.get(index) instanceof Deadline) {
            Deadline d = (Deadline) this.tasks.get(index);
            d.setDateTime(dateTime);
        }
    }

    /**
     * Returns the String representation of a particular task.
     * @param index The position of the task in the List.
     * @return A String representing the task.
     */
    public String getTaskAsString(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Gets all tasks whose description match the input string.
     * @param s The string to search the task list for.
     * @return A string of tasks that match the input string.
     */
    public String getAllOccurrencesOf(String s) {
        String result = "Here are the matching tasks in your list:\n";
        List<String> ls = this.tasks.stream()
                .map(t -> t.toString().toLowerCase())
                .collect(Collectors.toList());
        return result + "\n\t" + this.tasks.stream()
                .map(Task::toString)
                .filter(str -> str.toLowerCase().contains(s))
                .map(str -> ls.indexOf(str.toLowerCase()) + 1 + ". " + str)
                .reduce((x, y) -> x + "\n\t" + y)
                .orElse("No matching tasks found.");
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
