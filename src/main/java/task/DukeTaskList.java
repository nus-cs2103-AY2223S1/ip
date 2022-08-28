package task;

import java.util.ArrayList;

public class DukeTaskList {
    
    private final ArrayList<DukeTask> lst;
    
    public DukeTaskList (ArrayList<DukeTask> arrlst) {
        this.lst = arrlst;
    }

    public void add(DukeTask t) {
        lst.add(t);
    }
}
