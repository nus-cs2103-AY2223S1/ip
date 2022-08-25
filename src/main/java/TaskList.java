import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) throws DukeException {
        int numTasks = tasks.size();
        if (numTasks == 0) {
            throw new DukeException("\t You do not have any tasks.");
        } else if (index < 1) {
            throw new DukeException("\t Task number starts from one.");
        } else if (index > numTasks){
            if (numTasks == 1) {
                throw new DukeException(String.format("You only have %d task.", numTasks));
            } else {
                throw new DukeException(String.format("You only have %d tasks.", numTasks));
            }
        } else {
            return tasks.get(index - 1);
        }
    }

    public void loadTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public Task remove(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public void printString() {
        String strToReturn = "";
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + ". " + tasks.get(i));
        }
    }
}
