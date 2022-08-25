package Duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public TaskList findTask(String input) {
        TaskList resultList = new TaskList(new ArrayList<Task>());
        for (Task t : lst) {
            if (isSimilar(input, t.getDescription())) {
                resultList.addTask(t);
            }
        }
        return resultList;
    }

    public void addTask(Task t) {
        this.lst.add(t);
    }

    public Task deleteTask(int i) {
        Task removed = lst.get(i - 1);
        lst.remove(i - 1);
        return removed;
    }

    public Task markTask(int i) {
        Task t = lst.get(i - 1);
        t.markAsDone();
        return t;
    }

    public Task unmarkTask(int i) {
        Task t = lst.get(i - 1);
        t.markAsNotDone();
        return t;
    }

    public void printList() {
        int count = 1;
        for (Task tsk : this.lst) {
            System.out.println(String.valueOf(count++) + "." + tsk);
        }
    }

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

    public int getSize() {
        return lst.size();
    }

    private static boolean isSimilar(String input, String toCompare){
        Pattern pattern = Pattern.compile(".*" + input + ".*");
        Matcher matcher = pattern.matcher(toCompare);
        return (matcher.find());
    }
}
