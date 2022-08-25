import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private static final String EMPTY_LIST = "The current list is empty!";


    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addEntry(Task task) {
        tasks.add(task);
    }

    public Task deleteEntry(int index) throws OutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }
        return tasks.remove(index);
    }

    public Task get(int index) throws OutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void displayList(DukeIO io) {
        if (tasks.isEmpty()) {
            io.printTask(EMPTY_LIST);
            return;
        }
        io.printLine();
        for (int i = 0; i < tasks.size(); ++i) {
            io.printTask(String.format("%d. %s", i + 1, tasks.get(i)), 2);
        }
        io.printLine();
    }

    public ParsedData[]  getParsedData() {
        ParsedData[] ret = new ParsedData[tasks.size()];
        for (int i = 0 ; i < ret.length ; i++) {
            ret[i]  = tasks.get(i).convertToParseData();
        }
        return ret;
    }

}
