package amanda.manager;

import amanda.task.*;
import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> list;

    public TaskManager() {
        this.list = new ArrayList<>();
    }


    public ArrayList<Task> getList() {

        return list;
    }
}
