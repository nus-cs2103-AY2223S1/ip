package duke.command;

import duke.task.TaskListStub;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ByeCommandTest {
  @Test
  public void initialisationTest() {
    ByeCommand byeCommand = new ByeCommand(
        new String[1],
        new TaskListStub()
        );
  }

  @Test
  public void performAction_success() {
    String[] commandArgs = new String[1];
    commandArgs[0] = "Bye";
    ByeCommand byeCommand = new ByeCommand(commandArgs, new TaskListStub());
    assertFalse(byeCommand.performAction());
  }
}
