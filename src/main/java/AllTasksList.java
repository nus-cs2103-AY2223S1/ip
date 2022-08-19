import java.util.ArrayList;

/*
 * All Tasks List class used to store the tasks created by the user.
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class AllTasksList {

  /**ArrayList used to store all the tasks created by the user */
  private ArrayList<Task> allTasks = new ArrayList<>();

  /**
   * Method used to mark a task at index as complete
   *
   * @param index  the index of task to mark as complete
   * @throws DukeException
   */
  public void markTask(int index) throws DukeException {
    try {
      this.allTasks.get(index).markAsDone();
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Error: Task not found");
    }
  }

  /**
   * Method used to mark a task at index as incomplete
   *
   * @param index  the index of task to mark as incomplete
   * @throws DukeException
   */
  public void unMarkTask(int index) throws DukeException {
    try {
      this.allTasks.get(index).unmark();
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Error: Task not found");
    }
  }

  /**
   * Method used to mark a task at index as incomplete
   *
   * @param index  the index of task to mark as incomplete
   * @throws DukeException
   */
  public void delete(int index) throws DukeException {
    try {
      this.allTasks.remove(index);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Error: Task not found");
    }
  }

  /**
   * Method used to add a new task
   *
   * @param task  the task to be added
   */
  public void addTask(Task task) {
    this.allTasks.add(task);
  }

  /**
   * Method used to mark to list all available tasks
   */
  public void listAllTasks() {
    String output = "";
    for (int i = 0; i < this.allTasks.size(); i++) {
      output += "\n" + (i + 1) + ". " + this.allTasks.get(i).toString();
    }
    System.out.println(output);
  }
}
