package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        Task homework = new Todo("Home work to do");
        assertEquals(homework.getDescription(), "Home work to do");
    }

    @Test
    public void anotherDummyTest(){
        Task homework = new Event("Home work to do", "Monday");
        assertEquals(homework.getDate(), "Monday");
    }
}