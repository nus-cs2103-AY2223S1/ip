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

    /**
     * Marks the input task as completed.
     * @param userInput Input task number
     */
    public void mark(int userInput) {
        Task tmp = db.get(userInput - 1);
        tmp.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tmp);
    }

    /**
     * Marks the input task as incomplete.
     * @param userInput Input task number
     */
    public void unmark(int userInput) {
        Task tmp = db.get(userInput - 1);
        tmp.setUndone();
        System.out.println("OK, I've marked this task as undone:");
        System.out.println(tmp);
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

        System.out.println("Got it. I added this event:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
    }

    /**
     * Adds a new Deadline task.
     * @param task Deadline to be added
     */
    public void addDeadline(Deadline task) {
        db.add(task);

        System.out.println("Got it. I added this deadline:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
    }

    /**
     * Deletes a specified task from Duke's memory.
     * @param userInput Task number to be deleted
     */
    public void delete(int userInput) {
        Task tmp = db.remove(userInput - 1);
        System.out.println("OK, I've removed this task:");
        System.out.println("\t" + tmp);
        System.out.printf("Now you have %d tasks in the list.", db.size());
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < db.size(); i++) {
            res.append(i).append(". ").append(db.get(i).toString()).append("\n");
        }
        return res.toString();
    }

    public String[] toStringWritable() {
        String[] res = new String[db.size()];
        for (int i = 0; i < db.size(); i++) {
            res[i] = db.get(i).toStringWritable();
        }
        return res;
    }
}
