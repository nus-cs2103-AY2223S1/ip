package duke;

import java.util.List;

public class TaskList {

  private List<Task> taskList;

  public TaskList(List<Task> taskList) {
    this.taskList = taskList;
  }

  public void printList() {
    System.out.println("Here are the tasks in your list:");
    for (int i = 1; i <= taskList.size(); i++){
      System.out.println(i + "." + taskList.get(i-1));
    }
  }

  public int getSize() {
    return taskList.size();
  }
  public void addTask(Task task) {
    taskList.add(task);
  }

  public void deleteTask (Task task) {
    taskList.remove(task);
  }

  public List<Task> getList() {
    return this.taskList;
  }
  public Task getTask(int i) {
    return this.taskList.get(i);
  }


}
