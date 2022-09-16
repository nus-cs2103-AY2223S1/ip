package duke;

import duke.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
  @Test
  public void stringTest() {
      Todo p = new Todo("football", false);
      assertEquals("[T][O]football",p.toString());
  }

}
