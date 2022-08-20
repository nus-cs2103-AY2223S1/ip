package duke.task;

import duke.exception.DukeException;
import java.time.format.DateTimeParseException;

public abstract class Task {

  protected final String title;
  protected boolean status;

  Task(String title, boolean status) {
    this.title = title;
    this.status = status;
  }

  public static Task decode(String input) throws DukeException {
    String[] inputs = input.trim().split("\\s+\\|\\s+");
    if (inputs.length != 3) {
      throw new DukeException("Invalid task format!");
    }

    try {
      switch (inputs[0]) {
        case Todo.SYMBOL:
          return new Todo(inputs[2], inputs[1].equals("1"));
        case Deadline.SYMBOL:
          return new Deadline(inputs[2], inputs[1].equals("1"), inputs[3]);
        case Event.SYMBOL:
          return new Event(inputs[2], inputs[1].equals("1"), inputs[3]);
        default:
          throw new DukeException("Invalid task format!");
      }
    } catch (DateTimeParseException e) {
      throw new DukeException("Invalid date format!");
    }
  }

  Task setStatus(boolean status) {
    this.status = status;
    return this;
  }

  public abstract String encode();

  @Override
  public String toString() {
    return "[" + (this.status ? "X" : " ") + "] " + this.title;
  }
}
