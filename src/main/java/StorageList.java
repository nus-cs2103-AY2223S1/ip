import java.util.ArrayList;
import java.util.List;

/**
 * StorageList is a class that stores a list of strings
 */
public class StorageList {
  private final List<Task> list = new ArrayList<>();

  /**
   * Adds a string to the list
   * @param t Task to be added to the list
   */
  public void add(Task t) {
    list.add(t);
  }

  /**
   * Returns the Task at the specified index
   * @param index Index of the Task to be returned
   * @return Task at the specified index
   */
  public Task get(int index) {
    return list.get(index);
  }

  /**
   * Marks the Task at the specified index as completed
   * @param i Index of the Task to be marked as completed
   */
  public void mark(int i) {
    list.get(i).mark();
  }

  /**
   * Unmarks the Task at the specified index as completed
   * @param i Index of the Task to be unmarked as completed
   */
  public void unmark(int i) {
    list.get(i).unmark();
  }

  /**
   * Returns the list of strings
   * @return String representation of the list of strings
   */
  @Override
  public String toString() {
    String ans = "";
    for (int i = 0; i < list.size(); i++) {
      Task t = list.get(i);
      ans += (i+1) + ".[" + t.getStatusIcon() + "] " + t.toString() + "\n\t ";
    }
    return ans;
  }
}
