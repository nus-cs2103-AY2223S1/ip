import java.util.HashMap;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task of (String command, HashMap<Integer, String> map) {
        if (command.split(" ")[0].equals("mark")) {
            int num = Integer.parseInt(command.substring(5));
            Task task = new Mark(map.get(num));
            map.put(num, num + ".[X] " + map.get(num).substring(6));
            return task;
        }
        if (command.split(" ")[0].equals("unmark")) {
            int num = Integer.parseInt(command.substring(7));
            Task task = new Unmark(map.get(num));
            map.put(num, num + ".[ ] " + map.get(num).substring(6));
            return task;
        }
        return new Add(command);
    }

    public boolean AddToList() {
        return true;
    }

    /*
    * Get the status of the task.
    *
    * @return String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return " " + description + "\n";
    }
}
