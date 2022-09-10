package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * TaskList that contains the list of Tasks and its operations.
 *
 * @author Aaron Pang
 * @version CS2103T AY22/23 Sem 1
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    /**
     * Constructs the TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Constructs the TaskList with previous inputs
     * @param dataList Previous input data
     */
    public TaskList(ArrayList<String> dataList) {
        this.tasks = new ArrayList<>();
        for (String data : dataList) {
            tasks.add(Task.loadTask(data));
        }
        this.size = tasks.size();
    }

    /**
     * Saves the TaskList after user input bye.
     * @return ArrayList of the input that has been saved.
     */
    public ArrayList<String> bye() {
        return saveTasks();
    }

    /**
     * Saves the TaskList
     * @return ArrayList of the input that has been saved.
     */
    public ArrayList<String> saveTasks() {
        ArrayList<String> list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(task.saveTask());
        }
        return list;
    }

    /**
     * Marks a task.
     *
     * @param in Input of the user.
     * @return message for user
     */
    public String markTask(String in) {
        char n = in.charAt(5);
        int number = Character.getNumericValue(n) - 1;
        Task t = tasks.get(number);
        t.markAsDone();
        String line1 =  "Nice! I've marked this task as done:";
        String line2 = t.toString();
        String mark = line1 + "\n" + line2;
        return mark;
    }

    /**
     * Unmarks a task.
     *
     * @param in Input of the user.
     * @return message for user
     */
    public String unmarkTask(String in) {
        char n = in.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        Task t = tasks.get(number);
        t.markAsUndone();
        String line1 = "OK, I've marked this task as not done yet:";
        String line2 = t.toString();
        String unmark = line1 + "\n" + line2;
        return unmark;
    }

    /**
     * Lists the inputs of the user.
     *
     * @return message for user
     */
    public String list() {
        int count = 1;
        String list = "Here are the tasks in your list:" + "\n";
        for (Task task : tasks) {
            String newLine = count + ". " + task.toString() + "\n";
            list += newLine;
            count += 1;
        }
        return list;
    }

    /**
     * Deletes a task.
     *
     * @param in Input of the user.
     * @return message for user
     */
    public String delete(String in) {
        char n = in.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(number).toString());
        tasks.remove(number);
        size -= 1;
        return "Now you have " + size + " tasks in the list";
    }

    /**
     * Returns a message for deadline input.
     *
     * @param in Input of the user.
     * @return message for user
     * @throws DukeException An exception unique to duke.Duke.
     */
    public String deadline(String in) throws DukeException {
        String deadLine = in.replaceFirst("deadline", "");
        if (deadLine.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            return addDeadline(deadLine);
        }
    }

    /**
     * Adds a deadline event to the list.
     *
     * @param deadLine Input of the user.
     * @return message for user.
     */
    public String addDeadline(String deadLine) {
        String[] aStr = deadLine.split("/by ", 2);
        String desc = aStr[0];
        String by = aStr[1];
        Deadline d = new Deadline(desc, LocalDate.parse(by));
        tasks.add(d);
        size += 1;
        String line1 = "Got it. I've added this task:";
        String line2 = "  " + d.toString();
        String line3 = "Now you have " + size + " tasks in the list";
        String message = line1 + "\n" + line2 + "\n" + line3;
        return message;
    }

    /**
     * Returns a message for event input.
     *
     * @param in Input of the user.
     * @return Message for the user.
     * @throws DukeException An exception unique to duke.Duke.
     */
    public String event(String in) throws DukeException {
        String event = in.replaceFirst("event", "");
        if (event.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            return addEvent(event);
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param event Input of the user.
     * @return Message for the user
     */
    public String addEvent(String event) {
        String[] aStr = event.split("/at ", 2);
        String desc = aStr[0];
        String by = aStr[1];
        Event e = new Event(desc, LocalDate.parse(by));
        tasks.add(e);
        size += 1;
        String line1 = "Got it. I've added this task:";
        String line2 = "  " + e.toString();
        String line3 = "Now you have " + size + " tasks in the list";
        String message = line1 + "\n" + line2 + "\n" + line3;
        return message;
    }

    /**
     * Adds a to-do task to the list.
     *
     * @param in Input of the user.
     * @return message for user
     * @throws DukeException An exception unique to duke.Duke.
     */
    public String todo(String in) throws DukeException {
        String todo = in.replaceFirst("todo", "");
        if (todo.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            Todo t = new Todo(todo);
            tasks.add(t);
            size += 1;
            String line1 = "Got it. I've added this task:";
            String line2 = "  " + t.toString();
            String line3 = "Now you have " + size + " tasks in the list";
            String message = line1 + "\n" + line2 + "\n" + line3;
            return message;
        }
    }

    /**
     * Finds tasks in list
     * @param in Input of the user
     * @return message for user
     * @throws DukeException If there is an error in the input
     */
    public String find(String in) throws DukeException {
        String toBeFound = in.replaceFirst("find", "");
        if (toBeFound.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of what is to be found cannot be empty.");
        } else {
            int count = 1;
            String find = "Here are the matching tasks in your list:" + "\n";
            for ( Task task : tasks ) {
                if (task.getDescription().contains(toBeFound.trim())) {
                    String newLine = count + ". " + task.toString() + "\n";
                    count += 1;
                    find += newLine;
                }
            }
            return find;
        }
    }
}
