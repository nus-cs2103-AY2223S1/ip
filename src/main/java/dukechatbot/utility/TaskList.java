package dukechatbot.utility;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The TaskList implements the operations and attributes needed to handle
 * manipulation of the task list.
 */
public class TaskList {
    /**
     * Encapsulates the array list associated with the instance of
     * TaskList
     */
    private ArrayList<Task> tl;
    private Ui ui;

    /**
     * Constructs the instance of Task List with its associated
     * array list.
     * @param tl the array list that is associated with the TaskList instance.
     */
    public TaskList(ArrayList<Task> tl, Ui ui) {
        this.tl = tl;
        this.ui = ui;
    }

    /**
     * serves as the method to return the array list associated
     * with the TaskList instance.
     * @return the array list associated with the TaskList instance.
     */
    public ArrayList<Task> getArrayList() {
        return this.tl;
    }

    /**
     * Adds task into the array list associated with the TaskList instance
     * if duplicate is not detected else tells user that the task is a duplicate.
     * @param t the task to be added into the array list.
     */
    public String add(Task t) {
        if (isDuplicate(t)) {
            return this.ui.foundDuplicate(t);
        }
        this.tl.add(t);
        return this.ui.added(t);
    }

    /**
     * Adds the task read by Storage class into the array list
     * associated with this instance of TaskList.
     * @param ln the line read by the Storage class.
     */
    public void addFromStorage(String ln) {
        Task toAdd = createTask(ln);
        this.tl.add(toAdd);
    }

    /**
     * Creates tasks from the lines read from storage for adding into task array list.
     * @param ln the line read from storage.
     * @return the task to be added.
     */
    private static Task createTask(String ln) {
        String tag = String.valueOf(ln.charAt(1));
        String desc = null;
        int id = -1;
        boolean isDone = false;
        Task toAdd = null;
        if (String.valueOf(ln.charAt(4)).equals("X")) {
            isDone = true;
        }
        if (tag.equals("T")) {
            toAdd = new Todo(ln.substring(7));
        } else if (tag.equals("D")) {
            id = ln.indexOf("(by:");
            toAdd = new Deadline(ln.substring(7, id - 1), ln.substring(id + 5 , ln.length() - 1));
        } else if (tag.equals("E")) {
            id = ln.indexOf("(at:");
            String timeAttr = ln.substring(id + 5, id + 21)
                    + " " + ln.substring(ln.length() - 6, ln.length() - 1);
            toAdd = new Event(ln.substring(7, id - 1), timeAttr);
        } else {
            toAdd = new Task(ln.substring(6));
        }
        if (isDone) {
            toAdd.markAsDone();
        }
        return toAdd;
    }
    /**
     * Deletes the task from the array list associated with this instance of TaskList.
     * @param id the id passed in to obtain the actual task number to be deleted.
     * @param ui the instance of Ui to allow
     *           for printing out of the success of delete to user.
     */
    public String delete(Integer id, Ui ui) {
        int actualId = id - 1;
        Task t = this.tl.remove(actualId);
        return this.ui.removed(t, this.tl);
    }

    /**
     * Marks the task specified as done.
     * @param id the id passed in to obtain the actual task number to be marked done.
     * @param ui the instance of Ui to allow for printing out of the success of marking
     *           the task as done to user.
     */
    public String mark(int id, Ui ui) {
        Task t = this.tl.get(id - 1);
        t.markAsDone();
        return ui.marked(t);
    }

    /**
     * Marks the task as not done.
     * @param id the id passed in to obtain the actual task number to be marked as not done.
     * @param ui the instance of Ui to allow
     *           for printing out of the success of marking the task as not done to user.
     */
    public String unmark(int id, Ui ui) {
        Task t = this.tl.get(id - 1);
        t.markAsUndone();
        return ui.unmarked(t);
    }

    /**
     * Finds the tasks in the array list that contains
     * the description passed in.
     * @param desc the keywords that will specify which tasks to be found and shown.
     * @param ui the instance of Ui to allow
     *           for printing of the tasks that are found.
     */
    public String find(String desc, Ui ui) {
        int count = 1;
        ArrayList<Task> tempList = new ArrayList<>();
        for (Iterator<Task> it = tl.iterator(); it.hasNext();) {
            Task curr = it.next();
            if (curr.toString().contains(desc)) {
                tempList.add(curr);
            }
        }
        return ui.listMatch(tempList);
    }

    /**
     * Checks whether the task to be added is a duplicate of an
     * existing task within the task list.
     * @param t the task to be added.
     * @return true when duplicate is detected and false
     *         when there is no duplicates detected.
     */
    public boolean isDuplicate(Task t) {
        Iterator<Task> iter = this.tl.iterator();
        while (iter.hasNext()) {
            Task curr = iter.next();
            if (curr.toString().substring(7).equals(t.toString().substring(7))) {
                return true;
            }
        }
        return false;
    }
}
