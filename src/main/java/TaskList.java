import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> dataArrayList) throws DukeException {
        this.tasks = new ArrayList<>();
        for (String data : dataArrayList) {
            tasks.add(Task.loadToTaskList(data));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markAsUndone();
    }

    public void addToDo(String description) {
        ToDo toDo = new ToDo(description, false);
        tasks.add(toDo);
    }

    public void addDeadline(String description, String date) {
        Deadline deadline = new Deadline(description, false, date);
        tasks.add(deadline);
    }

    public void addEvent(String description, String date) {
        Event event = new Event(description, false, date);
        tasks.add(event);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public ArrayList<String> saveToStorage() {
        ArrayList<String> dataArrayList = new ArrayList<>();
        for (Task task : tasks) {
            dataArrayList.add(task.saveStringFormat());
        }
        return dataArrayList;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "\tYou currently have no tasks in your list.";
        } else {
            String lst = "";
            int size = tasks.size();
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    lst += String.format("\t%d.%s", i + 1, tasks.get(i));
                } else {
                    lst += String.format("\t%d.%s\n", i + 1, tasks.get(i));
                }
            }
            return lst;
        }
    }
}

