/**
 * Encapsulates a to-do task stored by Apollo.
 *
 * @author Kartikeya
 */
public class ToDo extends DukeTask {
  public ToDo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
