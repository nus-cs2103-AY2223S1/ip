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


}
