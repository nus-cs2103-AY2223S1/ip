package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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

    public void clear() {
        this.tasks.clear();
        this.tasksLength = 0;
    }

    public void printAllTasks() {
        for (int i = 1; i <= tasksLength; i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    public Task addTodo(String s) throws EmptyTodoException {
        if (s.length() <= 5) {
            throw new EmptyTodoException();
        }
        String result = s.substring(5);
        Todo t = new Todo(result);
        tasks.add(t);
        tasksLength++;
        return t;
    }

    private static String parseDate(String s) {
        try {
            LocalDate date = LocalDate.parse(s);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return s;
        }
    }

    public Task addDeadline(String s) throws DeadlineFormatException {
        if (s.length() <= 9) {
            throw new DeadlineFormatException();
        }
        String[] stuff = s.substring(9).split(" /by ");
        if (stuff.length < 2) {
            throw new DeadlineFormatException();
        }
        Deadline t = new Deadline(stuff[0], parseDate(stuff[1]));
        tasks.add(t);
        tasksLength++;
        return t;
    }

    public Task addEvent(String s) throws EventFormatException {
        if (s.length() <= 6) {
            throw new EventFormatException();
        }
        String[] stuff = s.substring(6).split(" /at ");
        if (stuff.length < 2) {
            throw new EventFormatException();
        }
        Event t = new Event(stuff[0], parseDate(stuff[1]));
        tasks.add(t);
        tasksLength++;
        return t;
    }

    public Task markTask(String s) throws TaskNumberException {
        try {
            int n = Integer.parseInt(s.substring(5));
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

    public Task unmarkTask(String s) throws TaskNumberException {
        try {
            int n = Integer.parseInt(s.substring(7));
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

    public Task deleteTask(String s) throws TaskNumberException {
        try {
            int n = Integer.parseInt(s.substring(7));
            if (n > tasksLength) {
                throw new TaskNumberException();
            } else {
                Task t = tasks.remove(n - 1);
                tasksLength--;
                return t;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new TaskNumberException();
        }
    }

    public ArrayList<Task> findTasks(String line) throws EmptyFindException {
        if (line.length() <= 5) {
            throw new EmptyFindException();
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
}
