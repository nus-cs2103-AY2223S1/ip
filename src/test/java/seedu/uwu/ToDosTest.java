package seedu.uwu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import uwu.exception.UwuException;
import uwu.task.ToDos;
import uwu.task.Deadline;


public class ToDosTest {
    @Test
    public void toString_nonNullTask_success() {
        ToDos task = new ToDos("read book");
        assertEquals("[T][ ]\tread book",task.toString());
    }

    @Test
    public void toStorageString_nonNullTask_success() {
        try {
            Deadline task = new Deadline("homework", "2022-09-09");
            assertEquals("D,0,homework,2022-09-09 00:00", task.toStorageString());
        } catch (UwuException e) {
            System.out.println(e.getMessage());
        }
    }
}
