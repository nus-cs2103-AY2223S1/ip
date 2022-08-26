package duke.task;

import duke.DateTime;

public class Deadline extends Task {
  private DateTime dateTime;

  public Deadline(String description, Boolean isDone, String by) {
    super(description, isDone);
    this.dateTime = new DateTime(by);
  }

  public Deadline(String description, String by) {
    super(description);
    this.dateTime = new DateTime(by);
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.toString());
  }

  @Override
  public String[] getPrintRepresentation() {
    String[] strArray = super.getPrintRepresentation();
    return new String[] { "Deadline", strArray[1], strArray[2], this.dateTime.toString() };
  }
}
