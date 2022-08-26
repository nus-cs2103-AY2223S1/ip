package duke.task;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
  @Test
  public void toStringTest() throws DukeException {
    assertEquals(new Event("Test", "2022-12-12 1800").toString(),
        "[E][ ] Test (at: Dec 12, 2022 | 6:00PM)");
  }

  @Test
  public void getStorageStringTest() throws DukeException {
    assertEquals(new Event("Test", "2022-12-12 1800").getStorageString(),
        "E >> 0 >> Test >> 2022-12-12 1800");
  }
}
