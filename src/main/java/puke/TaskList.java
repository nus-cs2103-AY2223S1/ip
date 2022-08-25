package puke;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks = new ArrayList<>();
    public int numTasks = tasks.size();
    public int removed = 0;

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

    public String find(String s) {
        String s1 = "    ____________________________________________________________";
        String str = "Here are the matching tasks in your list\n";
        int count = 1;
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).description.contains(s)) {
                str += count + "." + tasks.get(i) + "\n";
                count++;
            }
        }
        System.out.println(s1 + "\n" + str + "\n" + s1);
        return s1 + "\n" + str + "\n" + s1;
    }

} 
