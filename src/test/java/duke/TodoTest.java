package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void TodoObjectTest() {
        Todo todoObject = new Todo("Homework 1");
        String actualOutput = "[T][ ] Homework 1";
        assertEquals(todoObject.toString(), actualOutput);
    }
}
