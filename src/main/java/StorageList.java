import java.time.LocalDateTime;
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
   * @throws DukeException Thrown when the index is out of bounds
   */
  public Task get(int index) throws DukeException {
    try {
      return list.get(index);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Invalid index.");
    }
  }

  /**
   * Marks the Task at the specified index as completed
   * @param i Index of the Task to be marked as completed
   * @throws DukeException Thrown when the index is out of bounds
   */
  public void mark(int i) throws DukeException {
    try {
      list.get(i).mark();
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Invalid index.");
    }
  }

  /**
   * Unmarks the Task at the specified index as completed
   * @param i Index of the Task to be unmarked as completed
   * @throws DukeException Thrown when the index is out of bounds
   */
  public void unmark(int i) throws DukeException {
    try {
      list.get(i).unmark();
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Invalid index.");
    }
  }

  /**
   * Returns the size of the list
   * @return Size of the list
   */
  public int getSize() {
    return list.size();
  }
  
  /**
   * Deletes the Task at the specified index
   * @param i Index of the Task to be deleted
   * @throws DukeException Thrown when the index is out of bounds
   */
  public void delete(int i) throws DukeException{
    try {
      list.remove(i);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Invalid index.");
    }
  }

  /**
   * An overloaded method that return the String representation of the list of task in which the date is matched.
   * @param date Date to be matched
   * @return String representation of the list of task with correct date.
   */
  public String toString(LocalDateTime date) {
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (Task t : list) {
      if (t instanceof Deadline && ((Deadline) t).getDateTime().equals(date)) {
        sb.append("\t " + i + ". " + t.toString() + "\n");
        i++;
      }
    }
    return sb.toString();
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
      ans += "\t " + (i+1) + "." + t + "\n";
    }
    return ans;
  }
}
