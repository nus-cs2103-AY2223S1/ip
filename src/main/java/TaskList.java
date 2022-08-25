import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTodo(String s) {
        taskList.add(new Todo(s));
    }

    public void addDeadline(String s) {
        String[] splitWord = s.split("/by", 2);
        String description = splitWord[0];
        String by = splitWord[1];
        taskList.add(new Deadline(description, by));
    }
    public void addEvent(String s) {
        String[] splitWord = s.split("/at", 2);
        String description = splitWord[0];
        String at = splitWord[1];
        taskList.add(new Event(description, at));
    }

    public String readTask(int index) {
        return taskList.get(index).toString();
    }

    public String readStatus(int index) {
        return taskList.get(index).getStatus();
    }

    public void setCompleted(int index) {
        taskList.get(index).markAsCompleted();
    }

    public void setNotCompleted(int index) {
        taskList.get(index).markAsNotCompleted();
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public String getDescription(int index) {
        return taskList.get(index).getDescription();
    }

    public String getDate(int index) {
        Task curr = taskList.get(index);
        if (curr instanceof Deadline) {
            return ((Deadline) curr).getBy();
        } else if (curr instanceof Event) {
            return ((Event) curr).getAt();
        } else {
            return "";
        }
    }
}
