package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    // Todo: include test for more classes and methods
    @Test
    public void toStringTest() {
        assertEquals(new Todo("str1").toString(), "[T][ ]: str1");
    }

    @Test
    public void toStorageStringTest() {
        assertEquals(new Todo("str2").toStorageString(), "T | 0 | str2");
    }
}
