package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void delete(int num) throws DukeException {
        try {
            int pos = num - 1;
            tasks.remove(pos);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number!");
        }
    }

    public void mark(int num) throws DukeException {
        try {
            int pos = num - 1;
            Task curr = tasks.get(pos);
            curr.mark();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number!");
        }
    }

    public void unmark(int num) throws DukeException{
        try {
            int pos = num - 1;
            Task curr = tasks.get(pos);
            curr.unmark();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number!");
        }
    }

    public Task getTask(int num) {
        return this.tasks.get(num - 1);
    }

}
