package Duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> lst;

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Returns the TaskList of tasks that matches the search term.
     * @param input search term.
     * @return the result list.
     */
    public String findTask(String input) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list: \n");
        for(int i = 1; i <= lst.size(); i++){
            Task task = lst.get(i-1);
            if(task.getDescription().contains(input)){
                str.append(i).append(". ").append(task).append(" \n");
            }
        }
        return str.toString();
    }

    /**
     * Adds task to list.
     * @param t task to add.
     */
    public String addTask(Task t) {
        this.lst.add(t);
        return "Got it. I've added this task: \n  " + t +" \n Now you have " + Integer.toString(lst.size()) + " tasks in the list.\n";
    }

    /**
     * Deletes task from list.
     * @param i index of task starting from 1.
     * @return the deleted task.
     */
    public String deleteTask(int i) {
        Task removed = lst.get(i - 1);
        lst.remove(i - 1);
        return "Noted. I've removed this task: \n  " + removed + " \nNow you have " + lst.size() + " tasks in the list.\n";
    }

    /**
     * Mark task as done.
     * @param i index of task starting from 1.
     * @return the marked task.
     */
    public String markTask(int i) {
        Task t = lst.get(i - 1);
        t.markAsDone();
        return "Nice! I've marked this task as done:\n  " + t;
    }

    /**
     * Mark task as done.
     * @param i index of task starting from 1.
     * @return the marked task.
     */
    public String unmarkTask(int i) {
        Task t = lst.get(i - 1);
        t.markAsNotDone();
        return "OK, I've marked this task as not done yet:\n  " + t;
    }

    /**
     * Display items in list.
     * @return the list.
     */
    public String displayList() {
        if(lst.size() == 0 ) {
            return "You do not have any tasks currently";
        }

        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= lst.size(); i++) {
            Task task = lst.get(i - 1);
            str.append(i).append(". ").append(task).append("\n");
        }
        return str.toString();
    }

    /**
     * Converts list to list of string.
     * @return string representation of list.
     */
    public ArrayList<String> toStringList() {
        ArrayList<String> items = new ArrayList<>();
        if (!(this.lst.isEmpty())) {
            for (int i = 0; i < lst.size(); i++) {
                Task t = lst.get(i);
                String type = t.toString().substring(1, 2);
                if (type.equals("T")) {
                    items.add(String.format("%s ~ %s ~ %s\n", type, t.getStatusIcon(), t.getDescription()));
                } else {
                    items.add(String.format("%s ~ %s ~ %s ~ %s\n", type, t.getStatusIcon(),
                            t.getDescription(), t.getDate()));
                }
            }
        }
        return items;
    }

    /**
     * Returns size of list.
     * @return list size.
     */
    public int getSize() {
        return lst.size();
    }
}
