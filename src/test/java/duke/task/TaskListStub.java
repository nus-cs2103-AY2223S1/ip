package duke.task;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListStub extends TaskList {
  public TaskListStub() {
    super(new ArrayList<Task>());
  }
}
