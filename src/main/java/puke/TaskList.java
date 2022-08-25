package puke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected int numTasks = tasks.size();
    protected int removed = 0;

    public TaskList(ArrayList<Task> s) {
        this.tasks = s;
        this.numTasks = tasks.size();
        this.removed = 0;
    }

    public void listTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < this.tasks.size() ; i++) {
            System.out.println("     " + (i+1) + "." + this.tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
        return;
    }

    public void addIncrement(Task t) {
        this.tasks.add(t);
        this.numTasks++;
    }

    public void delete(int index) {
        this.tasks.remove(index);
        this.removed++;
    }

} 
