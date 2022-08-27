package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

  @Test
  public void parseDateTest1(){
    assertEquals(Parser.parseDate("2019-02-19"), "Feb 19 2019");
  }

  @Test
  public void parseDateTest2(){
    assertEquals(Parser.parseDate("2020-10-27"), "Oct 27 2020");
  }

  @Test
  public void parseDateTest3(){
    try {
      Parser.parseDate("2020-20-27");
    } catch (DateTimeParseException e) {
      assertEquals(e.toString(),
              "java.time.format.DateTimeParseException: Text '2020-20-27' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 20");
    }
  }
}

