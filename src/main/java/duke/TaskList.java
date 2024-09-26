package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Encapsulates a list of tasks for Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int tasksLength;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasksLength = 0;
    }

    public TaskList(ArrayList<Task> ts) {
        this.tasks = ts;
        this.tasksLength = ts.size();
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getTasksLength() {
        return this.tasksLength;
    }

    /**
     * Removes all the tasks in the task list.
     */
    public void clear() {
        this.tasks.clear();
        this.tasksLength = 0;
        assert (tasks.size() == 0) : "Task list and length mismatch";
    }

    /**
     * Prints all the tasks in the task list.
     */
    public void printAllTasks() {
        for (int i = 1; i <= tasksLength; i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    /**
     * Adds a task of type Todo into the task list.
     *
     * @param line user input string
     * @return the Todo task added
     */
    public Task addTodo(String line) throws EmptyFieldException {
        if (line.length() <= 5) {
            throw new EmptyFieldException();
        }

        String result = line.substring(5);
        Todo t = new Todo(result);
        tasks.add(t);
        tasksLength++;
        assert (tasks.size() == tasksLength) : "Task list and length mismatch";
        return t;
    }

    private static String parseDate(String str) {
        try {
            LocalDate date = LocalDate.parse(str);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return str;
        }
    }

    /**
     * Adds a task of type Deadline into the task list.
     *
     * @param line user input string
     * @return the Deadline task added
     */
    public Task addDeadline(String line) throws DeadlineFormatException {
        if (line.length() <= 9) {
            throw new DeadlineFormatException();
        }
        String[] stuff = line.substring(9).split(" /by ");
        if (stuff.length < 2) {
            throw new DeadlineFormatException();
        }
        Deadline t = new Deadline(stuff[0], parseDate(stuff[1]));
        tasks.add(t);
        tasksLength++;
        assert (tasks.size() == tasksLength) : "Task list and length mismatch";
        return t;
    }

    /**
     * Adds a task of type Event into the task list.
     *
     * @param line user input string
     * @return the Event task added
     */
    public Task addEvent(String line) throws EventFormatException {
        if (line.length() <= 6) {
            throw new EventFormatException();
        }
        String[] stuff = line.substring(6).split(" /at ");
        if (stuff.length < 2) {
            throw new EventFormatException();
        }
        Event t = new Event(stuff[0], parseDate(stuff[1]));
        tasks.add(t);
        tasksLength++;
        assert (tasks.size() == tasksLength) : "Task list and length mismatch";
        return t;
    }

    /**
     * Marks a task as done.
     *
     * @param line user input string
     * @return the task marked
     */
    public Task markTask(String line) throws TaskNumberException {
        try {
            int n = Integer.parseInt(line.substring(5));
            if (n > tasksLength) {
                throw new TaskNumberException();
            } else {
                tasks.get(n - 1).setDone(true);
                return tasks.get(n - 1);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new TaskNumberException();
        }
    }

    /**
     * Unmarks a task (sets it to not done yet).
     *
     * @param line user input string
     * @return the task unmarked
     */
    public Task unmarkTask(String line) throws TaskNumberException {
        try {
            int n = Integer.parseInt(line.substring(7));
            if (n > tasksLength) {
                throw new TaskNumberException();
            } else {
                tasks.get(n - 1).setDone(false);
                return tasks.get(n - 1);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new TaskNumberException();
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param line user input string
     * @return the task deleted
     */
    public Task deleteTask(String line) throws TaskNumberException {
        try {
            int n = Integer.parseInt(line.substring(7));
            if (n > tasksLength) {
                throw new TaskNumberException();
            } else {
                Task t = tasks.remove(n - 1);
                tasksLength--;
                assert (tasks.size() == tasksLength) : "Task list and length mismatch";
                return t;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new TaskNumberException();
        }
    }

    /**
     * Finds all tasks in the task list that matches keyword in given command.
     *
     * @param line user input string
     * @return an ArrayList of tasks that match the keyword
     */
    public ArrayList<Task> findTasks(String line) throws EmptyFieldException {
        if (line.length() <= 5) {
            throw new EmptyFieldException();
        }
        String filter = line.substring(5);
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(filter)) {
                result.add(t);
            }
        }
        return result;
    }

    public Task updateTaskDesc(String line) throws TaskNumberException, EmptyFieldException {
        try {
            String[] stuff = line.substring(11).split(" ");
            int n = Integer.parseInt(stuff[0]);
            if (n > tasksLength) {
                throw new TaskNumberException();
            }
            if (stuff.length < 2) {
                throw new EmptyFieldException();
            }

            String newDesc = String.join(" ", Arrays.copyOfRange(stuff, 1, stuff.length));
            Task t = tasks.get(n - 1);
            t.updateDesc(newDesc);
            assert (tasks.size() == tasksLength) : "Task list and length mismatch";
            return t;
        } catch (NumberFormatException e) {
            throw new TaskNumberException();
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyFieldException();
        }
    }

    public Task updateTaskTime(String line) throws TaskNumberException, EmptyFieldException, TaskTypeException {
        try {
            String[] stuff = line.substring(11).split(" ");
            int n = Integer.parseInt(stuff[0]);
            if (n > tasksLength) {
                throw new TaskNumberException();
            }
            if (stuff.length < 2) {
                throw new EmptyFieldException();
            }

            String newTime = String.join(" ", Arrays.copyOfRange(stuff, 1, stuff.length));
            newTime = parseDate(newTime);
            if (!(tasks.get(n - 1) instanceof TimedTask)) {
                throw new TaskTypeException();
            }

            TimedTask t = (TimedTask) tasks.get(n - 1);
            t.updateTime(newTime);
            assert (tasks.size() == tasksLength) : "Task list and length mismatch";
            return t;
        } catch (NumberFormatException e) {
            throw new TaskNumberException();
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyFieldException();
        }
    }
}
