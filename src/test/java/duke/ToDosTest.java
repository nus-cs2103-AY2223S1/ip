package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void toDoTest_addTodo_todoPrinted(){
        ToDos todo = new ToDos("eat");
        assertEquals(todo.toString(), "[T][ ] eat");
    }

}