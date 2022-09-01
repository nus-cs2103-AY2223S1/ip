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
        return "Noted I have removed this task\n"
                + task.toString() + "\n" +
                "Now you have" + " " + lst.size() + " " + "tasks in list";
    }

    /**
     * Adds a task to the list
     * @param task Task that needs to be added
     */
    public String add(Task task) {
        lst.add(task);
        return "Got it.I've added this task\n" +
                task.toString()  + "\n" +
                "Now you have" + " " + lst.size() + " " + "tasks in list";
    }

    /**
     * Marks a test to indicate it as done
     * @param task Task to be marked
     */
    public String mark(Task task) {
        task.mark();
        return "Nice I have marked this as done:\n" + " " + task;


    }

    /**
     * Mark a test to indicate it as still need
     * to be done
     * @param task Task to be unmarked
     */
    public String unmark(Task task) {
        task.unmark();
        return  "Ok I have marked this as still to be done:\n"  + " " + task;
    }


    /**
     * Prints out all tasks already in TaskList
     * and lists them in order
     */
    public String list() {

        String lists = "This is your tasks in your list:\n";
        for (Task item : lst) {
            if (item != null)
                lists += lst.indexOf(item) + 1 + "." + item + "\n";;
        }
        return lists;
    }

    public String find(String searchTask) {
        String found = "Here are the matching tasks in your list:\n";
        for (Task item:lst) {
            String taskName = item.description;
            if (taskName.contains(searchTask)) {
               found += taskName + "\n";
            }
        }
        return found;
    }
}
