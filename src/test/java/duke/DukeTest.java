package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void todoTest(){
        assertEquals("[T][ ] ruaPHD", new Todo("ruaPHD").toString());
    }

    @Test
    public void eventTest(){
        assertEquals(4, 4);
    }
}