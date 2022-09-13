package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;

/**
 * Tasklist for managing tasks.
 */
public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private TaskList(List<Task> list) {
        this.tasks = list;
    }

    private int getSize() {
        return tasks.size();
    }

    private boolean isEmpty() {
        return getSize() == 0;
    }

    private String printTask(int index) {
        return tasks.get(index).toString();
    }

    private String printAddedTask(Task task) {
        return String.format("Wow, so productive... \n%s \n%d outstanding tasks",
                task.toString(), tasks.size());
    }

    private TaskList filterTasks(Predicate<? super Task> cond) {
        return new TaskList(tasks.stream().filter(cond).collect(Collectors.toList()));

    }

    private boolean hasDuplicate(String desc) {
        return filterTasks(t -> t.isSame(desc)).getSize() != 0;
    }

    /**b
     * Marks numbered task as done in task list.
     *
     * @param value a string representation of the task index to be marked as done
     * @throws DukeException if index is out of range
     */
    public String markDone(String value) throws DukeException {
        int index = Integer.parseInt(value) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Task not found!");
        }
        tasks.get(index).markAsDone();
        return String.format("Finally getting something done, huh? \n%s", printTask(index));
    }

    public String markUndone(String value) throws DukeException {
        int index = Integer.parseInt(value) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Task not found!");
        } else {
            tasks.get(index).markAsUndone();
            return String.format("One step forward. Two step backwards. \n%s", printTask(index));
        }
    }

    /**
     * Deletes numbered task in task list.
     *
     * @param value a string representation of the task index to be deleted
     * @throws DukeException if index is out of range
     */
    public String delete(String value) throws DukeException {
        int index = Integer.parseInt(value) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Task not found!");
        } else {
            Task currentTask = tasks.get(index);
            tasks.remove(index);
            return String.format("Giving up, huh? \n%s \n%d outstanding tasks",
                    currentTask, tasks.size());
        }
    }

    /**
     * Adds task to task list.
     *
     * @param task the task to be added to task list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a to do task to task list.
     *
     * @param desc the description of to do task
     * @throws DukeException if description is empty
     */
    public String addToDo(String desc) throws DukeException {
        if (desc == null || desc.isBlank()) {
            throw new DukeException("Please enter a valid description!");
        }
        if (hasDuplicate(desc)) {
            throw new DukeException("Task already exists!");
        }
        ToDo newTask = new ToDo(desc);
        addTask(newTask);
        return printAddedTask(newTask);
    }

    /**
     * Adds a deadline task to task list.
     *
     * @param desc the description of deadline task
     * @param time the deadline of deadline task
     * @throws DukeException if no description or time is given
     */
    public String addDeadline(String desc, String time) throws DukeException {
        if (desc == null || desc.isBlank()) {
            throw new DukeException("Please enter a valid description!");
        }
        if (time == null || time.isBlank()) {
            throw new DukeException("Please enter a valid deadline!");
        }
        if (hasDuplicate(desc)) {
            throw new DukeException("Task already exists!");
        }
        Deadline newTask = new Deadline(desc, time);
        addTask(newTask);
        return printAddedTask(newTask);
    }

    /**
     * Adds an event task to task list
     *
     * @param desc the description of event task
     * @param time the time of event task
     * @throws DukeException if no description or time is given
     */
    public String addEvent(String desc, String time) throws DukeException {
        if (desc == null || desc.isBlank()) {
            throw new DukeException("Please enter a valid description!");
        }
        if (time == null || time.isBlank()) {
            throw new DukeException("Please enter a valid deadline!");
        }
        if (hasDuplicate(desc)) {
            throw new DukeException("Task already exists!");
        }
        Event newTask = new Event(desc, time);
        addTask(newTask);
        return printAddedTask(newTask);
    }

    public String find(String desc) throws DukeException {
        if (desc == null || desc.isBlank()) {
            throw new DukeException("Please enter a valid description!");
        }
        return filterTasks(t -> t.hasDescription(desc)).toString();
    }

    /**
     * Prints current list.
     */
    public String printList() {
        if (isEmpty()) {
            return "You really have nothing to do, huh?";
        }
        return "Think you are free?\n" + this;
    }

    /**
     * Prints list of task before specified deadline.
     *
     * @param deadline the deadline to check
     * @throws DukeException if no deadline is given
     */
    public String printDeadline(String deadline) throws DukeException {
        if (deadline == null || deadline.isBlank()) {
            throw new DukeException("Please enter a valid deadline!");
        }
        return filterTasks(t -> t.isBefore(deadline)).toString();
    }

    /**
     * Returns string representation of list.
     *
     * @return string representation of the list.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "No such task, my friend.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            Task item = tasks.get(i);
            sb.append(j).append(".").append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}