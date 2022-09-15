package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Class to store list of tasks
 */
public class TaskList {
    private ArrayList<Task> lst;

    /**
     * Constructor for new TaskList
     */
    TaskList() {

        this.lst = new ArrayList<Task>();
    }

    /**
     * Constructor for list of tasks
     * @param lst previous list stored in storage file
     */
    TaskList(ArrayList<String> lst) {
        //parse duke.task from string to duke.task
        this.lst = new ArrayList<Task>();
        for (int i = 0; i < lst.size(); i++) {
            this.lst.add((Task.parseTask(lst.get(i))));
        }
    }

    public void addNewTask(Task task, Storage storage) {
        this.lst.add(task);
        updateStorage(storage);
    }


    public String deleteTask(int index, Storage storage) throws DukeException {
        if (index > lst.size() - 1 || index < 0) {
            throw new DukeException("There is no such task");
        }
        Task t = lst.get(index);
        this.lst.remove(index);
        updateStorage(storage);
        return("Noted. I've removed this task: \n" + t.formatTask() + "\nNow you have "
                + lst.size() + " tasks in the list.");
    }

    public int size() {
        return this.lst.size();
    }

    public void set(int index, Task task) {
        this.lst.set(index, task);
    }

    public Task get(int index) {
        return this.lst.get(index);
    }

    public String markTask(int index, Storage storage) {
        Task t = lst.get(index);
        t.markAsDone();
        lst.set(index, t);
        updateStorage(storage);
        return ("Nice! I've marked this task as done: \n" + lst.get(index).formatTask());

    }

    public String unmarkTask(int index, Storage storage) {
        Task t = lst.get(index);
        t.unMark();
        lst.set(index, t);
        updateStorage(storage);
        return ( "OK, I've marked this task as not done yet:\n" + lst.get(index).formatTask());

    }

    public void updateStorage(Storage storage) {
        System.out.println(lst.size());
        if (lst.size() == 0) {
            try {
                storage.update(lst);
            } catch (IOException e) {
                System.out.println("Something went wrong " + e.getMessage());
            }
        }
        for (int i = 0; i < lst.size(); i++) {
            try {
                storage.update(lst);
            } catch (IOException e) {
                System.out.println("Something went wrong " + e.getMessage());
            }
        }
    }

    public ArrayList<String> findTasks(String toFind) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).match(toFind)) {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }

    public ArrayList<String> findTodo() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
            if (lst.get(i).toString().charAt(0) == 'T') {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }

    public ArrayList<String> findEvent() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
            if (lst.get(i).toString().charAt(0) == 'E') {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }

    public ArrayList<String> findDeadline() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).toString().charAt(0) == 'D') {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }
}
