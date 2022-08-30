package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {

    @Test
    public void greetingTest(){
        UI ui = new UI();
        String greeting = "Hello! I'm Duke \n" 
                            + "What can I do for you? \n" ;
        assertEquals(greeting, ui.greet());
    }

    @Test
    public void addToListTest(){
        TaskList taskList = new TaskList();
        UI ui = new UI();
        taskList.addToList("todo do something", ui);
        assertEquals("do something", taskList.getTaskList().get(0).description);
    }
}
