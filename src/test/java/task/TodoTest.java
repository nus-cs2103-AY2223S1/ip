package task;

import exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString_for_unmarked_todo() {
        Todo test = new Todo("Testing!");
        String expected = "[T][ ] Testing!";
        assertEquals(expected, test.toString());
    }

    @Test
    void validateInput_throws_exception_when_input_length_less_than_two() {
        String[] inputLengthLessThanOne = new String[] {
                "Testing!"
        };
        assertThrows(
                DukeException.class,
                () -> Todo.validateInput(inputLengthLessThanOne));
    }

    @Test
    void validateInput_throws_exception_when_second_string_is_whitespace() {
        String[] secondStringIsWhitespace = new String[] {
                "Testing!",
                "        ",
        };
        assertThrows(
                DukeException.class,
                () -> Todo.validateInput(secondStringIsWhitespace));
    }

    @Test
    void encode_unmarked_todo() {
        Todo test = new Todo("Testing!");
        String expected = "T,0,Testing!\n";
        assertEquals(expected, test.encode());
    }

    @Test
    void encode_marked_todo() {
        Todo test = new Todo("Testing!");
        test.setIsMarked(true);
        String expected = "T,1,Testing!\n";
        assertEquals(expected, test.encode());
    }
}