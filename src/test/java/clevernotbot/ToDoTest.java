package clevernotbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void getTaskTypeTest() {
        Task toDoTest = new ToDo("Test 1",false);
        assertEquals(toDoTest.getTaskType(),"T");
    }

    @Test
    public void toggleCompletedTest(){
        Task toDoTest = new ToDo("Test 2",false);
        assertEquals(!toDoTest.isCompleted(),toDoTest.toggleCompleted().isCompleted());
    }

    @Test
    public void toStringTest(){
        Task toDoTest = new ToDo("Test 3",false);
        assertEquals(toDoTest.toString(),"[T][ ] Test 3");
    }

}
