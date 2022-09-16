package duke;

import java.util.ArrayList;

/**
 * Used to represent all the Tasks
 * to be done as it
 * contains a list of all tasks
 */
public class TaskList {
    private ArrayList<Task> lst;

    TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Returns the task in specified index
     * @param ind index of task
     * @return Task at index ind
     */
    public Task get(int ind) {
        return lst.get(ind);
    }

    /**
     * Returns ArrayList of all tasks that are currently in Tasklist
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.lst;
    }

    /**
     * Removes a task from the list if not
     * needed anymore
     * @param task Task that needs to be removed
     */
    public String delete(Task task) {
        lst.remove(task);
        return "I gotchu, I have removed this task\n"
                + task.toString() + "\n" +
                "Now you gots" + " " + lst.size() + " " + "tasks in list";
    }

    /**
     * Adds a task to the list
     * @param task Task that needs to be added
     */
    public String add(Task task) {
        lst.add(task);
        return "I gotchu, I've added this task\n" +
                task.toString()  + "\n" +
                "Now you gots" + " " + lst.size() + " " + "tasks in list";
    }

    /**
     * Marks a test to indicate it as done
     * @param task Task to be marked
     */
    public String mark(Task task) {
        task.mark();
        return "Nice I marked this as done, SIUUUU:\n" + " " + task;
    }

    /**
     * Unmarked a test to indicate it as still needs
     * to be done
     * @param task Task to be unmarked
     */
    public String unmark(Task task) {
        task.unmark();
        return  "Ok I marked this as still to be done, not so SIUUUU:\n"  + " " + task;
    }


    /**
     * Prints out all tasks already in TaskList
     * and lists them in order
     */
    public String list() {
        String lists = "This what u gotta do:\n";
        for (Task item : lst) {
            if (item != null)
                lists += lst.indexOf(item) + 1 + "." + item + "\n";;
        }
        return lists;
    }

    public String find(String searchTask) {
        String found = "This what i found fam:\n";
        for (Task item:lst) {
            String taskName = item.description;
            if (taskName.contains(searchTask)) {
               found += taskName + "\n";
            }
        }
        return found;
    }
}
