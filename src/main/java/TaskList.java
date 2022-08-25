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

  public void deleteTask(int taskIndex) throws CheeseException {
    Task task = getTask(taskIndex);
    taskList.remove(task);
    System.out.println("Gotcha! I'll forget about this task!");
    System.out.println("  " + task);
    System.out.println("You have " + taskList.size() + " task(s) remaining.");
  }

  public void addTask(Task task) {
    taskList.add(task);
    System.out.println("Gotcha! I have a paw-fect memory!");
    System.out.println("  " + task);
    System.out.println("You have " + taskList.size() + " task(s) in the list.");
  }

  public void markTaskAsDone(int taskIndex) throws CheeseException {
    Task task = getTask(taskIndex);
    task.markAsDone();
  }

  public void markTaskAsNotDone(int taskIndex) throws CheeseException {
    Task task = getTask(taskIndex);
    task.markAsNotDone();
  }

  /** Prints list */
  public void printTaskList() {
    for (int i = 1; i <= taskList.size(); i++) {
      System.out.println(i + ". " + taskList.get(i - 1));
    }
  }

  private void validateTaskIndexInRange(int taskIndex) throws CheeseException {
    if (taskIndex < 0 || taskIndex >= taskList.size()) {
      throw new CheeseException("Item number is not in list range.");
    }
  }
}
