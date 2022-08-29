package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exception.DukeException;

class TodoTest {

    @Test
    @DisplayName("Test for toString() method of unmarked todo")
    void testToString_for_unmarked_todo() {
        Todo test = new Todo("Testing!");
        String expected = "[T][ ] Testing!";
        assertEquals(expected, test.toString());
    }

    @Test
    @DisplayName("Test validateInput throws exception when task item is not given")
    void validateInput_throws_exception_when_input_length_less_than_two() {
        String[] inputLengthLessThanOne = new String[] {
                "Testing!"
        };
        assertThrows(
                DukeException.class,
                () -> Todo.validateInput(inputLengthLessThanOne));
    }

    @Test
    @DisplayName("Test validateInput throws exception when task item is whitespace")
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
    @DisplayName("Test for encode() method of unmarked todo")
    void encode_unmarked_todo() {
        Todo test = new Todo("Testing!");
        String expected = "T,0,Testing!\n";
        assertEquals(expected, test.encode());
    }

    @Test
    @DisplayName("Test for encode() method of marked todo")
    void encode_marked_todo() {
        Todo test = new Todo("Testing!");
        test.setIsMarked(true);
        String expected = "T,1,Testing!\n";
        assertEquals(expected, test.encode());
    }
}