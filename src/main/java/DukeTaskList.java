import java.util.ArrayList;

public class DukeTaskList {
    
    private final ArrayList<DukeTask> lst;
    
    DukeTaskList (ArrayList<DukeTask> arrlst) {
        this.lst = arrlst;
    }

    public void add(DukeTask t) {
        lst.add(t);
    }
}
