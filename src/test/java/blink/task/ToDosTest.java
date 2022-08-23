package blink.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void addToDo() {
        Task todo = new ToDos("Study");
        assertEquals("[T][ ] Study",todo.toString());
    }

    @Test
    public void markToDo() {
        Task todo = new ToDos("Study 2");
        todo.mark();
        assertEquals("[T][X] Study 2", todo.toString());
    }

    @Test
    public void unMarkToDo() {
        Task todo = new ToDos("Study 3");
        todo.mark();
        todo.unMark();
        assertEquals("[T][ ] Study 3", todo.toString());
    }

    @Test
    public void checkToDoDate() {
        Task todo = new ToDos("Study 4");
        LocalDate date = LocalDate.parse("2022-08-23");
        assertEquals(false, todo.checkDate(date));
    }

    @Test
    public void saveToDo() {
        Task todo = new ToDos("Study 5");
        String expected = "T |0| Study 5\n";
        assertEquals(expected, todo.saveString());
    }
}
