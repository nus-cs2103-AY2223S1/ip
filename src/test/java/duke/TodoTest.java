package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void stringToTodo() {
        String testString = "[T][X] read book";
        Todo convertedStr = new Todo("");
        try {
            convertedStr = Todo.stringToTodo(testString);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(testString, convertedStr.toString());
    }
}