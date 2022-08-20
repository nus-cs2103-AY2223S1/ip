import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskArrayList;
    private final Storage storage;

    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        this.taskArrayList = this.storage.readFromFile();
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public Task get(int index) {
        return this.taskArrayList.get(index);
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    public void remove(int index) {
        this.taskArrayList.remove(index);
    }

    public boolean doesIndexExist(int index) {
        return index >= 0 && index < this.taskArrayList.size();
    }

    public void saveToFile() throws SkylarkException {
        this.storage.saveToFile(this.taskArrayList);
    }
}
