package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Todo;

//featureUnderTest_testScenario_expectedBehavior()
public class TodoTest {
    @Test
    public void newTodoTest(){
        String name = "test1";
        Todo newTodo = new Todo(name);
        assertEquals("[T][ ] " + name, newTodo.toString());
    }


    @Test
    public void newTodoTest_markTest(){
        String name = "test1";
        Todo newTodo = new Todo(name);
        newTodo.markComplete();
        assertEquals("[T][X] " + name, newTodo.toString());
    }
}