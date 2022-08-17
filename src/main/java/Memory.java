import java.util.ArrayList;

/*
This class encapsulates the idea of a memory/storage of the chatbot
 */
public class Memory {
    private static ArrayList<Task> storage;

    public Memory() {
        this.storage = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return storage.get(index);
    }

    public void saveTask(Task task) {
        storage.add(task);
    }

    public int getNumOfTask() {
        return storage.size();
    }

    public boolean checkValidIndex(int index) {
        return index > -1 && index < storage.size();
    }

    public void deleteTask(int index) {
        storage.remove(index);
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < storage.size(); i ++) {
            Task current = storage.get(i);
            output = output + String.valueOf(i + 1) + "." + current.toString();
            if (i != storage.size() - 1) {
                output = output + "\n";
            }
        }
        return output;
    }

    public String numOfTaskToString() {
        return "\n" + "Now you have " + String.valueOf(storage.size()) + " tasks in the list.";
    }
}
