import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        lst.add(t);
        System.out.println(String.format("%s\n%s\n%s",
                            Constants.addTask, t.toString(), Constants.numTasks(lst.size())));
    }

    public void deleteTask(int i) throws DukeException {
        checkError(i);
        Task removed = lst.get(i - 1);
        lst.remove(i - 1);
        System.out.println(String.format("%s\n%s\n%s",
                Constants.deleteTask, removed.toString(), Constants.numTasks(lst.size())));
    }

    public void markTask(int i) throws DukeException {
        checkError(i);
        lst.get(i - 1).setMarked();
        System.out.println(String.format("%s\n%s", Constants.markMsg, lst.get(i - 1).toString()));
    }

    public void unmarkTask(int i) throws DukeException {
        checkError(i);
        lst.get(i - 1).setUnmarked();
        System.out.println(String.format("%s\n%s", Constants.unmarkMsg, lst.get(i - 1).toString()));
    }

    public void checkError(int i) throws DukeException{
        if (i >= lst.size()) {
            throw new DukeException(Constants.invalidInput);
        }
    }

    public void printList() {
        System.out.println(Constants.list);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, lst.get(i).toString()));
        }
    }

}
