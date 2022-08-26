package duke;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    void showList() {
        Ui ui = new Ui();
        ui.displayListUi(this.list);
    }

    void addToList(Task task) {
        this.list.add(task);
        Ui ui = new Ui();
        ui.addToListUi(task, this.list);
    }

     void delete(String s) {
        int i = Integer.parseInt(s.substring(7)) - 1;
        Task task = this.list.remove(i);
         Ui ui = new Ui();
         ui.deleteUi(task, this.list);
    }
}
