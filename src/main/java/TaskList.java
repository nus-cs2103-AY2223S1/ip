import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> db;

    public TaskList() {
         db = new ArrayList<>(10);
    }

    /**
     * Initializes a TaskList from a String array passed in by the Storage class.
     * @param inputList String array containing tasks
     */
    public TaskList(ArrayList<String[]> inputList) {
        db = new ArrayList<>(10);
        for (String[] tmp: inputList) {
            switch (tmp[0]) {
                case "T":
                    db.add(new Todo(tmp));
                    break;
                case "D":
                    db.add(new Deadline(tmp));
                    break;
                case "E":
                    db.add(new Event(tmp));
                    break;
            }
        }
    }

    public int getLength() {
        return db.size();
    }

    public Task getTask(int i) {
        return db.get(i - 1);
    }

    /**
     * Marks the input task as completed.
     * @param userInput Input task number
     */
    public void mark(int userInput) {
        Task tmp = db.get(userInput - 1);
        tmp.setDone();
    }

    /**
     * Marks the input task as incomplete.
     * @param userInput Input task number
     */
    public void unmark(int userInput) {
        Task tmp = db.get(userInput - 1);
        tmp.setUndone();
    }

    /**
     * Adds a new To-Do task.
     * @param task To-do to be added
     */
    public void addTodo(Todo task) {
        db.add(task);
    }

    /**
     * Adds a new Event task.
     * @param task Event to be added
     */
    public void addEvent(Event task) {
        db.add(task);
    }

    /**
     * Adds a new Deadline task.
     * @param task Deadline to be added
     */
    public void addDeadline(Deadline task) {
        db.add(task);
    }

    /**
     * Deletes a specified task from Duke's memory.
     * @param userInput Task number to be deleted
     * @return Deleted task
     */
    public Task delete(int userInput) {
        return db.remove(userInput - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < db.size(); i++) {
            res.append(i+1).append(". ").append(db.get(i).toString()).append("\n");
        }
        return res.toString().trim();
    }

    public String[] toStringWritable() {
        String[] res = new String[db.size()];
        for (int i = 0; i < db.size(); i++) {
            res[i] = db.get(i).toStringWritable();
        }
        return res;
    }
}
