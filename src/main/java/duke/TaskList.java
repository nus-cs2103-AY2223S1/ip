package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

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

    public void addNewTask(Task task) {
        this.lst.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        if (index > lst.size() - 1 || index == 0) {
            throw new DukeException("There is no such duke.task");
        }
        Task t = lst.get(index);
        this.lst.remove(index);
        System.out.println("Noted. I've removed this duke.task: \n" + t.formatTask() + "\nNow you have " + lst.size() + " tasks in the list.");
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

    public void markTask(int index) {
        Task t = lst.get(index);
        t.markAsDone();
        lst.set(index, t);
        System.out.println("Nice! I've marked this duke.task as done:");
        System.out.println(lst.get(index).formatTask());

    }

    public void unmarkTask(int index) {
        Task t = lst.get(index);
        t.unMark();
        lst.set(index, t);
        System.out.println("OK, I've marked this duke.task as not done yet:");
        System.out.println(lst.get(index).formatTask());
    }

    public void updateStorage(Storage storage) {
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
}
