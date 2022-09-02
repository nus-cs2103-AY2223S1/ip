package functional;
/**
 * Class for tasks with a deadline
 * @author Nicholas Patrick
 */

import technical.SaveLine;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDateTime deadline;

  /**
   * Construct a task with a name and a deadline.
   *
   * @param name The name of the task.
   * @param deadline The deadline of the task.
   */
  public Deadline(String name, LocalDateTime deadline) {
    super(name);
    this.deadline = deadline;
  }

  /**
   * Construct a task with a deadline from a SaveLine. If the argument is
   * invalid, an error may or may not be thrown.
   *
   * @param line The SaveLine containing necessary information.
   */
  public Deadline(SaveLine line) {
    super(line);
    deadline = LocalDateTime
        .parse(line.getValue("deadline"),
            DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss"));
  }

  /**
   * Shows the task name, status, and deadline as a String.
   *
   * @return A String with the task name, status, and deadline.
   */
  public String toString() {
    return String.format("[D]%s (by %s)", super.toString(),
        deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
  }

  /**
   * Turns the task into a SaveLine, so it's ready to be saved. Can also be
   * used to compare two tasks.
   *
   * @return A SaveLine with the data associated with the task.
   */
  @Override
  public SaveLine toData() {
    SaveLine ret = super.toData();
    ret.setInfoType("deadline");
    ret.addKeyValue("deadline", deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
    return ret;
  }

  /**
   * Checks whether this is equal to another Object. If the other object is
   * not a Deadline, the return value will be false.
   *
   * @param rhs The right hand side of the comparison.
   * @return The boolean stating whether this and the argument are equal.
   */
  @Override
  public boolean equals(Object rhs) {
    if (rhs instanceof Deadline) {
      Deadline rhsDeadline = (Deadline) rhs;
      return toData().equals(rhsDeadline.toData());
    }
    return false;
  }
}
