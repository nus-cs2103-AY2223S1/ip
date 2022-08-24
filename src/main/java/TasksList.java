import java.util.ArrayList;
import java.util.List;

public class TasksList {
    private List<Task> listOfTasks;
    private final Storage storage;

    public TasksList(String path) {
        this.storage = new Storage(path);
        this.listOfTasks = this.getSavedTasks();
    }

    private List<Task> getSavedTasks() {
        String storageTasks = this.storage.read();
        return this.parseToTasks(storageTasks);
    }

    private List<Task> parseToTasks(String storageTasks) {
        if (storageTasks == null) {
            return new ArrayList<>();
        }

        String[] arrayOfTaskStrings = storageTasks.split(System.lineSeparator());
        List<Task> taskList = new ArrayList<>();

        for (String taskString: arrayOfTaskStrings) {
            Task task = Task.fromStorageString(taskString);
            taskList.add(task);
        }
        return taskList;
    }

    public void saveTasks() {
        StringBuilder storageTasks = new StringBuilder();

        for(int i = 0; i < this.listOfTasks.size(); i++) {
            if (i > 0) {
                storageTasks.append(System.lineSeparator());
            }
            Task currentTask = this.listOfTasks.get(i);
            storageTasks.append(currentTask.toStorageString());
        }
        this.storage.write(storageTasks.toString());
    }

    public void addToList(Task task) {
        this.listOfTasks.add(task);
    }

    public Task markAsDone(int taskNumber) throws DukeException {
       if (taskNumber < 1 || taskNumber > this.listOfTasks.size()) {
           throw new DukeException("Please enter a valid task number!");
       } else {
           Task taskToMark = this.listOfTasks.get(taskNumber - 1);
           taskToMark.markAsDone();
           return taskToMark;
       }
    }

    public Task markAsUndone(int taskNumber) throws DukeException{
        if (taskNumber < 1 || taskNumber > this.listOfTasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        } else {
            Task taskToMark = this.listOfTasks.get(taskNumber - 1);
            taskToMark.markAsUndone();
            return taskToMark;
        }
    }

    public Task deleteTask(int taskNumber) throws DukeException{
        if (taskNumber < 1 || taskNumber > this.listOfTasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        } else {
            Task taskToDelete = this.listOfTasks.get(taskNumber - 1);
            this.listOfTasks.remove(taskToDelete);
            return taskToDelete;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= this.listOfTasks.size(); i++) {
            sb.append("\n");
            sb.append(i + ". " + this.listOfTasks.get(i - 1));
        }
        return sb.toString();
    }

    public int getLength() {
        return this.listOfTasks.size();
    }








}
