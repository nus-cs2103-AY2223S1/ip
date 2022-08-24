package duke.task;

import duke.Storage;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private final Storage storage;

    public TaskList(String path) {
        this.storage = new Storage(path);
        this.taskList = this.getSavedTasks();
    }

    private List<Task> getSavedTasks() {
        String storageTasks= this.storage.read();
        return this.parse(storageTasks);
    }

    private List<Task> parse(String string) {
        if (string == null) {
            return new ArrayList<>();
        }

        String[] taskStrings = string.split(System.lineSeparator());
        List<Task> taskList = new ArrayList<>();

        for (String taskString : taskStrings) {
            Task task = Task.fromStorageString(taskString);
            taskList.add(task);
        }

        return taskList;
    }

    public void saveTasks() {
        StringBuilder storageTasks = new StringBuilder();

        for (int i = 0; i < this.taskList.size(); i++) {
            if (i > 0) {
                storageTasks.append(System.lineSeparator());
            }
            storageTasks.append(this.taskList.get(i).toStorageString());
        }

        this.storage.write(storageTasks.toString());
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= this.getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            return task;
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task markDone(int index) throws DukeException {
        if (index < 0 || index >= this.getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = this.taskList.get(index);
            task.markDone();
            return task;
        }
    }

    public Task unmarkDone(int index) throws DukeException {
        if (index < 0 || index >= this.getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = this.taskList.get(index);
            task.markUndone();
            return task;
        }
    }

    @Override
    public String toString() {
        String[] stringList = new String[this.taskList.size()];
        for (int i = 0; i < this.taskList.size(); i++) {
            stringList[i] = (i + 1) + ". " + this.taskList.get(i) + "\n";
        }
        return String.join("", stringList);
    }


    /**
     * Returns a List of Tasks that matches the given keyword.
     * @param keyword The keyword to match with.
     * @return The List of matching Tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.hasKeyword(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
