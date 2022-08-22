package seedu.duke;

import duke.Duke;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void taskToFileTest(){
        assertEquals(Task.of(Task.Task_type.TODO, "finish CS2103T assignment").textFileMessage(),
                "T | 0 | finish CS2103T assignment");
    }

    @Test
    public void taskTest(){
        assertEquals(Task.of(Task.Task_type.TODO, "finish CS2103T assignment").toString(),
                "[T][ ] finish CS2103T assignment");
    }


}