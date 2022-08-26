package duke.tasks;

import duke.exceptions.*;
import duke.parser.TimeParser;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private static final TimeParser TIME_PARSER = new TimeParser();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private void printTask(int index) {
        System.out.println(tasks.get(index));
    }

    private void printAddedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Marks numbered task as done in task list.
     *
     * @param value a string representation of the task index to be marked as done
     * @throws DukeException if index is out of range
     */
    public void markDone(String value) throws DukeException {
        int index = Integer.parseInt(value) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new OutOfRangeException();
        } else {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            printTask(index);
        }
    }

    /**
     * Deletes numbered task in task list.
     *
     * @param value a string representation of the task index to be deleted
     * @throws DukeException if index is out of range
     */
    public void delete(String value) throws DukeException {
        int index = Integer.parseInt(value) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new OutOfRangeException();
        } else {
            System.out.println("Noted. I've removed this task:");
            printTask(index);
            tasks.remove(index);
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
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
    public void addToDo(String desc) throws DukeException {
        if (desc == null || desc.isBlank()) {
            throw new EmptyCommandException("todo");
        }
        ToDo newTask = new ToDo(desc);
        tasks.add(newTask);
        printAddedTask(newTask);
    }

    /**
     * Adds a deadline task to task list.
     *
     * @param desc the description of deadline task
     * @param time the deadline of deadline task
     * @throws DukeException if no description or time is given
     */
    public void addDeadline(String desc, String time) throws DukeException{
        if (desc == null || desc.isBlank()) {
            throw new EmptyCommandException("deadline");
        }
        if (time == null || time.isBlank()) {
            throw new NoTimeException("deadline");
        }
        Deadline newTask = new Deadline(desc, time);
        tasks.add(newTask);
        printAddedTask(newTask);
    }

    /**
     * Adds an event task to task list
     *
     * @param desc the description of event task
     * @param time the time of event task
     * @throws DukeException if no description or time is given
     */
    public void addEvent(String desc, String time) throws DukeException{
        if (desc == null || desc.isBlank()) {
            throw new EmptyCommandException("event");
        }
        if (time == null || time.isBlank()) {
            throw new NoTimeException("event");
        }
        Event newTask = new Event(desc, time);
        tasks.add(newTask);
        printAddedTask(newTask);
    }

    public void find(String desc) throws EmptyCommandException {
        if (desc == null || desc.isBlank()) {
            throw new EmptyCommandException("find");
        }
        int i = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : tasks) {
            if (t.hasDescription(desc)) {
                System.out.println(i + "." + t);
                i++;
            }
        }
    }

    /**
     * Prints current list.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(this.toString());
    }

    /**
     * Prints list of task before specified deadline.
     *
     * @param deadline the deadline to check
     * @throws NoBeforeException if no deadline is given
     */
    public void printDeadline(String deadline) throws NoBeforeException {
        if (deadline == null || deadline.isBlank()) {
            throw new NoBeforeException();
        }
        int i = 1;
        System.out.println("Here are the tasks before the deadline " + TIME_PARSER.formatDeadline(deadline));
        for (Task t : tasks) {
            if (t.isBefore(deadline)) {
                System.out.println(i + "." + t);
                i++;
            }
        }
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