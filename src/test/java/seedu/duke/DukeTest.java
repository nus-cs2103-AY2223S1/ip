package seedu.duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a DukeTask Class.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeTest {

    /**
     * Basic test to make sure Gradle is working.
     */
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    /**
     * Test to check if the formatting of the task when sent to the file is right.
     */
    @Test
    public void taskToFileTest(){
        assertEquals(Task.of(Task.TaskType.TODO, "finish CS2103T assignment").textFileMessage(),
                "T | 0 | finish CS2103T assignment");
    }

    /**
     * Test to check if the formatting of the task for the task list is right.
     */
    @Test
    public void taskTest(){
        assertEquals(Task.of(Task.TaskType.TODO, "finish CS2103T assignment").toString(),
                "[T][ ] finish CS2103T assignment");
    }

    /**
     * Tests whether the contains method in Task Class works.
     */
    @Test
    public void TaskContainsTest(){
        assertEquals(Task.of(Task.TaskType.TODO, "ACHIEVE SUCCESS").contains("SU"), true);
    }

}