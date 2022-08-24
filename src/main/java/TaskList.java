import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(String data) throws DukeException {
        this.taskList = new ArrayList<>();
        String[] taskFromData = data.split("\n");
        for (String taskFromDatum : taskFromData) {
            if (!taskFromDatum.equals("")) this.addTaskFromStr(taskFromDatum);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    private void addTaskFromStr(String task) throws DukeException {
        Task newTask = null;
        String[] taskSplit = task.split("\\|", 3);
        String taskType = taskSplit[0];
        boolean isDone = taskSplit[1].equals("1");
        String taskDesc = taskSplit[2];
        switch (taskType) {
            case "T":
                newTask = ToDo.ToDoFromData(taskDesc, isDone);
                break;
            case "D":
                newTask = Deadline.DeadlineFromData(taskDesc, isDone);
                break;
            case "E":
                newTask = Event.EventFromData(taskDesc, isDone);
                break;
        }
        taskList.add(newTask);
    }

    public int totalTask() {
        return taskList.size();
    }

    public void markTaskN(int n, boolean isDone) {
        this.taskList.get(n - 1).isDoneSetter(isDone);
    }

    public void deleteTaskN(int n) {
        this.taskList.remove(n - 1);
    }

    public Task getTaskN(int n) {
        // start counting from 1
        return this.taskList.get(n - 1);
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) return "List is empty";
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }

    public String toFile() {
        if (taskList.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(taskList.get(i).toFile()).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }
}
