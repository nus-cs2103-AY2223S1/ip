package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.NoBeforeException;
import duke.exceptions.NoTimeException;
import duke.exceptions.OutOfRangeException;
import duke.parser.TimeParser;

public class TaskList {

    private final ArrayList<Task> tasks;
//    private static final TimeParser TIME_PARSER = new TimeParser();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private String printTask(int index) {
        return tasks.get(index).toString();
    }

    private String printAddedTask(Task task) {
        return "Got it. I've added this task: \n" + task.toString() + "\n Now you have " + tasks.size() + " in the list.";
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
            throw new OutOfRangeException();
        } else {
            tasks.get(index).markAsDone();
            return "Nice! I've marked this task as done:" + printTask(index);
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
            throw new OutOfRangeException();
        } else {
            Task currentTask = tasks.get(index);
            tasks.remove(index);
            return "Noted. I've removed this task:\n" + currentTask + "\nNow you have " +  tasks.size() + " in the list.";
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
            throw new EmptyCommandException("todo");
        }
        ToDo newTask = new ToDo(desc);
        tasks.add(newTask);
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
            throw new EmptyCommandException("deadline");
        }
        if (time == null || time.isBlank()) {
            throw new NoTimeException("deadline");
        }
        Deadline newTask = new Deadline(desc, time);
        tasks.add(newTask);
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
            throw new EmptyCommandException("event");
        }
        if (time == null || time.isBlank()) {
            throw new NoTimeException("event");
        }
        Event newTask = new Event(desc, time);
        tasks.add(newTask);
        return printAddedTask(newTask);
    }

    public String find(String desc) throws EmptyCommandException {
        if (desc == null || desc.isBlank()) {
            throw new EmptyCommandException("find");
        }
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            if (t.hasDescription(desc)) {
                sb.append(i + "." + t + "\n");
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * Prints current list.
     */
    public String printList() {
        return "Here are the tasks in your list:\n" + this.toString();
    }

    /**
     * Prints list of task before specified deadline.
     *
     * @param deadline the deadline to check
     * @throws NoBeforeException if no deadline is given
     */
    public String printDeadline(String deadline) throws NoBeforeException {
        if (deadline == null || deadline.isBlank()) {
            throw new NoBeforeException();
        }
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            if (t.isBefore(deadline)) {
                sb.append(i + "." + t + "\n");
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * Returns string representation of list.
     *
     * @return string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            Task item = tasks.get(i);
            sb.append(j + "." + item.toString() + "\n");
        }
        return sb.toString();
    }
}
