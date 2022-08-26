package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;


    TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }


    public Task get(int ind) {
        return lst.get(ind);

    }

    public ArrayList<Task> getTasks() {
        return this.lst;
    }

    public void delete(Task task) {
        System.out.println("Noted I have removed this task");
        System.out.println(task);
        lst.remove(task);
        System.out.println("Now you have" + " " + lst.size() + " " + "tasks in list");
    }

    public void add(Task task) {
        System.out.println("Got it.I've added this task");
        lst.add(task);
        System.out.println(task);
        System.out.println("Now you have" + " " + lst.size() + " " + "tasks in list");
    }

    public void mark(Task task) {
        System.out.println("Nice I have marked this as done:");
        task.mark();
        System.out.println(" " + task);
    }
    public void unmark(Task task) {
        System.out.println("Ok I have marked this as still to be done:");
        task.unmark();
        System.out.println(" " + task);
    }


    public void list() {
        System.out.println("This is your tasks in your list: \n");
        for (Task item : lst) {
            if (item != null)
                System.out.println(lst.indexOf(item) + 1 + "." + item);
        }
    }



}
