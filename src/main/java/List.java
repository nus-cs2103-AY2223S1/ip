import java.util.ArrayList;

public class List {

    public ArrayList<Task> dir = new ArrayList<>();

    public List(){

    }

    public void addDir(String cmd) {
        if (!cmd.equals("list")) {
            dir.add(new Task(cmd));
        }
    }
    public void mark(int i) {
        Task task = dir.get(i);
        task.mark();
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("Here are the tasks in your list:\n");

        for(int i = 0; i < dir.size(); i++) {
            Task currTask = dir.get(i);
            tmp.append(String.format("%d.[%s] %s\n", i + 1, currTask.getStatusIcon(), currTask));
        }
        return tmp.toString();
    }
}
