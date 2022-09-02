import functional.Deadline;
import functional.Event;
import org.junit.jupiter.api.Test;
import technical.SaveLine;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DukeTest {
  @Test
  public void saveLineChecks() {
    SaveLine exampleDeadline = new Deadline("Theology",
        LocalDateTime.of(2022, 9, 2, 20, 10, 4)).toData();
    String printedDeadline = exampleDeadline.toString();
    SaveLine readDeadline = SaveLine.of(printedDeadline);
    assertEquals(exampleDeadline, readDeadline);

    SaveLine exampleEvent = new Event("Theology",
        LocalDateTime.of(2022, 9, 2, 20, 10, 4),
        LocalDateTime.of(2023, 10, 11, 12, 13, 14)).toData();
    String printedEvent = exampleEvent.toString();
    SaveLine readEvent = SaveLine.of(printedEvent);
    assertEquals(exampleEvent, readEvent);

    exampleEvent.setNameData("startTime", "something else");

    assertNotEquals(exampleEvent, readEvent);
  }
}
