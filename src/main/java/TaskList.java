import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> addedTasks;

    private Storage storage;

    private Ui ui;

    public TaskList(Parser parser, Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
        this.addedTasks = new ArrayList<>(100);
    }
    
    public int getSize() {
        return this.addedTasks.size();
    }

    public void reportSize() {
        if (this.getSize() == 1) {
            System.out.println("There is " + this.getSize() + " task in your list.");
        } else {
            System.out.println("There are " + this.getSize() + " tasks in your list.");
        }
    }

    public void addTask(Task task) {
        this.addedTasks.add(task);
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskAddedMessage(task);
        reportSize();
    }

    public void deleteTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task removedTask = this.addedTasks.remove(index - 1);
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskDeletedMessage(removedTask);
        reportSize();
    }

    public void listTask() {
        System.out.println("Listing your current tasks:");
        for (int i = 0; i < this.getSize(); ++i) {
            System.out.println((i + 1) + ". " + this.addedTasks.get(i));
        }
    }

    public void markTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToMark = this.addedTasks.get(index - 1);
        taskToMark.mark();
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskMarkedMessage(taskToMark);
    }

    public void unmarkTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToUnmark = this.addedTasks.get(index - 1);
        taskToUnmark.unmark();
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskMarkedMessage(taskToUnmark);
    }

}
