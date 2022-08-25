import java.io.FileWriter;
import java.io.IOException;
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

  public void saveTaskList() {
    String fileString = "";
    for (int i = 0; i < taskList.size(); i++) {
      Task task = taskList.get(i);
      fileString += task.toFileString();
      fileString += System.lineSeparator();
    }
    try {
      FileWriter fw = new FileWriter("data/cheese.txt");
      fw.write(fileString);
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
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
