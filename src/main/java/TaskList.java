import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> taskList;

  public TaskList() {
    this.taskList = new ArrayList<>();
  }

  public void loadTask(String taskString) throws CheeseException {
    Task task = Task.createTaskFromFile(taskString);
    taskList.add(task);
  }

  public Task getTask(int taskIndex) throws CheeseException {
    validateTaskIndexInRange(taskIndex);
    return taskList.get(taskIndex);
  }

  public void delete(int taskIndex) throws CheeseException {
    Task task = getTask(taskIndex);
    taskList.remove(task);
  }

  public void add(Task task) {
    taskList.add(task);
  }

  public void markTaskAsDone(int taskIndex) throws CheeseException {
    Task task = getTask(taskIndex);
    task.markAsDone();
  }

  public void markTaskAsNotDone(int taskIndex) throws CheeseException {
    Task task = getTask(taskIndex);
    task.markAsNotDone();
  }

  public String toFileString() {
    String fileString = "";
    for (int i = 0; i < taskList.size(); i++) {
      Task task = taskList.get(i);
      fileString += task.toFileString();
      fileString += System.lineSeparator();
    }
    return fileString;
  }

  @Override
  public String toString() {
    String taskListString = "";
    for (int i = 0; i < taskList.size(); i++) {
      int displayIndex = i++;
      Task task = taskList.get(i);
      taskListString += displayIndex + ". " + task;
      taskListString += System.lineSeparator();
    }
    return taskListString;
  }

  private void validateTaskIndexInRange(int taskIndex) throws CheeseException {
    if (taskIndex < 0 || taskIndex >= taskList.size()) {
      throw new CheeseException("Item number is not in list range.");
    }
  }
}
