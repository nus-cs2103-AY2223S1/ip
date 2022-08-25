import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tl;

    public TaskList(ArrayList<Task> tl) {
        this.tl = tl;
    }

    public void add(String inp) {
        String tag = String.valueOf(inp.charAt(1));
        String desc = null;
        int id = -1;
        boolean done = false;
        Task toAdd = null;
        if (String.valueOf(inp.charAt(4)).equals("X")) {
            done = true;
        }
        if (tag.equals("T")) {
            toAdd = new Todo(inp.substring(7));
        } else if (tag.equals("D")) {
            id = inp.indexOf("(by:");
            toAdd = new Deadline(inp.substring(7,id - 1),
                    inp.substring(id + 5 , inp.length() - 1));
        } else if (tag.equals("E")) {
            id = inp.indexOf("(at:");
            String timeAttr = inp.substring(id + 5, id + 21)
                    + " " + inp.substring(inp.length() - 6, inp.length() - 1);
            toAdd = new Event(inp.substring(7, id - 1), timeAttr);
        } else {
            toAdd = new Task(inp.substring(6));
        }
        if (done) {
            toAdd.markAsDone();
        }
        this.tl.add(toAdd);
    }

    public void delete(Integer id) {
        int actualId = id - 1;
        this.tl.remove(actualId);
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

}
