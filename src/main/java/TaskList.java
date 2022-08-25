import java.io.IOException;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> lst;

    TaskList() {
        this.lst = new ArrayList<Task>();
    }

    TaskList(ArrayList<String> lst) {
        //parse task from string to task
        this.lst = new ArrayList<Task>();
        for (int i = 0; i < lst.size(); i++) {
            this.lst.add(Task.parseTask(lst.get(i)));
        }
    }

    public void addNewTask(Task task) {
        this.lst.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        if (index > lst.size() - 1 || index == 0) {
            throw new DukeException("There is no such task");
        }
        Task t = lst.get(index);
        this.lst.remove(index);
        System.out.println("Noted. I've removed this task: \n" + t.formatTask() + "\nNow you have " + lst.size() + " tasks in the list.");
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
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(lst.get(index).formatTask());

    }

    public void unmarkTask(int index) {
        Task t = lst.get(index);
        t.unMark();
        lst.set(index, t);
        System.out.println("OK, I've marked this task as not done yet:");
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
}
