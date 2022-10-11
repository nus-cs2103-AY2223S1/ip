package rattus.chatbot.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_noBoolean_prefixAdded() {
        String description = "Finish level-7";
        String expected = "[T][ ] Finish level-7";
        String actual = new ToDo(description).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toString_haveBoolean_boxTicked() {
        String description = "Finish level-7";
        String expected = "[T][X] Finish level-7";
        String actual = new ToDo(description, true).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toString_emptyDescription_prefixOnly() {
        String expected = "[T][ ] ";
        String actual = new ToDo("").toString();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_noBoolean_stringWithZero() {
        String description = "Finish level-7";
        String expected = "T`0`Finish level-7`";
        String actual = new ToDo(description).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_haveBoolean_stringWithOne() {
        String description = "Finish level-7";
        String expected = "T`1`Finish level-7`";
        String actual = new ToDo(description, true).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_emptyDescription_emptySuffix() {
        String expected = "T`0``";
        String actual = new ToDo("").encode();
        assertEquals(expected, actual);
    }

    @Test
    public void equals_toDoAllSame_equal() {
        String description = "Go to school";
        ToDo first = new ToDo(description);
        ToDo second = new ToDo(description);
        assertEquals(first, second);
    }

    @Test
    public void equals_toDoDifferentDescription_notEqual() {
        String description1 = "Go to school";
        String description2 = "Walk the dog";
        ToDo first = new ToDo(description1);
        ToDo second = new ToDo(description2);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_toDoDifferentCompletion_notEqual() {
        String description = "Go to school";
        ToDo first = new ToDo(description);
        ToDo second = new ToDo(description, true);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_toDoOneNull_notEqual() {
        String description = "Go to school";
        ToDo first = new ToDo(description);
        ToDo second = null;
        assertNotEquals(first, second);
    }

    @Test
    public void equals_toDoAndEvent_notEqual() {
        String description = "Go to school";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        ToDo first = new ToDo(description);
        Event second = new Event(description, dateTime);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_toDoAndDeadline_notEqual() {
        String description = "Go to school";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        ToDo first = new ToDo(description);
        Deadline second = new Deadline(description, dateTime);
        assertNotEquals(first, second);
    }
}
