package dukechatbot.utility;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> tl;

    public TaskList(ArrayList<Task> tl) {
        this.tl = tl;
    }
    public ArrayList<Task> getArrayList() {
        return this.tl;
    }

    public void add(Task t) {
        this.tl.add(t);
    }

    public void delete(Integer id, Ui ui) {
        int actualId = id - 1;
        Task t = this.tl.remove(actualId);
        ui.removed(t, this.tl);
    }

    public void mark(int id, Ui ui) {
        Task t = this.tl.get(id - 1);
        t.markAsDone();
        ui.marked(t);
    }

    public void unmark(int id, Ui ui) {
        Task t = this.tl.get(id -1);
        t.markAsUndone();
        ui.unmarked(t);
    }

    public void find(String desc, Ui ui) {
        int count = 1;
        ArrayList<Task> tempList = new ArrayList<>();
        for (Iterator<Task> it = tl.iterator(); it.hasNext();) {
            Task curr = it.next();
            if (curr.toString().contains(desc)) {
                tempList.add(curr);
            }
        }
        ui.listMatch(tempList);
    }
}
