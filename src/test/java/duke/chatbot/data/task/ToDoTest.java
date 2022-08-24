package duke.chatbot.data.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_noBoolean_prefixAdded() {
        assertEquals("[T][ ] Finish level-7", new ToDo("Finish level-7").toString());
    }

    @Test
    public void toString_haveBoolean_boxTicked() {
        assertEquals("[T][X] Finish level-7", new ToDo("Finish level-7", true).toString());
    }

    @Test
    public void toString_emptyDescription_prefixOnly() {
        assertEquals("[T][ ] ", new ToDo("").toString());
    }

    @Test
    public void encode_noBoolean_stringWithZero() {
        assertEquals("T,,,0,,,Finish level-7", new ToDo("Finish level-7").encode());
    }

    @Test
    public void encode_haveBoolean_stringWithOne() {
        assertEquals("T,,,1,,,Finish level-7", new ToDo("Finish level-7", true).encode());
    }

    @Test
    public void encode_emptyDescription_emptySuffix() {
        assertEquals("T,,,0,,,", new ToDo("").encode());
    }
}
