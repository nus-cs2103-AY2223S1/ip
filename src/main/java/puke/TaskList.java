package puke;

import java.util.ArrayList;

/**
 * TaskList with list manipulating options
 */
public class TaskList {
    /**
     * field to store the tasks
     */
    protected ArrayList<Task> tasks = new ArrayList<>();
    /**
     * how many tasks in the list
     */
    protected int numTasks = tasks.size();
    protected int removed = 0;



    /**
     * Creates a TaskList
     * @param s ArrayList to store tasks in Tasklist class
     */
    public TaskList(ArrayList<Task> s) {
        this.tasks = s;
        this.numTasks = tasks.size();
        this.removed = 0;
    }

    /**
     * Lists all the tasks that are currently in the list
     */
    public String listTasks() {
        String temp = "";
        for(int i = 0; i < this.tasks.size() ; i++) {
            temp += "     " + (i+1) + "." + this.tasks.get(i) + "\n";
        }
        return "\n"
                + "     Here are the tasks in your list:\n"
                + temp;
    }

    /**
     * Adds a tasks to the list
     * @param t task to add
     */
    public void addIncrement(Task t, Storage s) {
        this.tasks.add(t);
        this.numTasks++;
        s.saveTasks(tasks);
    }

    /**
     * Deletes a task from the list
     * @param index position of task on the list to delete
     */
    public void delete(int index, Storage s) {
        this.tasks.remove(index);
        this.removed++;
        s.saveTasks(tasks);
    }

    /**
     * Used to find the list of all tasks that contains a keyword
     * @param s Keyword to search for
     * @return String containing lists of tasks
     */
    public String find(String s) {
        String str = "Here are the matching tasks in your list\n";
        int count = 1;
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).description.contains(s)) {
                str += count + "." + tasks.get(i) + "\n";
                count++;
            }
        }
        return "\n" + str + "\n";
    }

} 
