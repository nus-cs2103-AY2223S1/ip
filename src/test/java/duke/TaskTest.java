package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIconTest() {
        Task newTask1 = new Task("PHS3122", "T");
        Task newTask2 = new Task("CS2103", "D", true);
        assertAll(() -> assertEquals(newTask1.getStatusIcon(), " "),
        () -> assertEquals(newTask2.getStatusIcon(), "X"));
    }

    @Test
    void getDescriptionTest() {
        Task newTask = new Task("CS2103", "E");
        assertEquals(newTask.getDescription(), "CS2103");
    }

    @Test
    void getCodeTest() {
        Task newTask = new Task("CS2103", "E");
        assertEquals(newTask.getCode(), "E");
    }

    @Test
    void markDoneTest() {
        Task newTask = new Task("PHS3122", "E", false);
        assertEquals(newTask.getStatusIcon(), " ");
        newTask.markAsDone();
        assertEquals(newTask.getStatusIcon(), "X");
        newTask.unmarkAsDone();
        assertEquals(newTask.getStatusIcon(), " ");
    }

    @Test
    void printText() {
        Task newTask = new Task("PHS3116", "E", false);
        String text = "E |   | PHS3116";
        assertEquals(text, newTask.printText());
    }

    @Test
    void testToString() {
        Task newTask = new Task("PHS3116", "E", false);
        String toString = "[ ] PHS3116";
        assertEquals(newTask.toString(), toString);
    }
}